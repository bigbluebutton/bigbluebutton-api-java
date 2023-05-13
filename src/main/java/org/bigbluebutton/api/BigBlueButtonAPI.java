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

import javax.xml.parsers.ParserConfigurationException;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.bigbluebutton.api.handlers.ApiResponseHandler;
import org.bigbluebutton.api.parameters.CreateMeetingParameters;
import org.bigbluebutton.api.parameters.EndMeetingParameters;
import org.bigbluebutton.api.responses.ApiVersionResponse;
import org.bigbluebutton.api.responses.CreateMeetingResponse;
import org.bigbluebutton.api.responses.EndMeetingResponse;
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
    @Setter
    protected String securitySalt;

    @Getter
    @Setter
    protected String baseServerURL;

    protected URLBuilder urlBuilder;

    /**
     * Jackson XML mapper to transform responses to objects
     */
    private XmlMapper xmlMapper;

    public BigBlueButtonAPI() {
        this(System.getenv("BBB_SERVER_BASE_URL"), System.getenv("BBB_SECURITY_SALT"));
    }

    public BigBlueButtonAPI(String baseUrl, String securitySalt) {
        this.baseServerURL = baseUrl;
        this.securitySalt  = securitySalt;
        this.xmlMapper     = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.findAndRegisterModules();
        this.urlBuilder = new URLBuilder(baseUrl, securitySalt);
    }

    public URI getApiVersionURL() throws URISyntaxException {
        return urlBuilder.buildUrl(ApiMethod.ROOT);
    }

    public ApiVersionResponse getAPIVersion() throws MalformedURLException, IOException, ParserConfigurationException,
            SAXException, InterruptedException, URISyntaxException {
        return xmlMapper.readValue(this.sendRequest(getApiVersionURL()), ApiVersionResponse.class);
    }

    public URI getCreateMeetingURL(CreateMeetingParameters createMeetingParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.CREATE, createMeetingParams.getQueryParms());
    }

    public CreateMeetingResponse createMeeting(CreateMeetingParameters createMeetingParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getCreateMeetingURL(createMeetingParams), CreateMeetingResponse.class);

    }

    public URI getEndMeetingURL(EndMeetingParameters endMeetingParams)
            throws URISyntaxException, UnsupportedEncodingException {
        return urlBuilder.buildUrl(ApiMethod.END, endMeetingParams.getQueryParms());
    }

    public EndMeetingResponse endMeeting(EndMeetingParameters endMeetingParams)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return sendApiRequest(getEndMeetingURL(endMeetingParams), EndMeetingResponse.class);
    }

    public <T> T sendApiRequest(URI uri, Class<T> responseType)
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        return xmlMapper.readValue(this.sendRequest(uri), responseType);
    }

    protected String sendRequest(URI uri) throws MalformedURLException, IOException, ParserConfigurationException,
            SAXException, InterruptedException {
        return this.sendRequest(uri, "", "application/xml");
    }

    protected String sendRequest(URI uri, String payload, String contentType) throws MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException {
        // Open a connection to the API endpoint
        HttpClient         httpClient         = HttpClientBuilder.create().build();
        HttpGet            httpGet            = new HttpGet(uri);
        ApiResponseHandler apiResponseHandler = new ApiResponseHandler();
        return httpClient.execute(httpGet, apiResponseHandler);
    }
}
