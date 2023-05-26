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

package org.bigbluebutton.api.responses;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.bigbluebutton.api.test.JsonResponseTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class GetRecordingTextTracksResponseTest extends JsonResponseTestCase {

    @BeforeEach
    public void setUp() {
        xmlResponseFile = "fixtures/get_recording_text_tracks.json";

        super.setUp();
    }

    @Test
    @DisplayName("Get recording text tracks content")
    void testRecordingsTextTracksResponseContent() throws StreamReadException, DatabindException, IOException {
        GetRecordingTextTracksResponse getMeetingInfoResponse = jsonMapper.readValue(jsonInput,
                GetRecordingTextTracksResponse.class);
        assertEquals(getMeetingInfoResponse.response.getReturnCode(), APIReturnCode.SUCCESS.getReturnCode());
    }
}
