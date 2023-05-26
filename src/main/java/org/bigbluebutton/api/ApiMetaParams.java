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

import java.util.regex.Pattern;

public class ApiMetaParams {

    // Meta prefix
    public static String META_PREFIX = "meta_";

    // Official meta regex pattern
    public static final Pattern META_VAR_PATTERN = Pattern.compile("meta_[a-zA-Z][a-zA-Z0-9-]*$");

    // Predefined metas
    public static String END_CALLBACK_URL           = "endCallbackUrl";
    public static String BBB_RECORDING_READY_URL    = "bbb-recording-ready-url";
    public static String CANVAS_RECORDING_READY_URL = "canvas-recording-ready-url";
    public static String BBB_ANONYMIZE_CHAT         = "bbb-anonymize-chat";
    public static String BBB_ANONYMIZE_MODERATORS   = "bbb-anonymize-moderators";
    public static String HACK_RECORD_VIEWER_VIDEO   = "hack-record-viewer-video";

    private ApiMetaParams() {
        throw new IllegalStateException("ApiMetaParams is a utility class. Instanciation is forbidden.");
    }
}
