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
import sta.model.sensing.ext.TMObject;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * {@link TMObject} {@link JsonDeserializer}
 *
 * @author Aurelien Bourdon
 */
public class TMObjectDeserializer extends JsonDeserializer<TMObject> {

    @Override
    public TMObject deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        final String nodeValue = node.textValue();
        Objects.requireNonNull(nodeValue, "TMObject value cannot be null");
        try {
            return TMInstantDeserializer.parse(nodeValue);
        } catch (final DateTimeParseException noInstantException) {
            return TMPeriodDeserializer.parse(nodeValue);
        }
    }

}
