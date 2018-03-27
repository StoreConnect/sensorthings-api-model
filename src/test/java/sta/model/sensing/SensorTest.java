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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sta.utils.ResourcesUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link Sensor} unit tests
 *
 * @author Aurelien Bourdon
 */
@DisplayName("Sensor")
public class SensorTest extends SensingEntityTester<Sensor> {

    protected SensorTest() {
        super(() -> {
            try {
                return PDFEncodedSensor.pdfEncodedSensorBuilder()
                        .id(new Integer(1))
                        .selfLink(new URL("http://example.org/v1.0/Sensors(1)"))
                        .datastreamsNavigationLink("Sensors(1)/Datastreams")
                        .name("TMP36")
                        .description("TMP36 - Analog Temperature sensor")
                        .encodingType(Sensor.ValueCode.PDF)
                        .metadata(new URL("http://example.org/TMP35_36_37.pdf"))
                        .build();
            } catch (MalformedURLException e) {
                throw new IllegalStateException("Unable to create the Sensor reference test instance");
            }
        });
    }

    @Test
    @DisplayName("Sensor deserialization")
    public void testSensorDeserialization() throws IOException {
        final Sensor actual = getObjectMapper().readValue(ResourcesUtils.fromResources("sensor.json", getClass()), PDFEncodedSensor.class);
        assertEquals(getReferenceSensingEntity(), actual, "An Sensor can be deserialized");
    }

    @Test
    @DisplayName("Sensor serialization")
    public void testSensorSerialization() throws IOException {
        final Sensor actual = getObjectMapper().readValue(getObjectMapper().writeValueAsString(getReferenceSensingEntity()), PDFEncodedSensor.class);
        assertEquals(getReferenceSensingEntity(), actual, "An Sensor can be serialized");
    }

    /**
     * Specific {@link Sensor} that uses the {@link Sensor.ValueCode#PDF} encoding type
     */
    @Getter
    @Setter
    @NoArgsConstructor
    private static class PDFEncodedSensor extends Sensor {

        private URL metadata;

        // Necessary to be explicitly defined to be able to call parent constructor
        // (see https://github.com/rzwitserloot/lombok/issues/853 for more details)
        @Builder(builderMethodName = "pdfEncodedSensorBuilder") // To avoid non-allowed override of the parent #builder()
        private PDFEncodedSensor(
                final Integer id,
                final URL selfLink,
                final String name,
                final String description,
                final Sensor.ValueCode encodingType,
                final URL metadata,
                final Collection<Datastream> datastreams,
                final Integer datastreamsCount,
                final String datastreamsNavigationLink
        ) {
            setId(id);
            setSelfLink(selfLink);
            setName(name);
            setDescription(description);
            setEncodingType(encodingType);
            setDatastreams(datastreams);
            setDatastreamsCount(datastreamsCount);
            setDatastreamsNavigationLink(datastreamsNavigationLink);
            this.metadata = metadata;
        }

    }

}
