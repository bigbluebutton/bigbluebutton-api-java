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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiMethodTest {

    @Test
    @DisplayName("Test API method names")
    void apiMethodNamesShouldMatch() {
        assertEquals(ApiMethod.CREATE.getName(), "create");
        assertEquals(ApiMethod.JOIN.getName(), "join");
        assertEquals(ApiMethod.INSERT_DOCUMENT.getName(), "insertDocument");
        assertEquals(ApiMethod.ENTER.getName(), "enter");
        assertEquals(ApiMethod.END.getName(), "end");
        assertEquals(ApiMethod.IS_MEETING_RUNNING.getName(), "isMeetingRunning");
        assertEquals(ApiMethod.GET_MEETING_INFO.getName(), "getMeetingInfo");
        assertEquals(ApiMethod.GET_MEETINGS.getName(), "getMeetings");
        assertEquals(ApiMethod.SIGN_OUT.getName(), "signOut");
        assertEquals(ApiMethod.GET_RECORDINGS.getName(), "getRecordings");
        assertEquals(ApiMethod.PUBLISH_RECORDINGS.getName(), "publishRecordings");
        assertEquals(ApiMethod.DELETE_RECORDINGS.getName(), "deleteRecordings");
        assertEquals(ApiMethod.UPDATE_RECORDINGS.getName(), "updateRecordings");
        assertEquals(ApiMethod.GET_RECORDING_TEXT_TRACKS.getName(), "getRecordingTextTracks");
        assertEquals(ApiMethod.PUT_RECORDING_TEXT_TRACK.getName(), "putRecordingTextTrack");
        assertEquals(ApiMethod.HOOKS_CREATE.getName(), "hooks/create");
        assertEquals(ApiMethod.HOOKS_LIST.getName(), "hooks/list");
        assertEquals(ApiMethod.HOOKS_DESTROY.getName(), "hooks/destroy");
    }
}
