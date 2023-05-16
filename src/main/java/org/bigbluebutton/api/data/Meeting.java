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

import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;

import org.bigbluebutton.api.responses.BaseResponse;
import org.bigbluebutton.api.responses.GetMeetingInfoResponse;
import org.bigbluebutton.api.util.DateTimeUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(value = { "attendeePW", "moderatorPW" })
public class Meeting extends BaseResponse {
    @Getter
    @Setter
    private String meetingName;

    @Getter
    private GetMeetingInfoResponse response;

    public Meeting(JsonNode node) throws IOException {
        JsonNode meetingIdNode = node.get("meetingID");
        if (meetingIdNode != null) {
            this.meetingId = meetingIdNode.asText();
        }

        JsonNode meetingNameNode = node.get("meetingName");
        if (meetingNameNode != null) {
            this.meetingName = meetingNameNode.asText();
        }

        JsonNode createTimeNode = node.get("createTime");
        if (createTimeNode != null) {
            this.createTime = Instant.ofEpochSecond(createTimeNode.asLong());
        }

        JsonNode createDateNode = node.get("createDate");
        if (createDateNode != null) {
            this.createDate = DateTimeUtil.parseBigBlueButtonDate(createDateNode.asText());
        }

        JsonNode voiceBridgeNode = node.get("voiceBridge");
        if (voiceBridgeNode != null) {
            this.voiceBridge = voiceBridgeNode.asText();
        }

        JsonNode dialNumberNode = node.get("dialNumber");
        if (dialNumberNode != null) {
            this.dialNumber = dialNumberNode.asText();
        }

        JsonNode runningNode = node.get("running");
        if (runningNode != null) {
            this.running = runningNode.asBoolean();
        }

        JsonNode durationNode = node.get("duration");
        if (durationNode != null) {
            this.duration = durationNode.asInt();
        }

        JsonNode hasUserJoinedNode = node.get("hasUserJoined");
        if (hasUserJoinedNode != null) {
            this.hasUserJoined = hasUserJoinedNode.asBoolean();
        }

        JsonNode recordingNode = node.get("recording");
        if (recordingNode != null) {
            this.recording = recordingNode.asBoolean();
        }

        JsonNode hasBeenForciblyEndedNode = node.get("hasBeenForciblyEnded");
        if (hasBeenForciblyEndedNode != null) {
            this.hasBeenForciblyEnded = hasBeenForciblyEndedNode.asBoolean();
        }

        JsonNode startTimeNode = node.get("startTime");
        if (startTimeNode != null) {
            this.startTime = Instant.ofEpochSecond(startTimeNode.asLong());
        }

        JsonNode endTimeNode = node.get("endTime");
        if (endTimeNode != null) {
            this.endTime = Instant.ofEpochSecond(endTimeNode.asLong());
        }

        JsonNode participantCountNode = node.get("participantCount");
        if (participantCountNode != null) {
            this.participantCount = participantCountNode.asInt();
        }

        JsonNode listenerCountNode = node.get("listenerCount");
        if (listenerCountNode != null) {
            this.listenerCount = listenerCountNode.asInt();
        }

        JsonNode voiceParticipantCountNode = node.get("voiceParticipantCount");
        if (voiceParticipantCountNode != null) {
            this.voiceParticipantCount = voiceParticipantCountNode.asInt();
        }

        JsonNode videoCount = node.get("videoCount");
        if (videoCount != null) {
            this.videoCount = videoCount.asInt();
        }

        JsonNode moderatorCountNode = node.get("moderatorCount");
        if (moderatorCountNode != null) {
            this.moderatorCount = moderatorCountNode.asInt();
        }

        JsonNode isBreakoutNode = node.get("isBreakout");
        if (isBreakoutNode != null) {
            this.isBreakout = isBreakoutNode.asBoolean();
        }

        JsonNode maxUsersNode = node.get("maxUsers");
        if (maxUsersNode != null) {
            this.maxUsers = maxUsersNode.asInt();
        }

        JsonNode parentMeetingIDNode = node.get("parentMeetingID");
        if (parentMeetingIDNode != null) {
            this.parentMeetingId = parentMeetingIDNode.asText();
        }

        JsonNode sequenceNode = node.get("sequence");
        if (sequenceNode != null) {
            this.sequence = sequenceNode.asInt();
        }
    }

    @Getter
    @JacksonXmlProperty(localName = "meetingID")
    private String meetingId;

    @Getter
    @JacksonXmlProperty(localName = "internalMeetingID")
    private String internalMeetingId;

    @Getter
    private Instant createTime;

    @Getter
    private ZonedDateTime createDate;

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
    private Instant startTime;

    @Getter
    private Instant endTime;

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

    @Getter
    @JacksonXmlProperty(localName = "parentMeetingID")
    private String parentMeetingId;

    @Getter
    private Integer sequence;
}
