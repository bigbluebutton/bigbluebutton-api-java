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

import org.bigbluebutton.api.deserializers.PutRecordingTextTrackResponseDeserliazer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;

@JsonDeserialize(using = PutRecordingTextTrackResponseDeserliazer.class)
public class PutRecordingTextTrackResponse extends BaseResponse {
    @Getter
    protected String recordId;

    public static PutRecordingTextTrackResponse fromPutRecordingTextTrackResponse(JsonParser parser)
            throws IOException {
        PutRecordingTextTrackResponse response     = new PutRecordingTextTrackResponse();
        JsonNode                      rootNode     = parser.getCodec().readTree(parser);
        JsonNode                      responseNode = rootNode.get("response");

        if (responseNode != null) {
            response.messageKey = responseNode.get("messageKey").asText();
            response.message    = responseNode.get("message").asText();
            response.recordId   = responseNode.get("recordId").asText();
            response.returnCode = responseNode.get("returncode").asText();
        }

        return response;
    }
}
