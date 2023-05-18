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
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DocumentableTrait implements Documentable<DocumentableTrait> {

    private Map<String, String> presentations = new LinkedHashMap<>();

    @Override
    public Map<String, String> getPresentations() {
        return presentations;
    }

    @Override
    public DocumentableTrait addPresentation(String name, URI url) {
        presentations.put(name, url.toString());
        return this;
    }

    @Override
    public DocumentableTrait addPresentation(String name, File file) throws IOException {
        presentations.put(name, base64EncodeFile(file));
        return this;
    }

    @Override
    public String getPresentationsAsXML() throws ParserConfigurationException, TransformerException {
        if (presentations.isEmpty()) {
            return null;
        }

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder        docBuilder = docFactory.newDocumentBuilder();
        Document               doc        = docBuilder.newDocument();

        Element modulesElement = doc.createElement("modules");
        doc.appendChild(modulesElement);

        Element moduleElement = doc.createElement("module");
        moduleElement.setAttribute("name", "presentation");
        modulesElement.appendChild(moduleElement);

        for (Map.Entry<String, String> entry : presentations.entrySet()) {
            String nameOrUrl = entry.getKey();
            String content   = entry.getValue();

            Element documentElement = doc.createElement("document");

            if (isURL(nameOrUrl)) {
                documentElement.setAttribute("url", nameOrUrl);
                if (isBase64(content)) {
                    documentElement.setAttribute("filename", content);
                }
            } else {
                documentElement.setAttribute("name", nameOrUrl);
                documentElement.setTextContent(content);
            }

            moduleElement.appendChild(documentElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer        transformer        = transformerFactory.newTransformer();
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        transformer.transform(new DOMSource(doc), result);

        return writer.toString();
    }

    private boolean isURL(String str) {
        try {
            new URL(str);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    private boolean isBase64(String str) {
        byte[] decodedBytes  = Base64.getDecoder().decode(str);
        String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
        // Check if the decoded string contains valid characters for Base64 data
        return Base64.getEncoder().encodeToString(decodedString.getBytes(StandardCharsets.UTF_8)).equals(str);
    }

    private String base64EncodeFile(File file) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        return Base64.getEncoder().encodeToString(fileContent);
    }
}