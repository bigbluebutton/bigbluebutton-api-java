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
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.NameValuePair;
import org.bigbluebutton.api.ApiParams;
import org.bigbluebutton.api.enums.MeetingLayout;
import org.bigbluebutton.api.enums.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class JoinMeetingParameters extends UserDataParameters {

    @Getter
    protected String fullName;

    @Getter
    protected String meetingId;

    @Getter
    protected Role role;

    @Getter
    @Setter
    protected String createTime;

    @Getter
    @Setter
    protected String userId;

    @Getter
    @Setter
    protected String webVoiceConf;

    @Getter
    @Setter
    protected MeetingLayout defaultLayout;

    @Getter
    @Setter
    protected String avatarURL;

    @Getter
    @Setter
    protected Boolean redirect;

    @Getter
    @Setter
    protected Boolean guest;

    @Getter
    @Setter
    protected Boolean excludeFromDashboard;

    public JoinMeetingParameters(String fullName, String meetingId, Role role) {
        this.fullName  = fullName;
        this.meetingId = meetingId;
        this.role      = role;
    }

    public List<NameValuePair> getQueryParms() throws UnsupportedEncodingException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        addStringValue(params, ApiParams.FULL_NAME, getFullName());
        addStringValue(params, ApiParams.MEETING_ID, getMeetingId());
        addStringValue(params, ApiParams.ROLE, getRole().getName());
        addStringValue(params, ApiParams.CREATE_TIME, getCreateTime());
        addStringValue(params, ApiParams.WEB_VOICE_CONF, getWebVoiceConf());
        if (getDefaultLayout() != null) {
            addStringValue(params, ApiParams.DEFAULT_LAYOUT, getDefaultLayout().getName());
        }
        addStringValue(params, ApiParams.AVATAR_URL, getAvatarURL());
        addBooleanValue(params, ApiParams.REDIRECT, getRedirect());
        addBooleanValue(params, ApiParams.EXCLUDE_FROM_DASHBOARD, getExcludeFromDashboard());

        this.buildHTTPUserData(params);

        return params;
    }
}
