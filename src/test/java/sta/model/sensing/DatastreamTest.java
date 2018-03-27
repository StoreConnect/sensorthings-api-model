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


import org.geojson.LngLatAlt;
import org.geojson.Polygon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Interval;
import sta.model.sensing.ext.TMPeriod;
import sta.model.sensing.ext.UnitOfMeasurement;
import sta.utils.ResourcesUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link Datastream} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("Datastream")
public class DatastreamTest extends SensingEntityTester<Datastream> {

    protected DatastreamTest() {
        super(() -> {
            try {
                return Datastream.builder()
                        .id(new Integer(1))
                        .selfLink(new URL("http://example.org/v1.0/Datastreams(1)"))
                        .thingNavigationlink("Datastreams(1)/Thing")
                        .sensorNavigationLink("Datastreams(1)/Sensor")
                        .observedPropertyNavigationLink("Datastreams(1)/ObservedProperty")
                        .observationsNavigationLink("Datastreams(1)/Observations")
                        .name("oven temperature")
                        .description("This is a datastream measuring the air temperature in an oven.")
                        .unitOfMeasurement(UnitOfMeasurement.builder()
                                .name("degree Celsius")
                                .definition("http://unitsofmeasure.org/ucum.html#para-30")
                                .symbol("°C")
                                .build())
                        .observationType(Datastream.ValueCode.OM_Measurement)
                        .observedArea(new Polygon(
                                new LngLatAlt(100, 0),
                                new LngLatAlt(101, 0),
                                new LngLatAlt(101, 1),
                                new LngLatAlt(100, 1),
                                new LngLatAlt(100, 0)
                        ))
                        .phenomenonTime(TMPeriod.builder()
                                .period(Interval.parse("2014-03-01T13:00:00Z/2015-05-11T15:30:00Z"))
                                .build()
                        )
                        .resultTime(TMPeriod.builder()
                                .period(Interval.parse("2014-03-01T13:00:00Z/2015-05-11T15:30:00Z"))
                                .build()
                        )
                        .build();
            } catch (final MalformedURLException e) {
                throw new IllegalStateException("Unable to create the Datastream reference test instance");
            }
        });
    }

    @Test
    @DisplayName("Datastream deserialization")
    public void testDatastreamDeserialization() throws IOException {
        final Datastream actual = getObjectMapper().readValue(ResourcesUtils.fromResources("datastream.json", getClass()), Datastream.class);
        assertEquals(getReferenceSensingEntity(), actual, "A Datastream can be deserialized");
    }

    @Test
    @DisplayName("Datastream serialization")
    public void testDatastreamSerialization() throws IOException {
        final Datastream actual = getObjectMapper().readValue(getObjectMapper().writeValueAsString(getReferenceSensingEntity()), Datastream.class);
        assertEquals(getReferenceSensingEntity(), actual, "A Datastream can be serialized");
    }

}
