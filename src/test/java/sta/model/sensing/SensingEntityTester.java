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


import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import sta.model.WithObjectMapper;

import java.util.function.Supplier;

/**
 * Base class for any {@link SensingEntity} unit tests class.
 * <p>
 * An {@link SensingEntityTester} kept a {@link Supplier} to be able to create its associated {@link SensingEntity}.
 * <p>
 * This {@link Supplier} is called before each unit test. Hence, it should always <strong>create</strong> a new {@link SensingEntity} to isolate unit tests.
 *
 * @author Aurelien Bourdon
 */
public abstract class SensingEntityTester<T extends SensingEntity> extends WithObjectMapper {

    private final Supplier<T> referenceSensingEntitySupplier;

    @Getter
    private T referenceSensingEntity;

    protected SensingEntityTester(final Supplier<T> referenceSensingEntitySupplier) {
        this.referenceSensingEntitySupplier = referenceSensingEntitySupplier;
    }

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        // should always create a new sensing entity to isolate each unit test
        referenceSensingEntity = referenceSensingEntitySupplier.get();
    }

}
