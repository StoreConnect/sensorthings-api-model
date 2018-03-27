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
import org.geojson.Feature;
import org.geojson.LngLatAlt;
import org.geojson.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sta.utils.ResourcesUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link FeatureOfInterest} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("FeatureOfInterest")
public class FeatureOfInterestTest extends SensingEntityTester<FeatureOfInterest> {

    protected FeatureOfInterestTest() {
        super(() -> {
            try {
                final Feature feature = new Feature();
                feature.setGeometry(new Point(new LngLatAlt(-114.06, 51.05)));
                return PointFeatureOfInterest.pointFeatureOfInterestBuilder()
                        .id(new Integer(1))
                        .selfLink(new URL("http://example.org/v1.0/FeatureOfInterest(1)"))
                        .observationsNavigationLink("FeatureOfInterest(1)/Observations")
                        .name("Weather Station YYC.")
                        .description("This is a weather station located at the Calgary Airport.")
                        .encodingType(FeatureOfInterest.ValueCode.GeoJSON)
                        .feature(feature)
                        .build();
            } catch (final MalformedURLException e) {
                throw new IllegalStateException("Unable to create the FeatureOfInterest reference test instance");
            }
        });
    }

    @Test
    @DisplayName("FeatureOfInterest deserialization")
    public void testFeatureOfInterestDeserialization() throws IOException {
        final FeatureOfInterest actual = getObjectMapper().readValue(ResourcesUtils.fromResources("featureOfInterest.json", getClass()), PointFeatureOfInterest.class);
        assertEquals(getReferenceSensingEntity(), actual, "A FeatureOfInterest can be deserialized");
    }

    @Test
    @DisplayName("FeatureOfInterest serialization")
    public void testFeatureOfInterestSerialization() throws IOException {
        final FeatureOfInterest actual = getObjectMapper().readValue(getObjectMapper().writeValueAsString(getReferenceSensingEntity()), PointFeatureOfInterest.class);
        assertEquals(getReferenceSensingEntity(), actual, "A FeatureOfInterest can be serialized");
    }

    /**
     * Specific {@link FeatureOfInterest} that uses a {@link Feature} as a {@link FeatureOfInterest#feature}
     */
    @NoArgsConstructor
    protected static class PointFeatureOfInterest extends FeatureOfInterest {

        @Getter
        @Setter
        private Feature feature;

        // Necessary to be explicitly defined to be able to call parent constructor
        // (see https://github.com/rzwitserloot/lombok/issues/853 for more details)
        @Builder(builderMethodName = "pointFeatureOfInterestBuilder") // To avoid non-allowed override of the parent #builder()
        protected PointFeatureOfInterest(
                final Integer id,
                final URL selfLink,
                final String name,
                final String description,
                final FeatureOfInterest.ValueCode encodingType,
                final Feature feature,
                final Collection<Observation> observations,
                final Integer observationsCount,
                final String observationsNavigationLink
        ) {
            setId(id);
            setSelfLink(selfLink);
            setName(name);
            setDescription(description);
            setEncodingType(encodingType);
            setObservations(observations);
            setObservationsCount(observationsCount);
            setObservationsNavigationLink(observationsNavigationLink);
            this.feature = feature;
        }

    }

}
