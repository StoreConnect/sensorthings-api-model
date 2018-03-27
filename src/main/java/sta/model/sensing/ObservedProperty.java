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

import java.net.URI;
import java.net.URL;
import java.util.Collection;

/**
 * The OGC SensorThings' <code>ObservedProperty</code> entity
 *
 * @author Aurelien Bourdon
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObservedProperty extends SensingEntity {

    private String name;

    private URI definition;

    private String description;

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
    private ObservedProperty(
            final Integer id,
            final URL selfLink,
            final String name,
            final URI definition,
            final String description,
            final Collection<Datastream> datastreams,
            final Integer datastreamsCount,
            final String datastreamsNavigationLink,
            final Collection<MultiDatastream> multiDatastreams,
            final Integer multiDatastreamsCount,
            final String multiDatastreamsNavigationLink

    ) {
        super(id, selfLink);
        this.name = name;
        this.definition = definition;
        this.description = description;
        this.datastreams = datastreams;
        this.datastreamsCount = datastreamsCount;
        this.datastreamsNavigationLink = datastreamsNavigationLink;
        this.multiDatastreams = multiDatastreams;
        this.multiDatastreamsCount = multiDatastreamsCount;
        this.multiDatastreamsNavigationLink = multiDatastreamsNavigationLink;
    }

}
