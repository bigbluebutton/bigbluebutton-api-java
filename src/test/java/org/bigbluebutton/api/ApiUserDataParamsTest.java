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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.bigbluebutton.api.test.BaseTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiUserDataParamsTest extends BaseTestCase {

    @Test
    @DisplayName("Test API user data parameters names")
    void apiMethodNamesShouldMatch() {

        assertEquals(ApiUserDataParams.BBB_ASK_FOR_FEEDBACK_ON_LOGOUT, "bbb_ask_for_feedback_on_logout");
        assertEquals(ApiUserDataParams.BBB_OVERRIDE_DEFAULT_LOCALE, "bbb_override_default_locale");
        assertEquals(ApiUserDataParams.BBB_AUTO_JOIN_AUDIO, "bbb_auto_join_audio");
        assertEquals(ApiUserDataParams.BBB_AUTO_SHARE_WEBCAM, "bbb_auto_share_webcam");
        assertEquals(ApiUserDataParams.BBB_PREFERRED_CAMERA_PROFILE, "bbb_preferred_camera_profile");
        assertEquals(ApiUserDataParams.BBB_CLIENT_TITLE, "bbb_client_title");
        assertEquals(ApiUserDataParams.BBB_CUSTOM_STYLE, "bbb_custom_style");
        assertEquals(ApiUserDataParams.BBB_CUSTOM_STYLE_URL, "bbb_custom_style_url");
        assertEquals(ApiUserDataParams.BBB_SKIP_CHECK_AUDIO_ON_FIRST_JOIN, "bbb_skip_check_audio_on_first_join");
        assertEquals(ApiUserDataParams.BBB_DISPLAY_BRANDING_AREA, "bbb_display_branding_area");
        assertEquals(ApiUserDataParams.BBB_ENABLE_VIDEO, "bbb_enable_video");
        assertEquals(ApiUserDataParams.BBB_RECORD_VIDEO, "bbb_record_video");
        assertEquals(ApiUserDataParams.BBB_SKIP_VIDEO_PREVIEW, "bbb_skip_video_preview");
        assertEquals(ApiUserDataParams.BBB_SKIP_VIDEO_PREVIEW_ON_FIRST_JOIN, "bbb_skip_video_preview_on_first_join");
        assertEquals(ApiUserDataParams.BBB_MIRROR_OWN_WEBCAM, "bbb_mirror_own_webcam");
        assertEquals(ApiUserDataParams.BBB_FORCE_RESTORE_PRESENTATION_ON_NEW_EVENTS,
                "bbb_force_restore_presentation_on_new_events");
        assertEquals(ApiUserDataParams.BBB_FORCE_LISTEN_ONLY, "bbb_force_listen_only");
        assertEquals(ApiUserDataParams.BBB_HIDE_PRESENTATION, "bbb_hide_presentation");
        assertEquals(ApiUserDataParams.BBB_LISTEN_ONLY_MODE, "bbb_listen_only_mode");
        assertEquals(ApiUserDataParams.BBB_MULTI_USER_PEN_ONLY, "bbb_multi_user_pen_only");
        assertEquals(ApiUserDataParams.BBB_MULTI_USER_TOOLS, "bbb_multi_user_tools");
        assertEquals(ApiUserDataParams.BBB_PRESENTER_TOOLS, "bbb_presenter_tools");
        assertEquals(ApiUserDataParams.BBB_SHORTCUTS, "bbb_shortcuts");
        assertEquals(ApiUserDataParams.BBB_SKIP_CHECK_AUDIO, "bbb_skip_check_audio");
        assertEquals(ApiUserDataParams.BBB_HIDE_PRESENTATION_ON_JOIN, "bbb_hide_presentation_on_join");
        assertEquals(ApiUserDataParams.BBB_SHOW_PARTICIPANTS_ON_LOGIN, "bbb_show_participants_on_login");
        assertEquals(ApiUserDataParams.BBB_HIDE_ACTIONS_BAR, "bbb_hide_actions_bar");
        assertEquals(ApiUserDataParams.BBB_HIDE_NAV_BAR, "bbb_hide_nav_bar");
        assertEquals(ApiUserDataParams.BBB_CHANGE_LAYOUT, "bbb_change_layout");
    }
}
