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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * A simple contract is shown below. The client of the method must send a
 * parameter of type `int` that is smaller than
 * {@code MAX_VALUES. In return the client can be certain that that the method returns the value placed at the requested postion in the array.

 */
@Named
public class SimpleMethodContract {

    /**
     * Max number of values that can be persisted.
     */
    public static final int MAX_VALUES = 10;

    private int[] values = new int[MAX_VALUES];

    public int getValue(@Max(MAX_VALUES - 1) @Min(0) final int index) {
        return values[index];
    }

}
