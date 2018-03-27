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
import sta.model.WithObjectMapper;
import sta.model.sensing.ext.TMInstant;
import sta.utils.ResourcesUtils;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link TMInstant} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("TMInstantDeserializer")
public class TMInstantDeserializerTest extends WithObjectMapper {

    @Test
    @DisplayName("TMInstant deserialization with valid instant")
    public void testDeserializationWithValidInstant() throws IOException {
        final TMInstant tmInstant = getObjectMapper().readValue(ResourcesUtils.fromResources("validInstant.json", getClass()), TMInstant.class);
        assertEquals(OffsetDateTime.parse("2018-03-27T12:00:00Z"), tmInstant.getInstant(), "Instant value must be conformed to input");
    }

    @Test
    @DisplayName("TMInstant deserialization with invalid instant")
    public void testDeserializationWithInvalidInstant() {
        assertThrows(DateTimeParseException.class, () -> getObjectMapper().readValue(ResourcesUtils.fromResources("invalidInstant.json", TMInstantDeserializerTest.this.getClass()), TMInstant.class));
    }

}
