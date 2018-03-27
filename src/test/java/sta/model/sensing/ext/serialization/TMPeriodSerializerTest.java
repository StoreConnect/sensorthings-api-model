/**
 * Copyright 2018 Inria Lille
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sta.model.sensing.ext.serialization;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Interval;
import sta.model.WithObjectMapper;
import sta.model.sensing.ext.TMPeriod;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link TMPeriodSerializer} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("TMPeriodSerializer")
public class TMPeriodSerializerTest extends WithObjectMapper {

    @Test
    @DisplayName("TMPeriod serialization with valid period")
    public void testTMPeriodSerializationWithValidInstant() throws JsonProcessingException {
        final String period = "2018-03-27T12:00:10Z/2018-03-27T17:00:20Z";
        final String actual = getObjectMapper().writeValueAsString(TMPeriod.builder()
                .period(Interval.parse(period))
                .build());
        assertEquals("\"" + period + "\"", actual, "TMPeriod can be serialized with valid period");
    }

    @Test
    @DisplayName("TMPeriod serialization with invalid period")
    public void testTMPeriodSerializationWithInvalidInstant() throws JsonProcessingException {
        final String expected = "wrong";
        assertThrows(
                DateTimeParseException.class,
                () -> getObjectMapper().writeValueAsString(TMPeriod.builder().period(Interval.parse(expected)).build()),
                "TMPeriod cannot be serialized with invalid period"
        );
    }

}
