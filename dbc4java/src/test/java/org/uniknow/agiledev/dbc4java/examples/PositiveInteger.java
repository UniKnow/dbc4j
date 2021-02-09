package org.uniknow.agiledev.dbc4java.examples;

/*-
 * #%L
 * Design By Contracts for Java
 * %%
 * Copyright (C) 2017 - 2021 UniKnow
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.inject.Named;
import javax.validation.constraints.Min;

/**
 * Example of an invariant that guarantees that value is positive
 */
public class PositiveInteger {

    private int value = 0;

    public void add(@Min(0) int addedValue) {
        value += addedValue;
    }

    public void subtract(int subtractedValue) {
        value -= subtractedValue;
    }

    @Min(0)
    public int toInt() {
        return value;
    }
}
