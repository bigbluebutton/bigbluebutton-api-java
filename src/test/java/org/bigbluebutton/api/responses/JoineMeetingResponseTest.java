package org.bigbluebutton.api.responses;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;

import org.bigbluebutton.api.test.ResponseTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JoineMeetingResponseTest extends ResponseTestCase {

    @BeforeEach
    public void setUp() {
        xmlResponseFile = "fixtures/join_meeting.xml";

        super.setUp();
    }

    @Test
    @DisplayName("Join meeting response content")
    void testJoinMeetingResponseContent() throws StreamReadException, DatabindException, IOException {
        XmlMapper           xmlMapper          = new XmlMapper();
        JoinMeetingResponse joinmeeingResponse = xmlMapper.readValue(xmlInput, JoinMeetingResponse.class);
        assertEquals(joinmeeingResponse.getReturnCode(), APIReturnCode.SUCCESS.getReturnCode());
        assertEquals(joinmeeingResponse.getMeetingId(), "bec8af173fae4d1f45d8ed6ea7c0b1ffb93af020-1464618265614");
        assertEquals(joinmeeingResponse.getUserId(), "ao6ehbtvbmhz");
        assertEquals(joinmeeingResponse.getAuthToken(), "huzbpgthac7s");
        assertEquals(joinmeeingResponse.getSessionToken(), "rbe7bbkjzx5mnoda");
        assertEquals(joinmeeingResponse.getGuestStatus(), "ALLOW");
        assertEquals(joinmeeingResponse.getUrl(), URI
                .create("https://bigblubutton-server.sample/client/BigBlueButton.html?sessionToken=BKepJSKk39jtwDC9"));
    }
}
