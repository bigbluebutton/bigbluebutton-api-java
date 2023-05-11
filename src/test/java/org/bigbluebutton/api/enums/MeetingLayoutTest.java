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

package org.bigbluebutton.api.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MeetingLayoutTest {

    @Test
    @DisplayName("Test meeting layout names")
    void featureNamesShouldMatch() {
        assertEquals(MeetingLayout.CUSTOM_LAYOUT.getName(), "CUSTOM_LAYOUT");
        assertEquals(MeetingLayout.PRESENTATION_FOCUS.getName(), "PRESENTATION_FOCUS");
        assertEquals(MeetingLayout.SMART_LAYOUT.getName(), "SMART_LAYOUT");
        assertEquals(MeetingLayout.VIDEO_FOCUS.getName(), "VIDEO_FOCUS");
    }
}
