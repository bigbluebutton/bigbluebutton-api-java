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

public class ApiParamsTest extends BaseTestCase {

    @Test
    @DisplayName("Test API parameters names")
    void apiMethodNamesShouldMatch() {
        assertEquals(ApiParams.ALLOW_MODS_TO_EJECT_CAMERAS.toString(), "allowModsToEjectCameras");
        assertEquals(ApiParams.ALLOW_MODS_TO_UNMUTE_USERS.toString(), "allowModsToUnmuteUsers");
        assertEquals(ApiParams.ALLOW_REQUESTS_WITHOUT_SESSION.toString(), "allowRequestsWithoutSession");
        assertEquals(ApiParams.ALLOW_START_STOP_RECORDING.toString(), "allowStartStopRecording");
        assertEquals(ApiParams.AUTO_START_RECORDING.toString(), "autoStartRecording");
        assertEquals(ApiParams.AVATAR_URL.toString(), "avatarURL");
        assertEquals(ApiParams.BANNER_COLOR.toString(), "bannerColor");
        assertEquals(ApiParams.BANNER_TEXT.toString(), "bannerText");
        assertEquals(ApiParams.BREAKOUT_ROOMS_CAPTURE_NOTES.toString(), "breakoutRoomsCaptureNotes");
        assertEquals(ApiParams.BREAKOUT_ROOMS_CAPTURE_NOTES_FILENAME.toString(), "breakoutRoomsCaptureNotesFilename");
        assertEquals(ApiParams.BREAKOUT_ROOMS_CAPTURE_SLIDES.toString(), "breakoutRoomsCaptureSlides");
        assertEquals(ApiParams.BREAKOUT_ROOMS_PRIVATE_CHAT_ENABLED.toString(), "breakoutRoomsPrivateChatEnabled");
        assertEquals(ApiParams.BREAKOUT_ROOMS_RECORD.toString(), "breakoutRoomsRecord");
        assertEquals(ApiParams.CALLBACK_URL.toString(), "callbackURL");
        assertEquals(ApiParams.CHECKSUM.toString(), "checksum");
        assertEquals(ApiParams.COPYRIGHT.toString(), "copyright");
        assertEquals(ApiParams.CREATE_TIME.toString(), "createTime");
        assertEquals(ApiParams.DEFAULT_LAYOUT.toString(), "defaultLayout");
        assertEquals(ApiParams.DEPRECATED_LOCK_SETTINGS_DISABLE_NOTES.toString(), "lockSettingsDisableNote");
        assertEquals(ApiParams.DIAL_NUMBER.toString(), "dialNumber");
        assertEquals(ApiParams.DISABLED_FEATURES.toString(), "disabledFeatures");
        assertEquals(ApiParams.DURATION.toString(), "duration");
        assertEquals(ApiParams.END_WHEN_NO_MODERATOR.toString(), "endWhenNoModerator");
        assertEquals(ApiParams.END_WHEN_NO_MODERATOR_DELAY_IN_MINUTES.toString(), "endWhenNoModeratorDelayInMinutes");
        assertEquals(ApiParams.EXCLUDE_FROM_DASHBOARD.toString(), "excludeFromDashboard");
        assertEquals(ApiParams.FREE_JOIN.toString(), "freeJoin");
        assertEquals(ApiParams.FULL_NAME.toString(), "fullName");
        assertEquals(ApiParams.GET_RAW.toString(), "getRaw");
        assertEquals(ApiParams.GROUPS.toString(), "groups");
        assertEquals(ApiParams.GUEST.toString(), "guest");
        assertEquals(ApiParams.GUEST_POLICY.toString(), "guestPolicy");
        assertEquals(ApiParams.HOOK_ID.toString(), "hookID");
        assertEquals(ApiParams.HTML5_INSTANCE_ID.toString(), "html5InstanceId");
        assertEquals(ApiParams.IS_BREAKOUT.toString(), "isBreakout");
        assertEquals(ApiParams.LEARNING_DASHBOARD_CLEANUP_DELAY_IN_MINUTES.toString(),
                "learningDashboardCleanupDelayInMinutes");
        assertEquals(ApiParams.LIMIT.toString(), "limit");
        assertEquals(ApiParams.LOCK_SETTINGS_DISABLE_CAM.toString(), "lockSettingsDisableCam");
        assertEquals(ApiParams.LOCK_SETTINGS_DISABLE_MIC.toString(), "lockSettingsDisableMic");
        assertEquals(ApiParams.LOCK_SETTINGS_DISABLE_NOTES.toString(), "lockSettingsDisableNotes");
        assertEquals(ApiParams.LOCK_SETTINGS_DISABLE_PRIVATE_CHAT.toString(), "lockSettingsDisablePrivateChat");
        assertEquals(ApiParams.LOCK_SETTINGS_DISABLE_PUBLIC_CHAT.toString(), "lockSettingsDisablePublicChat");
        assertEquals(ApiParams.LOGO.toString(), "logo");
        assertEquals(ApiParams.LOGOUT_TIMER.toString(), "logoutTimer");
        assertEquals(ApiParams.LOGOUT_URL.toString(), "logoutURL");
        assertEquals(ApiParams.MAX_PARTICIPANTS.toString(), "maxParticipants");
        assertEquals(ApiParams.MAX_PINNED_CAMERAS.toString(), "maxPinnedCameras");
        assertEquals(ApiParams.MEETING_CAMERA_CAP.toString(), "meetingCameraCap");
        assertEquals(ApiParams.MEETING_ENDED_CALLBACK_URL.toString(), "meetingEndedURL");
        assertEquals(ApiParams.MEETING_EXPIRE_IF_NO_USER_JOINED_IN_MINUTES.toString(),
                "meetingExpireIfNoUserJoinedInMinutes");
        assertEquals(ApiParams.MEETING_EXPIRE_WHEN_LAST_USER_LEFT_IN_MINUTES.toString(),
                "meetingExpireWhenLastUserLeftInMinutes");
        assertEquals(ApiParams.MEETING_ID.toString(), "meetingID");
        assertEquals(ApiParams.MEETING_KEEP_EVENTS.toString(), "meetingKeepEvents");
        assertEquals(ApiParams.MEETING_LAYOUT.toString(), "meetingLayout");
        assertEquals(ApiParams.META.toString(), "meta");
        assertEquals(ApiParams.MODERATOR_ONLY_MESSAGE.toString(), "moderatorOnlyMessage");
        assertEquals(ApiParams.MUTE_ON_START.toString(), "muteOnStart");
        assertEquals(ApiParams.NAME.toString(), "name");
        assertEquals(ApiParams.NOTIFY_RECORDING_IS_ON.toString(), "notifyRecordingIsOn");
        assertEquals(ApiParams.OFFSET.toString(), "offset");
        assertEquals(ApiParams.PARENT_MEETING_ID.toString(), "parentMeetingID");
        assertEquals(ApiParams.PRE_UPLOAD_PRESENTATION_OVERRIDE_DEFAULT.toString(),
                "preUploadedPresentationOverrideDefault");
        assertEquals(ApiParams.PRESENTATION_UPLOAD_EXTERNAL_DESCRIPTION.toString(),
                "presentationUploadExternalDescription");
        assertEquals(ApiParams.PRESENTATION_UPLOAD_EXTERNAL_URL.toString(), "presentationUploadExternalUrl");
        assertEquals(ApiParams.PUBLISH.toString(), "publish");
        assertEquals(ApiParams.RECORD.toString(), "record");
        assertEquals(ApiParams.RECORD_ID.toString(), "recordID");
        assertEquals(ApiParams.REDIRECT.toString(), "redirect");
        assertEquals(ApiParams.ROLE.toString(), "role");
        assertEquals(ApiParams.SEQUENCE.toString(), "sequence");
        assertEquals(ApiParams.USER_CAMERA_CAP.toString(), "userCameraCap");
        assertEquals(ApiParams.USER_ID.toString(), "userID");
        assertEquals(ApiParams.VOICE_BRIDGE.toString(), "voiceBridge");
        assertEquals(ApiParams.WEB_VOICE.toString(), "webVoice");
        assertEquals(ApiParams.WEB_VOICE_CONF.toString(), "webVoiceConf");
        assertEquals(ApiParams.WEBCAMS_ONLY_FOR_MODERATOR.toString(), "webcamsOnlyForModerator");
        assertEquals(ApiParams.WELCOME.toString(), "welcome");
    }
}
