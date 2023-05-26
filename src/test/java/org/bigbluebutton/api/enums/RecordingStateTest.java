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

import org.bigbluebutton.api.test.BaseTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecordingStateTest extends BaseTestCase {

    @Test
    @DisplayName("Test recording states")
    void featureNamesShouldMatch() {
        assertEquals(RecordingState.ANY.getName(), "any");
        assertEquals(RecordingState.DELETED.getName(), "deleted");
        assertEquals(RecordingState.PROCESSED.getName(), "processed");
        assertEquals(RecordingState.PROCESSING.getName(), "processing");
        assertEquals(RecordingState.PUBLISHED.getName(), "published");
        assertEquals(RecordingState.UNPUBLISHED.getName(), "unpublished");
    }
}
