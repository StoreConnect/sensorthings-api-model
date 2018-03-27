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


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sta.model.sensing.ext.TMInstant;
import sta.model.sensing.ext.TMObject;
import sta.model.sensing.ext.TMPeriod;
import sta.utils.ResourcesUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link Observation} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("Observation")
public class ObservationTest extends SensingEntityTester<Observation> {

    protected ObservationTest() {
        super(() -> {
            try {
                return DoubleResultObservation.doubleResultObservationBuilder()
                        .id(new Integer(1))
                        .selfLink(new URL("http://example.org/v1.0/Locations(1)"))
                        .featureOfInterestNavigationLink("Observations(1)/FeatureOfInterest")
                        .datastreamNavigationLink("Observations(1)/Datastream")
                        .phenomenonTime(TMInstant.builder()
                                .instant(OffsetDateTime.parse("2014-12-31T11:59:59.00+08:00"))
                                .build()
                        )
                        .resultTime(TMInstant.builder()
                                .instant(OffsetDateTime.parse("2014-12-31T11:59:59.00+08:00"))
                                .build()
                        )
                        .result(70.4)
                        .build();
            } catch (final MalformedURLException e) {
                throw new IllegalStateException("Unable to create the Observation reference test instance");
            }
        });
    }

    @Test
    @DisplayName("Observation deserialization")
    public void testLocationDeserialization() throws IOException {
        final Observation actual = getObjectMapper().readValue(ResourcesUtils.fromResources("observation.json", getClass()), DoubleResultObservation.class);
        assertEquals(getReferenceSensingEntity(), actual, "An Observation can be deserialized");
    }

    @Test
    @DisplayName("Observation serialization")
    public void testLocationSerialization() throws IOException {
        final Observation actual = getObjectMapper().readValue(getObjectMapper().writeValueAsString(getReferenceSensingEntity()), DoubleResultObservation.class);
        assertEquals(getReferenceSensingEntity(), actual, "An Observation can be serialized");
    }

    /**
     * Specific {@link Observation} that uses a {@link Double} typed result
     */
    @Getter
    @Setter
    @NoArgsConstructor
    private static class DoubleResultObservation extends Observation {

        private Double result;

        // Necessary to be explicitly defined to be able to call parent constructor
        // (see https://github.com/rzwitserloot/lombok/issues/853 for more details)
        @Builder(builderMethodName = "doubleResultObservationBuilder") // To avoid non-allowed override of the parent #builder()
        private DoubleResultObservation(
                final Integer id,
                final URL selfLink,
                final TMObject phenomenonTime,
                final TMInstant resultTime,
                final Double result,
                final Collection<Object> resultQuality,
                final TMPeriod validTime,
                final Map<String, Object> parameters,
                final Datastream datastream,
                final String datastreamNavigationLink,
                final FeatureOfInterest featureOfInterest,
                final String featureOfInterestNavigationLink
        ) {
            setId(id);
            setSelfLink(selfLink);
            setPhenomenonTime(phenomenonTime);
            setResultTime(resultTime);
            setResultQuality(resultQuality);
            setValidTime(validTime);
            setParameters(parameters);
            setDatastream(datastream);
            setDatastreamNavigationLink(datastreamNavigationLink);
            setFeatureOfInterest(featureOfInterest);
            setFeatureOfInterestNavigationLink(featureOfInterestNavigationLink);
            this.result = result;
        }

    }

}
