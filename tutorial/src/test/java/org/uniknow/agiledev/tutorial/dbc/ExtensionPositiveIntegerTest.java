/*-
 * ////========================LICENSE_START=================================
 * Tutorial Design by Contract
 * ////
 * Copyright (C) 2017 - 2021 UniKnow
 * ////
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
 * ////=========================LICENSE_END==================================
 */
package org.uniknow.agiledev.tutorial.dbc;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;

/**
 * Created by mase on 12/6/2016.
 */
public class ExtensionPositiveIntegerTest {

    private ExtensionPositiveInteger value;

    @Before
    public void init() {
        value = new ExtensionPositiveInteger();
    }

    /**
     * Verifies exception is thrown in case divider is < 1
     */
    @Test(expected = ConstraintViolationException.class)
    public void testDivideByZero() {
        value.div(0);
    }

    /**
     * Verifies division is succesfull when divider >= 1 and <= 10
     */
    @Test
    public void testDivide() {
        value.add(6);
        value.div(1);
        assertEquals(6, value.value);
    }

    /**
     * Verifies exception is thrown in case divider is > 10
     */
    @Test(expected = ConstraintViolationException.class)
    public void testDivideByThirteen() {
        value.div(13);
    }

}
