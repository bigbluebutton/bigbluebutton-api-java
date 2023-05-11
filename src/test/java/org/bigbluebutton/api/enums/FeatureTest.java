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

public class FeatureTest {

    @Test
    @DisplayName("Test feature names")
    void featureNamesShouldMatch() {
        assertEquals(Feature.BREAKOUT_ROOMS.getName(), "breakoutRooms");
        assertEquals(Feature.CAPTIONS.getName(), "captions");
        assertEquals(Feature.CHAT.getName(), "chat");
        assertEquals(Feature.DOWNLOAD_PRESENTATION_WITH_ANNOTATIONS.getName(), "downloadPresentationWithAnnotations");
        assertEquals(Feature.EXTERNAL_VIDEOS.getName(), "externalVideos");
        assertEquals(Feature.IMPORT_PRESENTATION_WITH_ANNOTATIONS_FROM_BREAKOUT_ROOMS.getName(),
                "importPresentationWithAnnotationsFromBreakoutRooms");
        assertEquals(Feature.IMPORT_SHARED_NOTES_FROM_BREAKOUT_ROOMS.getName(), "importSharedNotesFromBreakoutRooms");
        assertEquals(Feature.LAYOUTS.getName(), "layouts");
        assertEquals(Feature.LEARNING_DASHBOARD.getName(), "learningDashboard");
        assertEquals(Feature.POLLS.getName(), "polls");
        assertEquals(Feature.SCREENSHARE.getName(), "screenshare");
        assertEquals(Feature.SHARED_NOTES.getName(), "sharedNotes");
        assertEquals(Feature.VIRTUAL_BACKGROUNDS.getName(), "virtualBackgrounds");
        assertEquals(Feature.CUSTOM_VIRTUAL_BACKGROUNDS.getName(), "customVirtualBackgrounds");
        assertEquals(Feature.LIVE_TRANSCRIPTION.getName(), "liveTranscription");
        assertEquals(Feature.PRESENTATION.getName(), "presentation");
    }
}
