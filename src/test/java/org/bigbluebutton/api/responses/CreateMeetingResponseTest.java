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
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;

import org.bigbluebutton.api.test.ResponseTestCase;
import org.bigbluebutton.api.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

class CreateMeetingResponseTest extends ResponseTestCase {

    @BeforeEach
    public void setUp() {
        xmlResponseFile = "fixtures/create_meeting.xml";

        super.setUp();
    }

    @Test
    @DisplayName("Create meeting response content")
    void testCreateMeetingResponseContent() throws StreamReadException, DatabindException, IOException {
        CreateMeetingResponse createResponse = xmlMapper.readValue(xmlInput, CreateMeetingResponse.class);
        assertEquals(createResponse.getReturnCode(), APIReturnCode.SUCCESS.getReturnCode());
        assertEquals(createResponse.getMeetingId(), "random-1665177");
        assertEquals(createResponse.getInternalMeetingId(), "1a6938c707cdf5d052958672d66c219c30690c47-1524212045514");
        assertEquals(createResponse.getParentMeetingId(), "bbb-none");
        assertEquals(createResponse.getCreateTime().getEpochSecond(), 1453283819419L);
        assertEquals(createResponse.getVoiceBridge(), "76286");
        assertEquals(createResponse.getDialNumber(), "613-555-1234");
        assertEquals(createResponse.getCreateDate().format(DateTimeUtil.TZ_DATE_FORMATTER),
                "Wed Jan 20 04:56:59 CET 2016");
        assertFalse(createResponse.getHasUserJoined());
        assertEquals(createResponse.getDuration(), 20);
        assertFalse(createResponse.getHasBeenForciblyEnded());
        assertEquals(createResponse.getMessageKey(), "duplicateWarning");
        assertEquals(createResponse.getMessage(),
                "This conference was already in existence and may currently be in progress.");
    }
}
