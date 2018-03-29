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


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import sta.model.sensing.ext.TMInstant;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * {@link TMInstant} {@link JsonDeserializer}
 *
 * @author Aurelien Bourdon
 */
public class TMInstantDeserializer extends JsonDeserializer<TMInstant> {

    /**
     * Create a new {@link TMInstant} instance based on the given text
     *
     * @param text the text to parse
     * @return a new {@link TMInstant} instance based on the given text
     * @throws java.time.format.DateTimeParseException if text cannot be parsed as a {@link TMInstant}
     */
    public static TMInstant parse(final String text) {
        return TMInstant.builder().instant(OffsetDateTime.parse(text)).build();
    }

    @Override
    public TMInstant deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        final String nodeValue = node.textValue();
        Objects.requireNonNull(nodeValue, "TMInstant value cannot be null");
        return parse(nodeValue);
    }

}
