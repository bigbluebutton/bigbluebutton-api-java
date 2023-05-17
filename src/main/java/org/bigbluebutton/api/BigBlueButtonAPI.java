/*
 * BigBlueButton open source conferencing system - https://www.bigbluebutton.org/.
 *
 * Copyright (c) 2023 BigBlueButton Inc. and by respective authors (see below).
 *
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 3.0 of the License, or (at your option) any later
 * version.
 *
 * BigBlueButton is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with BigBlueButton; if not, see <http://www.gnu.org/licenses/>.
 */

package org.bigbluebutton.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.bigbluebutton.api.handlers.ApiResponseHandler;
import org.bigbluebutton.api.parameters.CreateMeetingParameters;
import org.bigbluebutton.api.parameters.DeleteRecordingsParameters;
import org.bigbluebutton.api.parameters.EndMeetingParameters;
import org.bigbluebutton.api.parameters.GetMeetingInfoParameters;
import org.bigbluebutton.api.parameters.GetRecordingTextTracksParameters;
import org.bigbluebutton.api.parameters.GetRecordingsParameters;
import org.bigbluebutton.api.parameters.HooksCreateParameters;
import org.bigbluebutton.api.parameters.HooksDestroyParameters;
import org.bigbluebutton.api.parameters.InsertDocumentParameters;
import org.bigbluebutton.api.parameters.IsMeetingRunningParameters;
import org.bigbluebutton.api.parameters.JoinMeetingParameters;
import org.bigbluebutton.api.parameters.PublishRecordingsParameters;
import org.bigbluebutton.api.parameters.PutRecordingTextTrackParameters;
import org.bigbluebutton.api.parameters.UpdateRecordingsParameters;
import org.bigbluebutton.api.responses.ApiVersionResponse;
import org.bigbluebutton.api.responses.CreateMeetingResponse;
import org.bigbluebutton.api.responses.DeleteRecordingsResponse;
import org.bigbluebutton.api.responses.EndMeetingResponse;
import org.bigbluebutton.api.responses.GetMeetingInfoResponse;
import org.bigbluebutton.api.responses.GetMeetingsResponse;
import org.bigbluebutton.api.responses.GetRecordingTextTracksResponse;
import org.bigbluebutton.api.responses.GetRecordingsResponse;
import org.bigbluebutton.api.responses.HooksCreateResponse;
import org.bigbluebutton.api.responses.HooksDestroyResponse;
import org.bigbluebutton.api.responses.HooksListResponse;
import org.bigbluebutton.api.responses.InsertDocumentResponse;
import org.bigbluebutton.api.responses.JoinMeetingResponse;
import org.bigbluebutton.api.responses.PublishRecordingsResponse;
import org.bigbluebutton.api.responses.PutRecordingTextTrackResponse;
import org.bigbluebutton.api.responses.UpdateRecordingsResponse;
import org.bigbluebutton.api.util.URLBuilder;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Getter;
import lombok.Setter;

public class BigBlueButtonAPI {

    @Getter
    protected String securitySalt;

    @Getter
    protected String baseServerURL;

    @Getter
    @Setter
    protected Integer timeout = 10;

    @Getter
    @Setter
    protected String hashingAlgorithm = HashingAlgorithms.SHA_256;

    protected URLBuilder urlBuilder;

    /**
     * Jackson XML mapper to transform responses to objects
     */
    private XmlMapper xmlMapper;

    public BigBlueButtonAPI() {
        this(System.getenv("BBB_SERVER_BASE_URL"), System.getenv("BBB_SECURITY_SALT"));
    }

    public BigBlueButtonAPI(String hashingAlgorithm) {
        this();
        this.hashingAlgorithm = hashingAlgorithm;
    }

    public BigBlueButtonAPI(String baseUrl, String securitySalt) {
        this.baseServerURL = baseUrl;
        this.securitySalt  = securitySalt;
        this.xmlMapper     = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.findAndRegisterModules();
        this.urlBuilder = new URLBuilder(this.baseServerURL, this.securitySalt, this.hashingAlgorithm);
    }

    // API version

