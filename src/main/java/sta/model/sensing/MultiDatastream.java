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
import org.geojson.Polygon;
import sta.model.sensing.ext.TMPeriod;
import sta.model.sensing.ext.UnitOfMeasurement;

import java.net.URL;
import java.util.Collection;
import java.util.List;

/**
 * The OGC SensorThings APIs' <code>MultiDatastream</code> entity from within the SensorThings MultiDatastream extension
 *
 * @author Aurelien Bourdon
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MultiDatastream extends AbstractDatastream {

    private ValueCode observationType;

    private List<UnitOfMeasurement> unitOfMeasurements;

    private List<Datastream.ValueCode> multiObservationDataTypes;

    @JsonProperty("Observations")
    private Collection<Observation> observations;
    @JsonProperty("Observations@iot.unt")
    private Integer observationsCount;
    @JsonProperty("Observations@iot.navigationLink")
    private String observationsNavigationLink;

    // Necessary to be explicitly defined to be able to call parent constructor
    // (see https://github.com/rzwitserloot/lombok/issues/853 for more details)
    @Builder
    private MultiDatastream(
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
            final String observedPropertyNavigationLink,
            final ValueCode observationType,
            final List<UnitOfMeasurement> unitOfMeasurements,
            final List<Datastream.ValueCode> multiObservationDataTypes,
            final Collection<Observation> observations,
            final Integer observationsCount,
            final String observationsNavigationLink
    ) {
        super(id, selfLink, name, description, observedArea, phenomenonTime, resultTime, thing, thingNavigationlink, sensor, sensorNavigationLink, observedProperty, observedPropertyNavigationLink);
        this.observationType = observationType;
        this.unitOfMeasurements = unitOfMeasurements;
        this.multiObservationDataTypes = multiObservationDataTypes;
        this.observations = observations;
        this.observationsCount = observationsCount;
        this.observationsNavigationLink = observationsNavigationLink;
    }

    /**
     * All the possible values for a {@link MultiDatastream#observationType} attribute
     *
     * @author Aurelien Bourdon
     */
    public enum ValueCode {
        OM_ComplexObservation("http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_ComplexObservation");

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
