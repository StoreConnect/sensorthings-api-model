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
package sta.model.sensing;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sta.model.sensing.ext.TMInstant;
import sta.utils.ResourcesUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link HistoricalLocation} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("HistoricalLocation")
public class HistoricalLocationTest extends SensingEntityTester<HistoricalLocation> {

    protected HistoricalLocationTest() {
        super(() -> {
            try {
                return HistoricalLocation.builder()
                        .id(new Integer(1))
                        .selfLink(new URL("http://example.org/v1.0/HistoricalLocations(1)"))
                        .locationsNavigationLink("HistoricalLocations(1)/Locations")
                        .thingNavigationLink("HistoricalLocations(1)/Thing")
                        .time(TMInstant.builder()
                                .instant(OffsetDateTime.parse("2015-01-25T12:00:00-07:00"))
                                .build()
                        )
                        .build();
            } catch (final MalformedURLException e) {
                throw new IllegalStateException("Unable to create the HistoricalLocation reference test instance");
            }
        });
    }

    @Test
    @DisplayName("HistoricalLocation deserialization")
    public void testHistoricalLocationDeserialization() throws IOException {
        final HistoricalLocation actual = getObjectMapper().readValue(ResourcesUtils.fromResources("historicalLocation.json", getClass()), HistoricalLocation.class);
        assertEquals(getReferenceSensingEntity(), actual, "An HistoricalLocation can be deserialized");
    }

    @Test
    @DisplayName("HistoricalLocation serialization")
    public void testHistoricalLocationSerialization() throws IOException {
        final HistoricalLocation actual = getObjectMapper().readValue(getObjectMapper().writeValueAsString(getReferenceSensingEntity()), HistoricalLocation.class);
        assertEquals(getReferenceSensingEntity(), actual, "An HistoricalLocation can be serialized");
    }

}
