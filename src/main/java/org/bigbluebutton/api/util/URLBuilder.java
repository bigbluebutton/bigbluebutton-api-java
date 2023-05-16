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
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.net.URLEncodedUtils;
import org.bigbluebutton.api.ApiMethod;
import org.bigbluebutton.api.HashingAlgorithms;

import lombok.Getter;

public class URLBuilder {

    @Getter
    private URI baseUri;

    private final String apiPrefix = "api";

    @Getter
    private String sharedSecret;

    @Getter
    private String hashingAlgorithm;

    public URLBuilder(String uri, String sharedSecret, String hashingAlgorithm) {
        this(URI.create(uri), sharedSecret, hashingAlgorithm);
    }

    public URLBuilder(URI uri, String sharedSecret, String hashingAlgorithm) {
        this.baseUri          = uri;
        this.sharedSecret     = sharedSecret;
        this.hashingAlgorithm = hashingAlgorithm;
    }

    public URI buildUrl(ApiMethod apiMethod) throws URISyntaxException {
        return this.buildUrl(apiMethod, null);
    }

    public URI buildUrl(ApiMethod apiMethod, List<NameValuePair> params) throws URISyntaxException {
        URIBuilder builder  = new URIBuilder(baseUri + apiPrefix + "/" + apiMethod.getName()).addParameters(params);
        String     checksum = this.calculateChecksum(apiMethod, builder.getQueryParams());
        return builder.addParameter("checksum", checksum).build();
    }

    private String calculateChecksum(ApiMethod apiMethod, List<NameValuePair> queryParams) {
        switch (hashingAlgorithm) {
            case HashingAlgorithms.SHA_1:
                return DigestUtils.sha1Hex(apiMethod.getName()
                        + URLEncodedUtils.format(queryParams, StandardCharsets.UTF_8) + this.sharedSecret);
            case HashingAlgorithms.SHA_256:
                return DigestUtils.sha256Hex(apiMethod.getName()
                        + URLEncodedUtils.format(queryParams, StandardCharsets.UTF_8) + this.sharedSecret);
            case HashingAlgorithms.SHA_512:
                return DigestUtils.sha512Hex(apiMethod.getName()
                        + URLEncodedUtils.format(queryParams, StandardCharsets.UTF_8) + this.sharedSecret);
            case HashingAlgorithms.SHA_384:
                return DigestUtils.sha384Hex(apiMethod.getName()
                        + URLEncodedUtils.format(queryParams, StandardCharsets.UTF_8) + this.sharedSecret);
            default:
                throw new IllegalArgumentException("Unsupported hashing algorithm: " + hashingAlgorithm);
        }
    }
}
