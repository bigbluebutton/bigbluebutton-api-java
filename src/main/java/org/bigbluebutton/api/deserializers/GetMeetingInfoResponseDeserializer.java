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

package org.bigbluebutton.api.deserializers;

import java.io.IOException;

import org.bigbluebutton.api.responses.GetMeetingInfoResponse;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class GetMeetingInfoResponseDeserializer extends StdDeserializer<GetMeetingInfoResponse> {

    private static final long serialVersionUID = 5655122233138225747L;

    public GetMeetingInfoResponseDeserializer() {
        this(null);
    }

    public GetMeetingInfoResponseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GetMeetingInfoResponse deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        GetMeetingInfoResponse response = new GetMeetingInfoResponse(parser);

        return response;
    }
}