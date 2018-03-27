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
import sta.utils.ResourcesUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link Thing} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("Thing")
public class ThingTest extends SensingEntityTester<Thing> {

    protected ThingTest() {
        super(() -> {
            try {
                return Thing.builder()
                        .id(new Integer(1))
                        .selfLink(new URL("http://example.org/v1.0/Things(1)"))
                        .locationsNavigationLink("Things(1)/Locations")
                        .datastreamsNavigationLink("Things(1)/Datastreams")
                        .historicalLocationsNavigationLink("Things(1)/HistoricalLocations")
                        .name("Oven")
                        .description("This thing is an oven.")
                        // Lombok's @Singular cannot be used because of https://github.com/rzwitserloot/lombok/issues/1071
                        .properties(new HashMap<String, String>() {
                            {
                                put("owner", "Noah Liang");
                                put("color", "Black");
                            }
                        })
                        .build();
            } catch (final MalformedURLException e) {
                throw new IllegalStateException("Unable to create the Thing reference test instance");
            }
        });
    }

    @Test
    @DisplayName("Thing deserialization")
    public void testThingDeserialization() throws IOException {
        final Thing actual = getObjectMapper().readValue(ResourcesUtils.fromResources("thing.json", getClass()), Thing.class);
        assertEquals(getReferenceSensingEntity(), actual, "An Thing can be deserialized");
    }

    @Test
    @DisplayName("Thing serialization")
    public void testThingSerialization() throws IOException {
        final Thing actual = getObjectMapper().readValue(getObjectMapper().writeValueAsString(getReferenceSensingEntity()), Thing.class);
        assertEquals(getReferenceSensingEntity(), actual, "An Thing can be serialized");
    }

}
