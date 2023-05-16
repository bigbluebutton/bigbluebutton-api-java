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

package org.bigbluebutton.api.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtil {
    public static final DateTimeFormatter TZ_DATE_FORMATTER = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss z yyyy");

    private DateTimeUtil() {
        throw new IllegalStateException("DateTimeUtil is a utility class. Instanciation is forbidden.");
    }

    public static ZonedDateTime parseBigBlueButtonDate(String dateString) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, TZ_DATE_FORMATTER);
        String[]      parts         = dateString.split(" ");
        return ZonedDateTime.of(localDateTime, ZoneId.of(parts[parts.length - 2]));
    }
}
