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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.bigbluebutton.api.handlers.ApiResponseHandler;
import org.bigbluebutton.api.responses.ApiVersionResponse;
import org.bigbluebutton.api.util.URLBuilder;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

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
        this.securitySalt = securitySalt;
        this.xmlMapper = new XmlMapper();
        this.urlBuilder = new URLBuilder(baseUrl, securitySalt);
    }

    public ApiVersionResponse getAPIVersion() throws MalformedURLException, IOException, ParserConfigurationException,
            SAXException, InterruptedException, URISyntaxException {
        return xmlMapper.readValue(this.sendRequest(urlBuilder.buildUrl(ApiMethod.ROOT, "")), ApiVersionResponse.class);
    }

    protected String sendRequest(URI uri) throws MalformedURLException, IOException, ParserConfigurationException,
            SAXException, InterruptedException {
        return this.sendRequest(uri, "", "application/xml");
    }

    protected String sendRequest(URI uri, String payload, String contentType) throws MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException {
        // Open a connection to the API endpoint
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(uri);
        ApiResponseHandler apiResponseHandler = new ApiResponseHandler();
        return httpClient.execute(httpGet, apiResponseHandler);
    }
}
