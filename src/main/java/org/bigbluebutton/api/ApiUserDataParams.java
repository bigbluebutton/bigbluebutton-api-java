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

// https://github.com/bigbluebutton/bigbluebutton/blob/develop/bigbluebutton-html5/imports/api/users-settings/server/methods/addUserSettings.js
// https://github.com/bigbluebutton/bigbluebutton/blob/develop/bigbluebutton-tests/puppeteer/customparameters/constants.js
public class ApiUserDataParams {

    // Meta prefix
    public static String USERDATA_PREFIX = "userdata-";

    // Predefined metas
    public static String BBB_ASK_FOR_FEEDBACK_ON_LOGOUT               = "bbb_ask_for_feedback_on_logout";
    public static String BBB_OVERRIDE_DEFAULT_LOCALE                  = "bbb_override_default_locale";
    public static String BBB_AUTO_JOIN_AUDIO                          = "bbb_auto_join_audio";
    public static String BBB_AUTO_SHARE_WEBCAM                        = "bbb_auto_share_webcam";
    public static String BBB_PREFERRED_CAMERA_PROFILE                 = "bbb_preferred_camera_profile";
    public static String BBB_CLIENT_TITLE                             = "bbb_client_title";
    public static String BBB_CUSTOM_STYLE                             = "bbb_custom_style";
    public static String BBB_CUSTOM_STYLE_URL                         = "bbb_custom_style_url";
    public static String BBB_SKIP_CHECK_AUDIO_ON_FIRST_JOIN           = "bbb_skip_check_audio_on_first_join";
    public static String BBB_DISPLAY_BRANDING_AREA                    = "bbb_display_branding_area";
    public static String BBB_ENABLE_VIDEO                             = "bbb_enable_video";
    public static String BBB_RECORD_VIDEO                             = "bbb_record_video";
    public static String BBB_SKIP_VIDEO_PREVIEW                       = "bbb_skip_video_preview";
    public static String BBB_SKIP_VIDEO_PREVIEW_ON_FIRST_JOIN         = "bbb_skip_video_preview_on_first_join";
    public static String BBB_MIRROR_OWN_WEBCAM                        = "bbb_mirror_own_webcam";
    public static String BBB_FORCE_RESTORE_PRESENTATION_ON_NEW_EVENTS = "bbb_force_restore_presentation_on_new_events";
    public static String BBB_FORCE_LISTEN_ONLY                        = "bbb_force_listen_only";
    public static String BBB_HIDE_PRESENTATION                        = "bbb_hide_presentation";
    public static String BBB_LISTEN_ONLY_MODE                         = "bbb_listen_only_mode";
    public static String BBB_MULTI_USER_PEN_ONLY                      = "bbb_multi_user_pen_only";
    public static String BBB_MULTI_USER_TOOLS                         = "bbb_multi_user_tools";
    public static String BBB_PRESENTER_TOOLS                          = "bbb_presenter_tools";
    public static String BBB_SHORTCUTS                                = "bbb_shortcuts";
    public static String BBB_SKIP_CHECK_AUDIO                         = "bbb_skip_check_audio";
    public static String BBB_HIDE_PRESENTATION_ON_JOIN                = "bbb_hide_presentation_on_join";
    public static String BBB_SHOW_PARTICIPANTS_ON_LOGIN               = "bbb_show_participants_on_login";
    public static String BBB_HIDE_ACTIONS_BAR                         = "bbb_hide_actions_bar";
    public static String BBB_HIDE_NAV_BAR                             = "bbb_hide_nav_bar";
    public static String BBB_CHANGE_LAYOUT                            = "bbb_change_layout";

    private ApiUserDataParams() {
        throw new IllegalStateException("ApiUserDataParams is a utility class. Instanciation is forbidden.");
    }
}
