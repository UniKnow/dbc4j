/*-
 * ========================LICENSE_START=================================
 * Tutorial Design by Contract
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
 * =========================LICENSE_END==================================
 */
package org.uniknow.agiledev.tutorial.dbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.validation.ValidationException;

import static org.junit.Assert.assertEquals;

/**
 * Verifies invariant of {@code PositiveInteger} is applied.
 */
public class PositiveIntegerTest {

    private PositiveInteger value;

    @Before
    public void init() {
        value = new PositiveInteger();
    }

    /**
     * Verifies initial value of {@code PositiveInteger} is 0.
     */
    @Test
    public void testInitialValue() {
        assertEquals(0, value.toInt());
    }

    /**
     * Verifies value is properly added
     */
    @Test
    public void testAddPositiveValue() {
        value.add(1);
        assertEquals(1, value.toInt());
    }

    /**
     * Verifies {@code ValidationException} exception is thrown when value
     * becomes negative
     */
    @Test(expected = ValidationException.class)
    public void testAddNegativeValue() {
        value.add(-1);
    }
}
