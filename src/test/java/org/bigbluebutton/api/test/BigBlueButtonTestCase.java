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

package org.bigbluebutton.api.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.bigbluebutton.api.ApiParams;
import org.bigbluebutton.api.BigBlueButtonAPI;
import org.bigbluebutton.api.enums.GuestPolicy;
import org.bigbluebutton.api.enums.MeetingLayout;
import org.bigbluebutton.api.enums.Role;
import org.bigbluebutton.api.parameters.CreateMeetingParameters;
import org.bigbluebutton.api.parameters.EndMeetingParameters;
import org.bigbluebutton.api.parameters.JoinMeetingParameters;
import org.bigbluebutton.api.responses.CreateMeetingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.javafaker.Faker;

public class BigBlueButtonTestCase extends BaseTestCase {

    protected Faker faker;

    @BeforeEach
    public void setUp() {
        super.setUp();
        faker = new Faker();
    }

    protected CreateMeetingResponse realMeeting()
            throws JsonMappingException, JsonProcessingException, MalformedURLException, IOException,
            ParserConfigurationException, SAXException, InterruptedException, URISyntaxException, TransformerException {
        CreateMeetingParameters createMeetingParms = generateCreateMeetingParams();

        BigBlueButtonAPI bbbApi = new BigBlueButtonAPI();

        CreateMeetingResponse response = bbbApi.createMeeting(createMeetingParms);
        return response;
    }

    protected CreateMeetingParameters generateCreateMeetingParams() {
        Map<String, Object>     mockCreate   = mockCreateMeetingParams();
        CreateMeetingParameters createParams = new CreateMeetingParameters((String) mockCreate.get(ApiParams.NAME),
                (String) mockCreate.get(ApiParams.MEETING_ID)).setAllowModsToEjectCameras(faker.bool().bool())
                .setAllowModsToUnmuteUsers(faker.bool().bool()).setAllowRequestsWithoutSession(faker.bool().bool())
                .setAllowStartStopRecording(faker.bool().bool()).setAutoStartRecording(faker.bool().bool())
                .setBannerColor(faker.color().hex()).setBannerText(faker.lorem().sentence())
                .setBreakoutRoomsPrivateChatEnabled(faker.bool().bool()).setBreakoutRoomsRecord(faker.bool().bool())
                .setDialNumber(faker.phoneNumber().phoneNumber()).setDuration(faker.number().numberBetween(0, 10))
                .setEndWhenNoModerator(faker.bool().bool())
                .setEndWhenNoModeratorDelayInMinutes(faker.number().numberBetween(2, 5))
                .setFreeJoin(faker.bool().bool())
                .setGuestPolicy(GuestPolicy.values()[new Random().nextInt(GuestPolicy.values().length)])
                .setIsBreakout(faker.bool().bool())
                .setLearningDashboardCleanupDelayInMinutes(faker.number().numberBetween(0, 120))
                .setLockSettingsDisableCam(faker.bool().bool()).setLockSettingsDisableMic(faker.bool().bool())
                .setLockSettingsDisableNote(faker.bool().bool()).setLockSettingsDisablePrivateChat(faker.bool().bool())
                .setLockSettingsDisablePublicChat(faker.bool().bool())
                .setLockSettingsHideViewersCursor(faker.bool().bool()).setLockSettingsLockOnJoin(faker.bool().bool())
                .setLockSettingsLockOnJoinConfigurable(faker.bool().bool()).setLogo(URI.create(faker.internet().url()))
                .setMaxParticipants(faker.number().numberBetween(0, 200))
                .setMeetingCameraCap(faker.number().numberBetween(8, 20))
                .setMeetingExpireIfNoUserJoinedInMinutes(faker.number().numberBetween(0, 10))
                .setMeetingExpireWhenLastUserLeftInMinutes(faker.number().numberBetween(2, 8))
                .setMeetingKeepEvents(faker.bool().bool())
                .setMeetingLayout(MeetingLayout.values()[new Random().nextInt(MeetingLayout.values().length)])
                .setModeratorOnlyMessage(faker.lorem().paragraph()).setMuteOnStart(faker.bool().bool())
                .setNotifyRecordingIsOn(faker.bool().bool())
                .setPresentationUploadExternalDescription(faker.lorem().sentence())
                .setPresentationUploadExternalUrl(URI.create(faker.internet().url()))
                .setPreUploadedPresentationOverrideDefault(faker.bool().bool()).setRecord(faker.bool().bool())
                .setSequence(faker.number().numberBetween(1, 5)).setUserCameraCap(faker.number().numberBetween(3, 5))
                .setVoiceBridge(faker.phoneNumber().extension()).setWebcamsOnlyForModeratorBoolean(faker.bool().bool())
                .setWelcome(faker.lorem().paragraph())
                .addMeta(faker.country().countryCode3(), faker.country().capital());
        return createParams;
    }

    protected EndMeetingParameters generateEndMeetingParams() {
        Map<String, Object>  mockEnd   = mockEndMeetingParams();
        EndMeetingParameters endParams = new EndMeetingParameters((String) mockEnd.get(ApiParams.MEETING_ID));
        return endParams;
    }

    protected JoinMeetingParameters generateJoinMeetingParams() {
        Map<String, Object>   mockJoin   = mockJoinMeetingParams();
        JoinMeetingParameters joinParams = new JoinMeetingParameters((String) mockJoin.get(ApiParams.FULL_NAME),
                (String) mockJoin.get(ApiParams.MEETING_ID), (Role) mockJoin.get(ApiParams.ROLE));
        return joinParams;
    }

    protected Map<String, Object> mockCreateMeetingParams() {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(ApiParams.NAME, faker.educator().course());
        paramsMap.put(ApiParams.MEETING_ID, faker.code().isbn10());
        return paramsMap;
    }

    protected Map<String, Object> mockEndMeetingParams() {
        Map<String, Object> paramsMap = new HashMap<>();

        paramsMap.put(ApiParams.MEETING_ID, faker.code().isbn10());
        return paramsMap;
    }

    protected Map<String, Object> mockJoinMeetingParams() {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(ApiParams.FULL_NAME, faker.name().fullName());
        paramsMap.put(ApiParams.ROLE, Role.values()[new Random().nextInt(Role.values().length)]);
        paramsMap.put(ApiParams.MEETING_ID, faker.code().isbn10());
        return paramsMap;
    }

}
