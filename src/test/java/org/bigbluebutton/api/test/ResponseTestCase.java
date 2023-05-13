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

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public abstract class ResponseTestCase extends BaseTestCase {

    protected String xmlResponseFile;

    protected byte[] xmlInput;

    protected XmlMapper xmlMapper;

    @BeforeEach
    public void setUp() {
        super.setUp();
        try {
            xmlInput = Files.readAllBytes(Paths.get(
                    new File(getClass().getClassLoader().getResource(xmlResponseFile).getFile()).getAbsolutePath()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            fail("Failed loading fixutre: " + xmlResponseFile);
        }

        xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.findAndRegisterModules();
    }

}
