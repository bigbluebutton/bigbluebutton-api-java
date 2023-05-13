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

import java.util.HashMap;
import java.util.Map;

import org.bigbluebutton.api.ApiParams;
import org.bigbluebutton.api.parameters.CreateMeetingParameters;
import org.junit.jupiter.api.BeforeEach;

import com.github.javafaker.Faker;

public class BigBlueButtonTestCase {

    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
    }

    protected CreateMeetingParameters generateCreateMeetingParams() {
        Map<String, Object>     mockCreate   = mockCreateMeetingParams();
        CreateMeetingParameters createParams = new CreateMeetingParameters((String) mockCreate.get(ApiParams.NAME),
                (String) mockCreate.get(ApiParams.MEETING_ID)).setDuration(faker.number().numberBetween(0, 10))
                .setRecord(faker.bool().bool());
        return createParams;

    }

    protected Map<String, Object> mockCreateMeetingParams() {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(ApiParams.NAME, faker.educator().course());
        paramsMap.put(ApiParams.MEETING_ID, faker.code().isbn10());
        return paramsMap;
    }
}
