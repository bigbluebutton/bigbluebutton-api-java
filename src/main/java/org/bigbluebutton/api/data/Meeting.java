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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;

@JsonIgnoreProperties(value = { "attendeePW", "moderatorPW" })
public class Meeting {
    @Getter
    private String meetingName;

    @Getter
    @JacksonXmlProperty(localName = "meetingID")
    private String meetingId;

    @Getter
    @JacksonXmlProperty(localName = "internalMeetingID")
    private String internalMeetingId;

    @Getter
    private Long createTime;

    @Getter
    private String createDate;

    @Getter
    private String voiceBridge;

    @Getter
    private String dialNumber;

    @Getter
    private Boolean running;

    @Getter
    private Integer duration;

    @Getter
    private Boolean hasUserJoined;

    @Getter
    private Boolean recording;

    @Getter
    private Boolean hasBeenForciblyEnded;

    @Getter
    private Integer startTime;

    @Getter
    private Integer endTime;

    @Getter
    private Integer participantCount;

    @Getter
    private Integer listenerCount;

    @Getter
    private Integer voiceParticipantCount;

    @Getter
    private Integer videoCount;

    @Getter
    private Integer maxUsers;

    @Getter
    private Integer moderatorCount;

    // Attendees
    // Metadata

    @Getter
    private Boolean isBreakout;
}
