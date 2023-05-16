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

import java.io.IOException;

import org.bigbluebutton.api.data.Meeting;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;

@JacksonXmlRootElement(localName = "response")
public class GetMeetingInfoResponse extends BaseResponse {
    @Getter
    private Meeting meeting;

    public GetMeetingInfoResponse(JsonParser parser) throws IOException {
        ObjectCodec codec = parser.getCodec();
        JsonNode    node  = codec.readTree(parser);

        JsonNode returnCodeNode = node.get("returncode");
        if (returnCodeNode != null) {
            this.returnCode = returnCodeNode.asText();
        }

        JsonNode messageNode = node.get("message");
        if (messageNode != null) {
            this.message = messageNode.asText();
        }

        // Create the Meeting object and set it in the response
        Meeting meeting = new Meeting(node);
        this.meeting = meeting;
    }
}
