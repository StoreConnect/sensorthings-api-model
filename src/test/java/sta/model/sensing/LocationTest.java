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
 * {@link Location} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("Location")
public class LocationTest extends SensingEntityTester<Location> {

    protected LocationTest() {
        super(() -> {
            final Feature location = new Feature();
            location.setGeometry(new Point(new LngLatAlt(-114.06, 51.05)));
            try {
                return FeatureLocation.featureLocationBuilder()
                        .id(new Integer(1))
                        .selfLink(new URL("http://example.org/v1.0/Locations(1)"))
                        .thingsNavigationLink("Locations(1)/Things")
                        .historicalLocationsNavigationLink("Locations(1)/HistoricalLocations")
                        .encodingType(Location.ValueCode.GeoJSON)
                        .name("CCIT")
                        .description("Calgary Center for Innovative Technologies")
                        .location(location)
                        .build();
            } catch (final MalformedURLException e) {
                throw new IllegalStateException("Unable to create the Location reference test instance");
            }
        });
    }


    @Test
    @DisplayName("Location deserialization")
    public void testLocationDeserialization() throws IOException {
        final Location actual = getObjectMapper().readValue(ResourcesUtils.fromResources("location.json", getClass()), FeatureLocation.class);
        assertEquals(getReferenceSensingEntity(), actual, "A FeatureOfInterest can be deserialized");
    }

    @Test
    @DisplayName("Location serialization")
    public void testLocationSerialization() throws IOException {
        final Location actual = getObjectMapper().readValue(getObjectMapper().writeValueAsString(getReferenceSensingEntity()), FeatureLocation.class);
        assertEquals(getReferenceSensingEntity(), actual, "A FeatureOfInterest can be serialized");
    }

    /**
     * Specific {@link Location} that uses a {@link Feature} as a {@link Location#location}
     *
     * @author Aurélien Bourdon
     */
    @Getter
    @Setter
    @NoArgsConstructor
    private static class FeatureLocation extends Location {

        private Feature location;

        // Necessary to be explicitly defined to be able to call parent constructor
        // (see https://github.com/rzwitserloot/lombok/issues/853 for more details)
        @Builder(builderMethodName = "featureLocationBuilder") // To avoid non-allowed override of the parent #builder()
        private FeatureLocation(
                final Integer id,
                final URL selfLink,
                final String name,
                final String description,
                final Location.ValueCode encodingType,
                final Feature location,
                final Collection<Thing> things,
                final Integer thingsCount,
                final String thingsNavigationLink,
                final Collection<HistoricalLocation> historicalLocations,
                final Integer historicalLocationsCount,
                final String historicalLocationsNavigationLink
        ) {
            setId(id);
            setSelfLink(selfLink);
            setName(name);
            setDescription(description);
            setEncodingType(encodingType);
            setThings(things);
            setThingsCount(thingsCount);
            setThingsNavigationLink(thingsNavigationLink);
            setHistoricalLocations(historicalLocations);
            setHistoricalLocationsCount(historicalLocationsCount);
            setHistoricalLocationsNavigationLink(historicalLocationsNavigationLink);
            this.location = location;
        }

    }

}
