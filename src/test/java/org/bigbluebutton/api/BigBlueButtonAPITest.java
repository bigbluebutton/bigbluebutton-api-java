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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.bigbluebutton.api.parameters.CreateMeetingParameters;
import org.bigbluebutton.api.responses.ApiVersionResponse;
import org.bigbluebutton.api.responses.CreateMeetingResponse;
import org.bigbluebutton.api.test.BigBlueButtonTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class BigBlueButtonAPITest extends BigBlueButtonTestCase {

    private BigBlueButtonAPI bbbAPI;

    @BeforeEach
    public void setUp() {
        super.setUp();
        bbbAPI = new BigBlueButtonAPI();
    }

    @Test
    @DisplayName("BigBlueButton API version")
    public void fetchAPIVersion() throws MalformedURLException, IOException, ParserConfigurationException, SAXException,
            InterruptedException, URISyntaxException {
        ApiVersionResponse apiVersion = bbbAPI.getAPIVersion();
        assertEquals(apiVersion.getReturnCode(), "SUCCESS");
        assertEquals(apiVersion.getApiVersion(), "2.0");
        assertEquals(apiVersion.getVersion(), "2.0");
        assertEquals(apiVersion.getBbbVersion(), "");
    }

    @Test
    @DisplayName("BigBlueButton Create meeting")
    public void shouldCreateMeeting() throws JsonMappingException, JsonProcessingException, MalformedURLException,
            IOException, ParserConfigurationException, SAXException, InterruptedException, URISyntaxException {
        CreateMeetingParameters createMeetingParms = generateCreateMeetingParams();
        CreateMeetingResponse createMeetingResponse = bbbAPI.createMeeting(createMeetingParms);
        assertEquals(createMeetingResponse.getReturnCode(), "SUCCESS");
        assertEquals(createMeetingResponse.getMeetingId(), createMeetingParms.getMeetingId());
    }
}