    public URI getApiVersionURL() throws URISyntaxException {
        return urlBuilder.buildUrl(ApiMethod.ROOT);
    }

    public ApiVersionResponse getAPIVersion() throws MalformedURLException, IOException, ParserConfigurationException,
            SAXException, InterruptedException, URISyntaxException {
        return xmlMapper.readValue(this.sendRequest(getApiVersionURL()), ApiVersionResponse.class);
    }

    // Create meeting

    public URI getCreateMeetingURL(CreateMeetingParameters createMeetingParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.CREATE, createMeetingParams.getQueryParms());
    }

    public CreateMeetingResponse createMeeting(CreateMeetingParameters createMeetingParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException, TransformerException {
        return sendApiRequest(getCreateMeetingURL(createMeetingParams), createMeetingParams.getPresentationsAsXML(),
                CreateMeetingResponse.class);
    }

    // Join meeting

    public URI getJoinMeetingURL(JoinMeetingParameters joinMeetingParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.JOIN, joinMeetingParams.getQueryParms());
    }

    public JoinMeetingResponse joinMeeting(JoinMeetingParameters joinMeetingParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getJoinMeetingURL(joinMeetingParams), JoinMeetingResponse.class);
    }

    // End meeting

    public URI getEndMeetingURL(EndMeetingParameters endMeetingParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.END, endMeetingParams.getQueryParms());
    }

    public EndMeetingResponse endMeeting(EndMeetingParameters endMeetingParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getEndMeetingURL(endMeetingParams), EndMeetingResponse.class);
    }

    // Insert document

    public URI getInsertDocumentUrl(InsertDocumentParameters insertDocumentParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.INSERT_DOCUMENT, insertDocumentParams.getQueryParms());
    }

    public InsertDocumentResponse insertDocument(InsertDocumentParameters insertDocumentParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getInsertDocumentUrl(insertDocumentParams), InsertDocumentResponse.class);
    }

    // Is meeting running

    public URI getIsMeetingRunningUrl(IsMeetingRunningParameters isMeetingRunningParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.IS_MEETING_RUNNING, isMeetingRunningParams.getQueryParms());
    }

    public InsertDocumentResponse isMeetingRunning(IsMeetingRunningParameters isMeetingRunningParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getIsMeetingRunningUrl(isMeetingRunningParams), InsertDocumentResponse.class);
    }

    // Get meetings

    public URI getMeetingsUrl() throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.GET_MEETINGS);
    }

    public GetMeetingsResponse getMeetings()
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getMeetingsUrl(), GetMeetingsResponse.class);
    }

    // Get meeting info

    public URI getMeetingInfoUrl(GetMeetingInfoParameters getMeetingInfoParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.GET_MEETING_INFO, getMeetingInfoParams.getQueryParms());
    }

    public GetMeetingInfoResponse endMeeting(GetMeetingInfoParameters getMeetingInfoParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getMeetingInfoUrl(getMeetingInfoParams), GetMeetingInfoResponse.class);
    }

    // Get recordings

    public URI getRecordingsUrl(GetRecordingsParameters getRecordingsParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.GET_RECORDINGS, getRecordingsParams.getQueryParms());
    }

    public GetRecordingsResponse getRecordings(GetRecordingsParameters getRecordingsParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getRecordingsUrl(getRecordingsParams), GetRecordingsResponse.class);
    }

    // Publish recordings

    public URI getPublishRecordingsUrl(PublishRecordingsParameters publishRecordingsParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.PUBLISH_RECORDINGS, publishRecordingsParams.getQueryParms());
    }

    public PublishRecordingsResponse publishRecordings(PublishRecordingsParameters publishRecordingsParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getPublishRecordingsUrl(publishRecordingsParams), PublishRecordingsResponse.class);
    }

    // Delete recordings

    public URI getDeleteRecordingsUrl(DeleteRecordingsParameters publishRecordingsParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.DELETE_RECORDINGS, publishRecordingsParams.getQueryParms());
    }

    public DeleteRecordingsResponse publishRecordings(DeleteRecordingsParameters publishRecordingsParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getDeleteRecordingsUrl(publishRecordingsParams), DeleteRecordingsResponse.class);
    }

    // Update recordings

    public URI getUpdateRecordingsUrl(UpdateRecordingsParameters updateRecordingsParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.UPDATE_RECORDINGS, updateRecordingsParams.getQueryParms());
    }

    public UpdateRecordingsResponse updateRecordings(UpdateRecordingsParameters updateRecordingsParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getUpdateRecordingsUrl(updateRecordingsParams), UpdateRecordingsResponse.class);
    }

    // Get recording text tracks

    public URI getRecordingTextTracksUrl(GetRecordingTextTracksParameters getRecordingTextTracksParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.GET_RECORDING_TEXT_TRACKS, getRecordingTextTracksParams.getQueryParms());
    }

    public GetRecordingTextTracksResponse getRecordingTextTracks(
            GetRecordingTextTracksParameters getRecordingTextTracksParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getRecordingTextTracksUrl(getRecordingTextTracksParams),
                GetRecordingTextTracksResponse.class);
    }

    // Put recording text tracks

    public URI getPutRecordingTextTrackUrl(PutRecordingTextTrackParameters getRecordingTextTracksParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.PUT_RECORDING_TEXT_TRACK, getRecordingTextTracksParams.getQueryParms());
    }

    public PutRecordingTextTrackResponse putRecordingTextTrack(
            PutRecordingTextTrackParameters getRecordingTextTracksParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getPutRecordingTextTrackUrl(getRecordingTextTracksParams),
                PutRecordingTextTrackResponse.class);
    }

    // Create hook

    public URI getHooksCreateUrl(HooksCreateParameters hooksCreateParames)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.HOOKS_CREATE, hooksCreateParames.getQueryParms());
    }

    public HooksCreateResponse hooksCreate(HooksCreateParameters hooksCreateParames)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getHooksCreateUrl(hooksCreateParames), HooksCreateResponse.class);
    }

    // List hooks

    public URI getHooksListUrl() throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.HOOKS_LIST);
    }

    public HooksListResponse hooksList() throws JsonMappingException, JsonProcessingException, MalformedURLException,
            IOException, ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getHooksListUrl(), HooksListResponse.class);
    }

    // Destroy hook

    public URI getHooksDestroyUrl(HooksDestroyParameters hooksDestroyParames)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.HOOKS_CREATE, hooksDestroyParames.getQueryParms());
    }

    public HooksDestroyResponse hooksCreate(HooksDestroyParameters hooksDestroyParames)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getHooksDestroyUrl(hooksDestroyParames), HooksDestroyResponse.class);
    }

    // Common methods

    public <T> T sendApiRequest(URI uri, Class<T> responseType)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return xmlMapper.readValue(this.sendRequest(uri, null), responseType);
    }

    public <T> T sendApiRequest(URI uri, String payload, Class<T> responseType)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return xmlMapper.readValue(this.sendRequest(uri, payload), responseType);
    }

    protected String sendRequest(URI uri) throws MalformedURLException, IOException, ParserConfigurationException,
            SAXException, InterruptedException {
        return this.sendRequest(uri, null, "application/xml");
    }

    protected String sendRequest(URI uri, String payload) throws MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException {
        return this.sendRequest(uri, payload, "application/xml");
    }

    protected String sendRequest(URI uri, String payload, String contentType) throws MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException {
        // Open a connection to the API endpoint
        HttpClient         httpClient = HttpClientBuilder.create().build();
        HttpUriRequestBase httpRequest;
        if (payload == null) {
            httpRequest = new HttpGet(uri);
        } else {
            httpRequest = new HttpPost(uri);
            // Set the payload as the request entity
            StringEntity requestEntity = new StringEntity(payload);
            httpRequest.setEntity(requestEntity);
            httpRequest.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
            httpRequest.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(payload.length()));
        }

        httpRequest.setHeader(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8);
        httpRequest.setHeader(HttpHeaders.TIMEOUT, this.timeout);

        ApiResponseHandler apiResponseHandler = new ApiResponseHandler();
        return httpClient.execute(httpRequest, apiResponseHandler);
    }
}
