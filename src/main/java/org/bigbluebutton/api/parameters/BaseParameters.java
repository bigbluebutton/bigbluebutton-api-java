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

package org.bigbluebutton.api.parameters;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;

public abstract class BaseParameters {

    protected void addStringValue(List<NameValuePair> params, String name, String value)
            throws UnsupportedEncodingException {
        if (value != null) {
            params.add(new BasicNameValuePair(name, this.urlEncode(value)));
        }
    }

    protected void addIntegerValue(List<NameValuePair> params, String name, Integer value)
            throws UnsupportedEncodingException {
        if (value != null) {
            params.add(new BasicNameValuePair(name, value.toString()));
        }
    }

    protected void addBooleanValue(List<NameValuePair> params, String name, Boolean value) {
        if (value != null) {
            params.add(new BasicNameValuePair(name, value.toString()));
        }
    }

    protected void addUriValue(List<NameValuePair> params, String name, URI value) {
        if (value != null) {
            params.add(new BasicNameValuePair(name, value.toString()));
        }
    }

    protected String urlEncode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
