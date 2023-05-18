package org.bigbluebutton.api.responses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.bigbluebutton.api.deserializers.GetMeetingInfoResponseDeserializer;
import org.bigbluebutton.api.test.XMLResponseTestCase;
import org.bigbluebutton.api.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class GetMeetingInfoResponseTest extends XMLResponseTestCase {

    @BeforeEach
    public void setUp() {
        xmlResponseFile = "fixtures/get_meeting_info.xml";

        super.setUp();
    }

    @Test
    @DisplayName("Get meeting info response content")
    void testGetMeetingInfoResponseContent() throws StreamReadException, DatabindException, IOException {
        xmlMapper.registerModule(new SimpleModule().addDeserializer(GetMeetingInfoResponse.class,
                new GetMeetingInfoResponseDeserializer()));
        GetMeetingInfoResponse getMeetingInfoResponse = xmlMapper.readValue(xmlInput, GetMeetingInfoResponse.class);
        assertEquals(getMeetingInfoResponse.getReturnCode(), APIReturnCode.SUCCESS.getReturnCode());
        assertEquals(getMeetingInfoResponse.getMeeting().getMeetingId(), "117b12ae2656972d330b6bad58878541-28-15");
        assertEquals(getMeetingInfoResponse.getMeeting().getMeetingName(),
                "Mock meeting for testing getMeetingInfo API method");
        assertEquals(getMeetingInfoResponse.getMeeting().getCreateDate().format(DateTimeUtil.TZ_DATE_FORMATTER),
                "Fri Jan 20 04:56:59 CET 2023");
        assertEquals(getMeetingInfoResponse.getMeeting().getCreateTime().getEpochSecond(), 1453206317376L);
        assertEquals(getMeetingInfoResponse.getMeeting().getVoiceBridge(), "70100");
        assertEquals(getMeetingInfoResponse.getMeeting().getDialNumber(), "613-555-1234");
        assertTrue(getMeetingInfoResponse.getMeeting().getRunning());
        assertEquals(getMeetingInfoResponse.getMeeting().getDuration(), 20);
        assertTrue(getMeetingInfoResponse.getMeeting().getHasUserJoined());
        assertTrue(getMeetingInfoResponse.getMeeting().getRecording());
        assertFalse(getMeetingInfoResponse.getMeeting().getHasBeenForciblyEnded());
        assertEquals(getMeetingInfoResponse.getMeeting().getStartTime().getEpochSecond(), 1453206317380L);
        assertEquals(getMeetingInfoResponse.getMeeting().getEndTime().getEpochSecond(), 1453206325002L);
        assertEquals(getMeetingInfoResponse.getMeeting().getParticipantCount(), 2);
        assertEquals(getMeetingInfoResponse.getMeeting().getListenerCount(), 1);
        assertEquals(getMeetingInfoResponse.getMeeting().getVoiceParticipantCount(), 2);
        assertEquals(getMeetingInfoResponse.getMeeting().getVideoCount(), 1);
        assertEquals(getMeetingInfoResponse.getMeeting().getModeratorCount(), 2);
        assertTrue(getMeetingInfoResponse.getMeeting().getIsBreakout());
        assertEquals(getMeetingInfoResponse.getMeeting().getMaxUsers(), 20);
        assertEquals(getMeetingInfoResponse.getMeeting().getSequence(), 1);
        assertEquals(getMeetingInfoResponse.getMeeting().getParentMeetingId(),
                "b97b512f2c92c0ffe7a3476152525807daa1c676-1524213151782");
    }
}
