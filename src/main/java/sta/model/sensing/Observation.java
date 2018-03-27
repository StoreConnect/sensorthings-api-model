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
import sta.model.sensing.ext.TMObject;
import sta.model.sensing.ext.TMPeriod;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

/**
 * The OGC SensorThings API's <code>Observation</code> entity
 *
 * @author Aurelien Bourdon
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Observation extends SensingEntity {

    private TMObject phenomenonTime;

    private TMInstant resultTime;

    private Object result;

    private Collection<Object> resultQuality;

    private TMPeriod validTime;

    private Map<String, Object> parameters;

    @JsonProperty("Datastream")
    private Datastream datastream;
    @JsonProperty("Datastream@iot.navigationLink")
    private String datastreamNavigationLink;

    @JsonProperty("MultiDatastream")
    private MultiDatastream multiDatastream;
    @JsonProperty("MultiDatastream@iot.navigationLink")
    private String multiDatastreamNavigationLink;

    @JsonProperty("FeatureOfInterest")
    private FeatureOfInterest featureOfInterest;
    @JsonProperty("FeatureOfInterest@iot.navigationLink")
    private String featureOfInterestNavigationLink;

    // Necessary to be explicitly defined to be able to call parent constructor
    // (see https://github.com/rzwitserloot/lombok/issues/853 for more details)
    @Builder
    private Observation(
            final Integer id,
            final URL selfLink,
            final TMObject phenomenonTime,
            final TMInstant resultTime,
            final Object result,
            final Collection<Object> resultQuality,
            final TMPeriod validTime,
            final Map<String, Object> parameters,
            final Datastream datastream,
            final String datastreamNavigationLink,
            final MultiDatastream multiDatastream,
            final String multiDatastreamNavigationLink,
            final FeatureOfInterest featureOfInterest,
            final String featureOfInterestNavigationLink
    ) {
        super(id, selfLink);
        this.phenomenonTime = phenomenonTime;
        this.resultTime = resultTime;
        this.result = result;
        this.resultQuality = resultQuality;
        this.validTime = validTime;
        this.parameters = parameters;
        this.datastream = datastream;
        this.datastreamNavigationLink = datastreamNavigationLink;
        this.multiDatastream = multiDatastream;
        this.multiDatastreamNavigationLink = multiDatastreamNavigationLink;
        this.featureOfInterest = featureOfInterest;
        this.featureOfInterestNavigationLink = featureOfInterestNavigationLink;
    }

}
