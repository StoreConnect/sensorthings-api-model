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
import sta.model.sensing.ext.TMPeriod;
import sta.utils.ResourcesUtils;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link TMPeriod} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("TMPeriodDeserializer")
public class TMPeriodDeserializerTest extends WithObjectMapper {

    @Test
    @DisplayName("TMPeriod deserialization with valid period")
    public void testDeserializationWithValidPeriod() throws IOException {
        final TMPeriod tmPeriod = getObjectMapper().readValue(ResourcesUtils.fromResources("validPeriod.json", getClass()), TMPeriod.class);
        assertEquals(Interval.parse("2018-03-27T12:00:00Z/2018-03-27T17:00:00Z"), tmPeriod.getPeriod(), "Interval value must be conformed to input");
    }

    @Test
    @DisplayName("TMPeriod deserialization with invalid period")
    public void testDeserializationWithInvalidPeriod() {
        assertThrows(DateTimeParseException.class, () -> getObjectMapper().readValue(ResourcesUtils.fromResources("invalidPeriod.json", TMPeriodDeserializerTest.this.getClass()), TMPeriod.class));
    }

}
