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
import sta.model.sensing.ext.TMInstant;

import java.net.URL;
import java.util.Collection;

/**
 * The OGC SensorThings' <code>HistoricalLocation</code> entity
 *
 * @author Aurelien Bourdon
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoricalLocation extends SensingEntity {

    private TMInstant time;

    @JsonProperty("Locations")
    private Collection<Location> locations;
    @JsonProperty("Locations@iot.count")
    private Integer locationsCount;
    @JsonProperty("Locations@iot.navigationLink")
    private String locationsNavigationLink;

    @JsonProperty("Thing")
    private Thing thing;
    @JsonProperty("Thing@iot.navigationLink")
    private String thingNavigationLink;

    // Necessary to be explicitly defined to be able to call parent constructor
    // (see https://github.com/rzwitserloot/lombok/issues/853 for more details)
    @Builder
    protected HistoricalLocation(
            final Integer id,
            final URL selfLink,
            final TMInstant time,
            final Collection<Location> locations,
            final Integer locationsCount,
            final String locationsNavigationLink,
            final Thing thing,
            final String thingNavigationLink
    ) {
        super(id, selfLink);
        this.time = time;
        this.locations = locations;
        this.locationsCount = locationsCount;
        this.locationsNavigationLink = locationsNavigationLink;
        this.thing = thing;
        this.thingNavigationLink = thingNavigationLink;
    }

}
