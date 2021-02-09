/*-
 * ////========================LICENSE_START=================================
 * Design By Contracts for Java
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
package org.uniknow.agiledev.dbc4java.examples;

import org.junit.Test;

import javax.validation.ValidationException;

/**
 * Verifies that {@code ValidationException} is thrown when client doesn't
 * comply to the agreed contract.
 */
public class SimpleMethodContractTest {

    private SimpleMethodContract contract = new SimpleMethodContract();

    /**
     * Verifies {@code ValidationException} is thrown when specified index is
     * below 0
     */
    @Test(expected = ValidationException.class)
    public void getValueIndexBelowMin() {
        contract.getValue(-1);
    }

    /**
     * Verifies {@code ValidationException} is thrown when specified index is
     * above {@code MAX_VALUES}-1
     */
    @Test(expected = ValidationException.class)
    public void getValueIndexAboveMax() {
        contract.getValue(SimpleMethodContract.MAX_VALUES);
    }

}
