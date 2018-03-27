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
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geojson.Polygon;
import sta.model.sensing.ext.TMPeriod;

import java.net.URL;

/**
 * Base class for any OGC SensorThings API's <code>Datastream</code>
 *
 * @author Aurelien Bourdon
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractDatastream extends SensingEntity {

    private String name;

    private String description;

    private Polygon observedArea;

    private TMPeriod phenomenonTime;

    private TMPeriod resultTime;

    @JsonProperty("Thing")
    private Thing thing;
    @JsonProperty("Thing@iot.navigationLink")
    private String thingNavigationlink;

    @JsonProperty("Sensor")
    private Sensor sensor;
    @JsonProperty("Sensor@iot.navigationLink")
    private String sensorNavigationLink;

    @JsonProperty("ObservedProperty")
    private ObservedProperty observedProperty;
    @JsonProperty("ObservedProperty@iot.navigationLink")
    private String observedPropertyNavigationLink;

    protected AbstractDatastream(
            final Integer id,
            final URL selfLink,
            final String name,
            final String description,
            final Polygon observedArea,
            final TMPeriod phenomenonTime,
            final TMPeriod resultTime,
            final Thing thing,
            final String thingNavigationlink,
            final Sensor sensor,
            final String sensorNavigationLink,
            final ObservedProperty observedProperty,
            final String observedPropertyNavigationLink
    ) {
        super(id, selfLink);
        this.name = name;
        this.description = description;
        this.observedArea = observedArea;
        this.phenomenonTime = phenomenonTime;
        this.resultTime = resultTime;
        this.thing = thing;
        this.thingNavigationlink = thingNavigationlink;
        this.sensor = sensor;
        this.sensorNavigationLink = sensorNavigationLink;
        this.observedProperty = observedProperty;
        this.observedPropertyNavigationLink = observedPropertyNavigationLink;
    }

}
