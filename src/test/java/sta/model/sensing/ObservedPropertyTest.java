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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link ObservedProperty} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("ObservedProperty")
public class ObservedPropertyTest extends SensingEntityTester<ObservedProperty> {

    protected ObservedPropertyTest() {
        super(() -> {
            try {
                return ObservedProperty.builder()
                        .id(new Integer(1))
                        .selfLink(new URL("http://example.org/v1.0/ObservedProperties(1)"))
                        .datastreamsNavigationLink("ObservedProperties(1)/Datastreams")
                        .name("DewPoint Temperature")
                        .description("The dewpoint temperature is the temperature to which the air must be cooled," +
                                " at constant pressure, for dew to form. As the grass and other objects near the ground cool to the dewpoint," +
                                " some of the water vapor in the atmosphere condenses into liquid water on the objects.")
                        .definition(new URI("http://dbpedia.org/page/Dew_point"))
                        .build();
            } catch (MalformedURLException | URISyntaxException e) {
                throw new IllegalStateException("Unable to create the ObservedProperty reference test instance");
            }
        });
    }

    @Test
    @DisplayName("ObservedProperty deserialization")
    public void testObservedPropertyDeserialization() throws IOException {
        final ObservedProperty actual = getObjectMapper().readValue(ResourcesUtils.fromResources("observedProperty.json", getClass()), ObservedProperty.class);
        assertEquals(getReferenceSensingEntity(), actual, "An Observation can be deserialized");
    }

    @Test
    @DisplayName("ObservedProperty serialization")
    public void testObservedPropertySerialization() throws IOException {
        final ObservedProperty actual = getObjectMapper().readValue(getObjectMapper().writeValueAsString(getReferenceSensingEntity()), ObservedProperty.class);
        assertEquals(getReferenceSensingEntity(), actual, "An Observation can be serialized");
    }

}
