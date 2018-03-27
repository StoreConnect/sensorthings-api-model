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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

/**
 * The OGC SensorThings' <code>Thing</code> entity
 *
 * @author Aurelien Bourdon
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Thing extends SensingEntity {

    private String name;

    private String description;

    private Map<String, String> properties;

    @JsonProperty("Locations")
    private Collection<Location> locations;
    @JsonProperty("Locations@iot.count")
    private Integer locationsCount;
    @JsonProperty("Locations@iot.navigationLink")
    private String locationsNavigationLink;

    @JsonProperty("HistoricalLocations")
    private Collection<HistoricalLocation> historicalLocations;
    @JsonProperty("HistoricalLocations@iot.count")
    private Integer historicalLocationsCount;
    @JsonProperty("HistoricalLocations@iot.navigationLink")
    private String historicalLocationsNavigationLink;

    @JsonProperty("Datastreams")
    private Collection<Datastream> datastreams;
    @JsonProperty("Datastreams@iot.count")
    private Integer datastreamsCount;
    @JsonProperty("Datastreams@iot.navigationLink")
    private String datastreamsNavigationLink;

    @JsonProperty("MultiDatastreams")
    private Collection<MultiDatastream> multiDatastreams;
    @JsonProperty("MultiDatastreams@iot.count")
    private Integer multiDatastreamsCount;
    @JsonProperty("MultiDatastreams@iot.navigationLink")
    private String multiDatastreamsNavigationLink;

    // Necessary to be explicitly defined to be able to call parent constructor
    // (see https://github.com/rzwitserloot/lombok/issues/853 for more details)
    @Builder
    private Thing(
            final Integer id,
            final URL selfLink,
            final String name,
            final String description,
            final Map<String, String> properties,
            final Collection<Location> locations,
            final Integer locationsCount,
            final String locationsNavigationLink,
            final Collection<HistoricalLocation> historicalLocations,
            final Integer historicalLocationsCount,
            final String historicalLocationsNavigationLink,
            final Collection<Datastream> datastreams,
            final Integer datastreamsCount,
            final String datastreamsNavigationLink,
            final Collection<MultiDatastream> multiDatastreams,
            final Integer multiDatastreamsCount,
            final String multiDatastreamsNavigationLink
    ) {
        super(id, selfLink);
        this.name = name;
        this.description = description;
        this.properties = properties;
        this.locations = locations;
        this.locationsCount = locationsCount;
        this.locationsNavigationLink = locationsNavigationLink;
        this.historicalLocations = historicalLocations;
        this.historicalLocationsCount = historicalLocationsCount;
        this.historicalLocationsNavigationLink = historicalLocationsNavigationLink;
        this.datastreams = datastreams;
        this.datastreamsCount = datastreamsCount;
        this.datastreamsNavigationLink = datastreamsNavigationLink;
        this.multiDatastreams = multiDatastreams;
        this.multiDatastreamsCount = multiDatastreamsCount;
        this.multiDatastreamsNavigationLink = multiDatastreamsNavigationLink;
    }
    
}
