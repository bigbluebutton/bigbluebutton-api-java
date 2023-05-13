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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;

@JsonIgnoreProperties(value = { "attendeePW", "moderatorPW" })
public class CreateMeetingResponse extends BaseResponse {
    @Getter
    @JacksonXmlProperty(localName = "meetingID")
    private String meetingId;

    @Getter
    @JacksonXmlProperty(localName = "internalMeetingID")
    private String internalMeetingId;

    @Getter
    @JacksonXmlProperty(localName = "parentMeetingID")
    private String parnetMeetingId;

    @Getter
    private String createTime;

    @Getter
    private String voiceBridge;

    @Getter
    private String dialNumber;

    @Getter
    private String createDate;

    @Getter
    private String hasUserJoined;

    @Getter
    private String duration;

    @Getter
    private String hasBeenForciblyEnded;
}
