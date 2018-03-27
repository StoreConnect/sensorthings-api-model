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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link MultiDatastream} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("MultiDatastream")
public class MultiDatastreamTest extends SensingEntityTester<MultiDatastream> {

    protected MultiDatastreamTest() {
        super(() -> {
            try {
                return MultiDatastream.builder()
                        .id(new Integer(1))
                        .selfLink(new URL("http://example.org/v1.0/MultiDatastreams(1)"))
                        .thingNavigationlink("MultiDatastreams(1)/Thing")
                        .sensorNavigationLink("MultiDatastreams(1)/Sensor")
                        .observedPropertyNavigationLink("MultiDatastreams(1)/ObservedProperty")
                        .observationsNavigationLink("MultiDatastreams(1)/Observations")
                        .name("temperature, RH")
                        .description("This is a\n" +
                                "  MultiDatastream from a simple weather station measuring air temperature and \n" +
                                "  relative humidity")
                        .unitOfMeasurements(Arrays.asList(
                                UnitOfMeasurement.builder()
                                        .name("degree Celsius")
                                        .definition("http://unitsofmeasure.org/ucum.html#para-30")
                                        .symbol("°C")
                                        .build(),
                                UnitOfMeasurement.builder()
                                        .name("percent")
                                        .definition("http://unitsofmeasure.org/ucum.html#para-29")
                                        .symbol("%")
                                        .build()
                        ))
                        .multiObservationDataTypes(Arrays.asList(
                                Datastream.ValueCode.OM_Measurement,
                                Datastream.ValueCode.OM_Measurement
                        ))
                        .observationType(MultiDatastream.ValueCode.OM_ComplexObservation)
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
    @DisplayName("MultiDatastream deserialization")
    public void testMultiDatastreamDeserialization() throws IOException {
        final MultiDatastream actual = getObjectMapper().readValue(ResourcesUtils.fromResources("multiDatastream.json", getClass()), MultiDatastream.class);
        assertEquals(getReferenceSensingEntity(), actual, "A MultiDatastream can be deserialized");
    }

    @Test
    @DisplayName("MultiDatastream serialization")
    public void testMultiDatastreamSerialization() throws IOException {
        final MultiDatastream actual = getObjectMapper().readValue(getObjectMapper().writeValueAsString(getReferenceSensingEntity()), MultiDatastream.class);
        assertEquals(getReferenceSensingEntity(), actual, "A MultiDatastream can be serialized");
    }

}
