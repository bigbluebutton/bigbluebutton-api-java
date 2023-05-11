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

package org.bigbluebutton.api;

import org.bigbluebutton.api.responses.ApiVersionResponse;

import lombok.Getter;
import lombok.Setter;

public class BigBlueButtonAPI {

    @Getter
    @Setter
    protected String securitySalt;

    @Getter
    @Setter
    protected String baseServerURL;

    public BigBlueButtonAPI() {
        this(System.getenv("BBB_SERVER_BASE_URL"), System.getenv("BBB_SECURITY_SALT"));
    }

    public BigBlueButtonAPI(String baseUrl, String securitySalt) {
        this.baseServerURL = baseUrl;
        this.securitySalt  = securitySalt;
    }

    public ApiVersionResponse getAPIVersion() {
        return new ApiVersionResponse();
    }
}
