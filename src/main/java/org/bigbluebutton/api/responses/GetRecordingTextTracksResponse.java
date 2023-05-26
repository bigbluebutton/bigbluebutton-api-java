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

import java.util.List;

import org.bigbluebutton.api.data.Track;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;

public class GetRecordingTextTracksResponse extends BaseResponse {
    @Getter
    protected InternalResponse response;

    public List<Track> getTracks() {
        return response.getTracks();
    }

    public static class InternalResponse {
        @Getter
        @JacksonXmlProperty(localName = "returncode")
        @JsonProperty("returncode")
        protected String returnCode;

        @Getter
        @JsonProperty("tracks")
        private List<Track> tracks;
    }
}
