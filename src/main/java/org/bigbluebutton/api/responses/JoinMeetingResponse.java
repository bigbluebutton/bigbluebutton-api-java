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

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;

public class JoinMeetingResponse extends BaseResponse {
    @Getter
    @JacksonXmlProperty(localName = "meeting_id")
    @JsonProperty(required = false)
    private String meetingId;

    @Getter
    @JacksonXmlProperty(localName = "user_id")
    @JsonProperty(required = false)
    private String userId;

    @Getter
    @JsonProperty(required = false)
    @JacksonXmlProperty(localName = "auth_token")
    private String authToken;

    @Getter
    @JacksonXmlProperty(localName = "session_token")
    @JsonProperty(required = false)
    private String sessionToken;

    @Getter
    @JsonProperty(required = false)
    private String guestStatus;

    @Getter
    @JsonProperty(required = false)
    private URI url;
}
