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

import lombok.Getter;

public enum Feature {

    BREAKOUT_ROOMS("breakoutRooms"), CAPTIONS("captions"), CHAT("chat"),
    DOWNLOAD_PRESENTATION_WITH_ANNOTATIONS("downloadPresentationWithAnnotations"), EXTERNAL_VIDEOS("externalVideos"),
    IMPORT_PRESENTATION_WITH_ANNOTATIONS_FROM_BREAKOUT_ROOMS("importPresentationWithAnnotationsFromBreakoutRooms"),
    IMPORT_SHARED_NOTES_FROM_BREAKOUT_ROOMS("importSharedNotesFromBreakoutRooms"), LAYOUTS("layouts"),
    LEARNING_DASHBOARD("learningDashboard"), POLLS("polls"), SCREENSHARE("screenshare"), SHARED_NOTES("sharedNotes"),
    VIRTUAL_BACKGROUNDS("virtualBackgrounds"), CUSTOM_VIRTUAL_BACKGROUNDS("customVirtualBackgrounds"),
    LIVE_TRANSCRIPTION("liveTranscription"), PRESENTATION("presentation");

    @Getter
    private String name;

    Feature(String name) {
        this.name = name;
    }
}
