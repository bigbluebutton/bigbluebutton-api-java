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

import java.util.List;

import org.bigbluebutton.api.deserializers.CustomDataDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;

public class Attendee {
    @Getter
    @JacksonXmlProperty(localName = "userID")
    private String userId;

    @Getter
    private String fullName;

    @Getter
    private String role;

    @Getter
    private Boolean isPresenter;

    @Getter
    private Boolean isListeningOnly;

    @Getter
    private Boolean hasJoinedVoice;

    @Getter
    private Boolean hasVideo;

    @Getter
    private String clientType;

    @JsonDeserialize(using = CustomDataDeserializer.class)
    private List<CustomData> customData;
}
