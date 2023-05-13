package org.bigbluebutton.api.responses;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.bigbluebutton.api.test.ResponseTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ApiVersionResponseTest extends ResponseTestCase {

    @BeforeEach
    public void setUp() {
        xmlResponseFile = "fixtures/api_version.xml";

        super.setUp();
    }

    @Test
    @DisplayName("API version response content")
    void testApiVersionResponseContent() throws StreamReadException, DatabindException, IOException {
        XmlMapper          xmlMapper          = new XmlMapper();
        ApiVersionResponse apiVersionResponse = xmlMapper.readValue(xmlInput, ApiVersionResponse.class);
        assertEquals(apiVersionResponse.getReturnCode(), APIReturnCode.SUCCESS.getReturnCode());
    }
}
