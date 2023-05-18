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

package org.bigbluebutton.api.data;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;

public class Playback {
    @Getter
    private String type;

    @Getter
    private URI url;

    @Getter
    private Integer processingTime;

    @Getter
    private Integer length;

    @Getter
    private Integer size;

    @JsonProperty(required = false)
    private Preview preview;

    public List<Image> getImages() {
        return preview.getImages();
    }

    public static class Preview {
        @Getter
        @JacksonXmlElementWrapper(localName = "images")
        @JacksonXmlProperty(localName = "image")
        @JsonProperty(required = false)
        private List<Image> images;
    }
}
