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
package sta.model.sensing.ext;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sta.model.sensing.ext.deserialization.TMInstantDeserializer;
import sta.model.sensing.ext.serialization.TMInstantSerializer;

import java.time.OffsetDateTime;

/**
 * OGC SensorThings' <code>TM_Instant</code> type that wraps an ISO-8601 compliant {@link #instant} attribute
 *
 * @author Aurelien Bourdon
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = TMInstantSerializer.class)
@JsonDeserialize(using = TMInstantDeserializer.class)
public final class TMInstant extends TMObject {

    private OffsetDateTime instant;

}
