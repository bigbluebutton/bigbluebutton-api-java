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

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class InsertDocumentParameters extends BaseParameters implements Documentable<InsertDocumentParameters> {

    private DocumentableTrait documentableTrait = new DocumentableTrait();

    @Getter
    protected String meetingId;

    public InsertDocumentParameters(String meetingId) {
        this.meetingId = meetingId;
    }

    public List<NameValuePair> getQueryParms() throws UnsupportedEncodingException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        addStringValue(params, ApiParams.MEETING_ID, getMeetingId());
        return params;
    }

    @Override
    public Map<String, String> getPresentations() {
        return documentableTrait.getPresentations();
    }

    @Override
    public InsertDocumentParameters addPresentation(String name, URI url) {
        documentableTrait.addPresentation(name, url);
        return this;
    }

    @Override
    public InsertDocumentParameters addPresentation(String name, File file) throws IOException {
        documentableTrait.addPresentation(name, file);
        return this;
    }

    @Override
    public String getPresentationsAsXML() throws ParserConfigurationException, TransformerException {
        return documentableTrait.getPresentationsAsXML();
    }
}
