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


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.Collection;

/**
 * The OGC SensorThings API's <code>Location</code> entity
 *
 * @author Aurelien Bourdon
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location extends SensingEntity {

    private String name;

    private String description;

    private ValueCode encodingType;

    private Object location;

    @JsonProperty("Things")
    private Collection<Thing> things;
    @JsonProperty("Things@iot.count")
    private Integer thingsCount;
    @JsonProperty("Things@iot.navigationLink")
    private String thingsNavigationLink;

    @JsonProperty("HistoricalLocations")
    private Collection<HistoricalLocation> historicalLocations;
    @JsonProperty("HistoricalLocations@iot.count")
    private Integer historicalLocationsCount;
    @JsonProperty("HistoricalLocations@iot.navigationLink")
    private String historicalLocationsNavigationLink;

    // Necessary to be explicitly defined to be able to call parent constructor
    // (see https://github.com/rzwitserloot/lombok/issues/853 for more details)
    @Builder
    private Location(
            final Integer id,
            final URL selfLink,
            final String name,
            final String description,
            final ValueCode encodingType,
            final Object location,
            final Collection<Thing> things,
            final Integer thingsCount,
            final String thingsNavigationLink,
            final Collection<HistoricalLocation> historicalLocations,
            final Integer historicalLocationsCount,
            final String historicalLocationsNavigationLink
    ) {
        super(id, selfLink);
        this.name = name;
        this.description = description;
        this.encodingType = encodingType;
        this.location = location;
        this.things = things;
        this.thingsCount = thingsCount;
        this.thingsNavigationLink = thingsNavigationLink;
        this.historicalLocations = historicalLocations;
        this.historicalLocationsCount = historicalLocationsCount;
        this.historicalLocationsNavigationLink = historicalLocationsNavigationLink;
    }

    /**
     * All the possible values for a {@link Location#encodingType} attribute
     *
     * @author Aurelien Bourdon
     */
    public enum ValueCode {

        GeoJSON("application/vnd.geo+json");

        private final String value;

        ValueCode(final String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

    }

}
