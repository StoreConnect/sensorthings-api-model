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
import sta.model.WithObjectMapper;
import sta.model.sensing.ext.TMInstant;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link TMInstantSerializer} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("TMInstantSerializer")
public class TMInstantSerializerTest extends WithObjectMapper {

    @Test
    @DisplayName("TMInstant serialization with valid instant")
    public void testTMInstantSerializationWithValidInstant() throws JsonProcessingException {
        final String instant = "2018-03-27T12:00:10Z";
        final String actual = getObjectMapper().writeValueAsString(TMInstant.builder()
                .instant(OffsetDateTime.parse(instant))
                .build());
        assertEquals("\"" + instant + "\"", actual, "TMInstant can be serialized with valid instant");
    }

    @Test
    @DisplayName("TMInstant serialization with invalid instant")
    public void testTMInstantSerializationWithInvalidInstant() throws JsonProcessingException {
        final String expected = "wrong";
        assertThrows(
                DateTimeParseException.class,
                () -> getObjectMapper().writeValueAsString(TMInstant.builder().instant(OffsetDateTime.parse(expected)).build()),
                "TMInstant cannot be serialized with invalid instant"
        );
    }

}
