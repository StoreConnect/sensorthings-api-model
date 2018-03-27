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
 * The OGC SensorThings API's <code>FeatureOfInterest</code> entity
 *
 * @author Aurelien Bourdon
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeatureOfInterest extends SensingEntity {

    private String name;

    private String description;

    private ValueCode encodingType;

    private Object feature;

    @JsonProperty("Observations")
    private Collection<Observation> observations;
    @JsonProperty("Observations@iot.count")
    private Integer observationsCount;
    @JsonProperty("Observations@iot.navigationLink")
    private String observationsNavigationLink;

    // Necessary to be explicitly defined to be able to call parent constructor
    // (see https://github.com/rzwitserloot/lombok/issues/853 for more details)
    @Builder
    private FeatureOfInterest(
            final Integer id,
            final URL selfLink,
            final String name,
            final String description,
            final ValueCode encodingType,
            final Object feature,
            final Collection<Observation> observations,
            final Integer observationsCount,
            final String observationsNavigationLink
    ) {
        super(id, selfLink);
        this.name = name;
        this.description = description;
        this.encodingType = encodingType;
        this.feature = feature;
        this.observations = observations;
        this.observationsCount = observationsCount;
        this.observationsNavigationLink = observationsNavigationLink;
    }

    /**
     * All the possible values for a {@link FeatureOfInterest#encodingType} attribute
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
