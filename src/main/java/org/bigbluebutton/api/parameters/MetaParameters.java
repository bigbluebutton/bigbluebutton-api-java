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

import java.util.HashMap;
import java.util.List;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.bigbluebutton.api.ApiMetaParams;

public abstract class MetaParameters extends BaseParameters {

    protected HashMap<String, String> metas;

    public MetaParameters() {
        metas = new HashMap<>();
    }

    public String getMeta(String name) {
        return metas.get(name);
    }

    public BaseParameters addMeta(String key, String value) {
        metas.put(key, value);
        return this;
    }

    protected void buildHTTPMeta(List<NameValuePair> params) {
        metas.forEach((key, value) -> params.add(new BasicNameValuePair(ApiMetaParams.META_PREFIX + key, value)));
    }
}
