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

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.hc.core5.http.NameValuePair;
import org.bigbluebutton.api.ApiParams;
import org.bigbluebutton.api.enums.GuestPolicy;
import org.bigbluebutton.api.enums.MeetingLayout;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class CreateMeetingParameters extends MetaParameters implements Documentable {

    private DocumentableTrait documentableTrait = new DocumentableTrait();

    @Getter
    protected String name;

    @Getter
    protected String meetingId;

    @Getter
    @Setter
    protected String welcome;

    @Getter
    @Setter
    protected String dialNumber;

    @Getter
    @Setter
    protected String voiceBridge;

    @Getter
    @Setter
    protected Integer maxParticipants;

    @Getter
    protected URI logoutURL;

    @Getter
    @Setter
    protected Boolean record;

    @Getter
    @Setter
    protected Integer duration;

    @Getter
    @Setter
    protected Boolean isBreakout;

    @Getter
    @Setter
    protected Integer sequence;

    @Getter
    @Setter
    protected Boolean freeJoin;

    @Getter
    @Setter
    protected Boolean breakoutRoomsPrivateChatEnabled;

    @Getter
    @Setter
    protected Boolean breakoutRoomsRecord;

    @Getter
    @Setter
    protected String moderatorOnlyMessage;

    @Getter
    @Setter
    protected Boolean autoStartRecording;

    @Getter
    @Setter
    protected Boolean allowStartStopRecording;

    @Getter
    @Setter
    protected Boolean webcamsOnlyForModeratorBoolean;

    @Getter
    @Setter
    protected String bannerText;

    @Getter
    @Setter
    protected String bannerColor;

    @Getter
    @Setter
    protected Boolean muteOnStart;

    @Getter
    @Setter
    protected Boolean allowModsToUnmuteUsers;

    @Getter
    @Setter
    protected Boolean lockSettingsDisableCam;

    @Getter
    @Setter
    protected Boolean lockSettingsDisableMic;

    @Getter
    @Setter
    protected Boolean lockSettingsDisablePrivateChat;

    @Getter
    @Setter
    protected Boolean lockSettingsDisablePublicChat;

    @Getter
    @Setter
    protected Boolean lockSettingsDisableNote;

    @Getter
    @Setter
    protected Boolean lockSettingsLockOnJoin;

    @Getter
    @Setter
    protected Boolean lockSettingsLockOnJoinConfigurable;

    @Getter
    @Setter
    protected Boolean lockSettingsHideViewersCursor;

    @Getter
    @Setter
    protected GuestPolicy guestPolicy;

    @Getter
    @Setter
    protected Boolean meetingKeepEvents;

    @Getter
    @Setter
    protected Boolean endWhenNoModerator;

    @Getter
    @Setter
    protected Integer endWhenNoModeratorDelayInMinutes;

    @Getter
    @Setter
    protected MeetingLayout meetingLayout;

    @Getter
    @Setter
    protected Integer learningDashboardCleanupDelayInMinutes;

    @Getter
    @Setter
    protected Boolean allowModsToEjectCameras;

    @Getter
    @Setter
    protected Boolean allowRequestsWithoutSession;

    @Getter
    @Setter
    protected Integer userCameraCap;

    @Getter
    @Setter
    protected Integer meetingCameraCap;

    @Getter
    @Setter
    protected Integer meetingExpireIfNoUserJoinedInMinutes;

    @Getter
    @Setter
    protected Integer meetingExpireWhenLastUserLeftInMinutes;

    @Getter
    @Setter
    protected URI logo;

    @Getter
    @Setter
    protected Boolean preUploadedPresentationOverrideDefault;

    @Getter
    @Setter
    protected Boolean notifyRecordingIsOn;

    @Getter
    @Setter
    protected URI presentationUploadExternalUrl;

    @Getter
    @Setter
    protected String presentationUploadExternalDescription;

    public CreateMeetingParameters(String name, String meetingId) {
        this.name      = name;
        this.meetingId = meetingId;
    }

    public CreateMeetingParameters addMeta(String key, String value) {
        metas.put(key, value);
        return this;
    }

    public List<NameValuePair> getQueryParms() throws UnsupportedEncodingException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        addStringValue(params, ApiParams.NAME, getName());
        addStringValue(params, ApiParams.MEETING_ID, getMeetingId());
        addStringValue(params, ApiParams.WELCOME, getWelcome());
        addStringValue(params, ApiParams.DIAL_NUMBER, getDialNumber());
        addStringValue(params, ApiParams.VOICE_BRIDGE, getVoiceBridge());
        addIntegerValue(params, ApiParams.MAX_PARTICIPANTS, getMaxParticipants());
        addUriValue(params, ApiParams.LOGOUT_URL, getLogoutURL());
        addBooleanValue(params, ApiParams.RECORD, getRecord());
        addIntegerValue(params, ApiParams.DURATION, getDuration());
        addBooleanValue(params, ApiParams.IS_BREAKOUT, getIsBreakout());
        addIntegerValue(params, ApiParams.SEQUENCE, getSequence());
        addBooleanValue(params, ApiParams.FREE_JOIN, getFreeJoin());
        addBooleanValue(params, ApiParams.BREAKOUT_ROOMS_PRIVATE_CHAT_ENABLED, getBreakoutRoomsPrivateChatEnabled());
        addBooleanValue(params, ApiParams.BREAKOUT_ROOMS_RECORD, getBreakoutRoomsRecord());
        addStringValue(params, ApiParams.MODERATOR_ONLY_MESSAGE, getModeratorOnlyMessage());
        addBooleanValue(params, ApiParams.AUTO_START_RECORDING, getAutoStartRecording());
        addBooleanValue(params, ApiParams.ALLOW_START_STOP_RECORDING, getAllowStartStopRecording());
        addBooleanValue(params, ApiParams.WEBCAMS_ONLY_FOR_MODERATOR, getWebcamsOnlyForModeratorBoolean());
        addStringValue(params, ApiParams.BANNER_TEXT, getBannerText());
        addStringValue(params, ApiParams.BANNER_COLOR, getBannerColor());
        addBooleanValue(params, ApiParams.MUTE_ON_START, getMuteOnStart());
        addBooleanValue(params, ApiParams.ALLOW_MODS_TO_UNMUTE_USERS, getAllowModsToUnmuteUsers());
        addBooleanValue(params, ApiParams.LOCK_SETTINGS_DISABLE_CAM, getLockSettingsDisableCam());
        addBooleanValue(params, ApiParams.LOCK_SETTINGS_DISABLE_MIC, getLockSettingsDisableMic());
        addBooleanValue(params, ApiParams.LOCK_SETTINGS_DISABLE_PRIVATE_CHAT, getLockSettingsDisablePrivateChat());
        addBooleanValue(params, ApiParams.LOCK_SETTINGS_DISABLE_PUBLIC_CHAT, getLockSettingsDisablePublicChat());
        addBooleanValue(params, ApiParams.LOCK_SETTINGS_DISABLE_NOTES, getLockSettingsDisableNote());
        addBooleanValue(params, ApiParams.LOCK_SETTINGS_LOCK_ON_JOIN, getLockSettingsLockOnJoin());
        addBooleanValue(params, ApiParams.LOCK_SETTINGS_LOCK_ON_JOIN_CONFIGURABLE,
                getLockSettingsLockOnJoinConfigurable());
        addBooleanValue(params, ApiParams.LOCK_SETTINGS_HIDE_VIEWERS_CURSOR, getLockSettingsHideViewersCursor());
        if (getGuestPolicy() != null) {
            addStringValue(params, ApiParams.GUEST_POLICY, getGuestPolicy().getName());
        }
        addBooleanValue(params, ApiParams.MEETING_KEEP_EVENTS, getMeetingKeepEvents());
        addBooleanValue(params, ApiParams.END_WHEN_NO_MODERATOR, getEndWhenNoModerator());
        addIntegerValue(params, ApiParams.END_WHEN_NO_MODERATOR_DELAY_IN_MINUTES,
                getEndWhenNoModeratorDelayInMinutes());
        if (getMeetingLayout() != null) {
            addStringValue(params, ApiParams.MEETING_LAYOUT, getMeetingLayout().getName());
        }
        addIntegerValue(params, ApiParams.LEARNING_DASHBOARD_CLEANUP_DELAY_IN_MINUTES,
                getLearningDashboardCleanupDelayInMinutes());
        addBooleanValue(params, ApiParams.ALLOW_MODS_TO_EJECT_CAMERAS, getAllowModsToEjectCameras());
        addBooleanValue(params, ApiParams.ALLOW_REQUESTS_WITHOUT_SESSION, getAllowRequestsWithoutSession());
        addIntegerValue(params, ApiParams.USER_CAMERA_CAP, getUserCameraCap());
        addIntegerValue(params, ApiParams.MEETING_CAMERA_CAP, getMeetingCameraCap());
        addIntegerValue(params, ApiParams.MEETING_EXPIRE_IF_NO_USER_JOINED_IN_MINUTES,
                getMeetingExpireIfNoUserJoinedInMinutes());
        addIntegerValue(params, ApiParams.MEETING_EXPIRE_WHEN_LAST_USER_LEFT_IN_MINUTES,
                getMeetingExpireWhenLastUserLeftInMinutes());
        addStringValue(params, ApiParams.LOGO, getLogo().toString());
        addBooleanValue(params, ApiParams.PRE_UPLOAD_PRESENTATION_OVERRIDE_DEFAULT,
                getPreUploadedPresentationOverrideDefault());
        addBooleanValue(params, ApiParams.NOTIFY_RECORDING_IS_ON, getNotifyRecordingIsOn());
        addUriValue(params, ApiParams.PRESENTATION_UPLOAD_EXTERNAL_URL, getPresentationUploadExternalUrl());
        addStringValue(params, ApiParams.PRESENTATION_UPLOAD_EXTERNAL_DESCRIPTION,
                getPresentationUploadExternalDescription());
        // disabledFeatures
        // groups
        // pre-uploaded documents

        this.buildHTTPMeta(params);
        return params;
    }

    @Override
    public Map<String, String> getPresentations() {
        return documentableTrait.getPresentations();
    }

    @Override
    public void addPresentation(String name, URI url) {
        documentableTrait.addPresentation(name, url);
    }

    @Override
    public void addPresentation(String name, File file) throws IOException {
        documentableTrait.addPresentation(name, file);
    }

    @Override
    public String getPresentationsAsXML() throws ParserConfigurationException, TransformerException {
        return documentableTrait.getPresentationsAsXML();
    }
}
