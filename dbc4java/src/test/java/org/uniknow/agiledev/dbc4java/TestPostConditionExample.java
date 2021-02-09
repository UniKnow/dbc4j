/*-
 * ========================LICENSE_START=================================
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
 * =========================LICENSE_END==================================
 */
package org.uniknow.agiledev.dbc4java;

import org.junit.Test;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Verifies exception occurs when post condition on return value fails.
 */
public class TestPostConditionExample {

    @Test
    public void testGetValidProperty() {
        PostConditionExample instance = new PostConditionExample();
        instance.getProperty(new Object());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNoGetterNoneClassMember() {
        PostConditionExample instance = new PostConditionExample();
        instance.getProperty(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testGetInvalidResponseNonClassMember() {
        PostConditionExample instance = new PostConditionExample();
        instance.getValueNonClassMember(null);
    }

    @Test
    public void testGetValidResponseNonClassMember() {
        PostConditionExample instance = new PostConditionExample();
        assertNotNull(instance.getValueNonClassMember(new Object()));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testIterativeInvalidResponseNonClassMember() {
        PostConditionExample instance = new PostConditionExample();
        instance.calculateValidIterative(0, 14);
    }

    /**
     * Verifies post conditions are verified correctly in case of valid handling
     */
    @Test
    public void testIterativeValidResponseNonClassMember() {
        PostConditionExample instance = new PostConditionExample();
        assertEquals(10, instance.calculateValidIterative(0, 10));

    }

    /**
     * Verifies invariants are properly validated in case of correct
     * implementation
     */
    @Test
    public void testValidInvariants() {
        PostConditionExample instance = new PostConditionExample();
        instance.calculateValid();
    }

    /**
     * Verifies validaton exception occurs when trying to invoke getter on
     * wrongly implemented method
     */
    @Test(expected = ValidationException.class)
    public void testInvalidImplementation() {
        InvalidImplementation instance = new InvalidImplementation();
        instance.getInvalidResponse();
    }

}
