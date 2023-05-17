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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bigbluebutton.api.data.CustomData;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class CustomDataDeserializer extends JsonDeserializer<List<CustomData>> {
    @Override
    public List<CustomData> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        List<CustomData> CustomDataEntries = new ArrayList<>();
        JsonNode         CustomDataNode    = jsonParser.getCodec().readTree(jsonParser);

        Iterator<String> fieldNames = CustomDataNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName  = fieldNames.next();
            String fieldValue = CustomDataNode.get(fieldName).asText();
            CustomDataEntries.add(new CustomData(fieldName, fieldValue));
        }

        return CustomDataEntries;
    }
}