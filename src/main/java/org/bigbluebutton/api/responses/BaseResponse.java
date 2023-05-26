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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;

@JacksonXmlRootElement(localName = "response")
@JsonRootName(value = "response")
public abstract class BaseResponse {
    @Getter
    @JacksonXmlProperty(localName = "returncode")
    @JsonProperty(value = "returncode", required = false)
    protected String returnCode;

    @Getter
    @JsonProperty("message")
    protected String message;

    @Getter
    @JsonProperty("messageKey")
    protected String messageKey;

    public Boolean success() {
        return returnCode.equals(APIReturnCode.SUCCESS.getReturnCode());
    }

    public Boolean failed() {
        return returnCode.equals(APIReturnCode.FAILED.getReturnCode());
    }
}
