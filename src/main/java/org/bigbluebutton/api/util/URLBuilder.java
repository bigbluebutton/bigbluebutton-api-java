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

package org.bigbluebutton.api.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hc.core5.net.URIBuilder;
import org.bigbluebutton.api.ApiMethod;

import lombok.Getter;

public class URLBuilder {

    @Getter
    private URI baseUri;

    private final String apiPrefix = "api";

    @Getter
    private String sharedSecret;

    public URLBuilder(String uri, String sharedSecret) {
        this(URI.create(uri), sharedSecret);
    }

    public URLBuilder(URI uri, String sharedSecret) {
        this.baseUri      = uri;
        this.sharedSecret = sharedSecret;
    }

    public URI buildUrl(ApiMethod apiMethod, String params) throws URISyntaxException {
        return new URIBuilder(baseUri + apiPrefix + apiMethod.getName()).build();
    }
}
