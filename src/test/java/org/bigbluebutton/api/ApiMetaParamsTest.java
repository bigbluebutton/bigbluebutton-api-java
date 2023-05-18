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

import org.bigbluebutton.api.test.BaseTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiMetaParamsTest extends BaseTestCase {

    @Test
    @DisplayName("Test API meta parameters names")
    void apiMethodNamesShouldMatch() {

        assertEquals(ApiMetaParams.META_PREFIX, "meta_");
        assertEquals(ApiMetaParams.END_CALLBACK_URL, "endCallbackUrl");
        assertEquals(ApiMetaParams.BBB_RECORDING_READY_URL, "bbb-recording-ready-url");
        assertEquals(ApiMetaParams.CANVAS_RECORDING_READY_URL, "canvas-recording-ready-url");
        assertEquals(ApiMetaParams.BBB_ANONYMIZE_CHAT, "bbb-anonymize-chat");
        assertEquals(ApiMetaParams.BBB_ANONYMIZE_MODERATORS, "bbb-anonymize-moderators");
        assertEquals(ApiMetaParams.HACK_RECORD_VIEWER_VIDEO, "hack-record-viewer-video");
    }
}
