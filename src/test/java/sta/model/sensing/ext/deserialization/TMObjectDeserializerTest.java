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
package sta.model.sensing.ext.deserialization;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Interval;
import sta.model.WithObjectMapper;
import sta.model.sensing.ext.TMInstant;
import sta.model.sensing.ext.TMObject;
import sta.model.sensing.ext.TMPeriod;
import sta.utils.ResourcesUtils;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link TMObject} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("TMObjectDeserializer")
public class TMObjectDeserializerTest extends WithObjectMapper {

    @Test
    @DisplayName("TMObject deserialization with valid instant")
    public void testDeserializationWithValidInstant() throws IOException {
        final TMObject tmObject = getObjectMapper().readValue(ResourcesUtils.fromResources("validInstant.json", getClass()), TMObject.class);
        assertAll("TMObject can be deserialized as a TMInstant",
                () -> assertTrue(tmObject instanceof TMInstant, "Deserialized object must be a TMInstant"),
                () -> assertEquals(OffsetDateTime.parse("2018-03-27T12:00:00Z"), ((TMInstant) tmObject).getInstant(), "Instant value must be conformed to input")
        );
    }

    @Test
    @DisplayName("TMObject deserialization with invalid instant")
    public void testDeserializationWithInvalidInstant() {
        assertThrows(DateTimeParseException.class, () -> getObjectMapper().readValue(ResourcesUtils.fromResources("invalidInstant.json", TMObjectDeserializerTest.this.getClass()), TMObject.class));
    }

    @Test
    @DisplayName("TMObject deserialization with valid period")
    public void testDeserializationWithValidPeriod() throws IOException {
        final TMObject tmObject = getObjectMapper().readValue(ResourcesUtils.fromResources("validPeriod.json", getClass()), TMObject.class);
        assertAll("TMObject can be deserialized as a TMPeriod",
                () -> assertTrue(tmObject instanceof TMPeriod, "Deserialized object must be a TMPeriod"),
                () -> assertEquals(Interval.parse("2018-03-27T12:00:00Z/2018-03-27T17:00:00Z"), ((TMPeriod) tmObject).getPeriod(), "Interval value must be conformed to input")
        );
    }

    @Test
    @DisplayName("TMObject deserialization with invalid period")
    public void testDeserializationWithInvalidPeriod() {
        assertThrows(DateTimeParseException.class, () -> getObjectMapper().readValue(ResourcesUtils.fromResources("invalidPeriod.json", TMObjectDeserializerTest.this.getClass()), TMObject.class));
    }

}
