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
package org.uniknow.agiledev.dbc4java;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.uniknow.agiledev.dbc4java.examples.ExampleValidationConstraint;

import javax.validation.ConstraintViolationException;

/**
 * Performs basic load test of dbc4java
 */
public class LoadTest {

    @Rule
    public ContiPerfRule rule = new ContiPerfRule();

    private ExampleValidationConstraint instanceWithConstrains = new ExampleValidationConstraint();

    /**
     * Set value of instance invoking method with pre conditions
     */
    @Test
    @PerfTest(invocations = 1000000, rampUp = 100, threads = 100)
    @Required(average = 10)
    public void testSetAcceptableValuePostCondition() {
        instanceWithConstrains.setQuantity(7);
    }

    /**
     * Set invalid value of instance invoking method with pre conditions
     */
    @Test(expected = ConstraintViolationException.class)
    @PerfTest(invocations = 1000000, rampUp = 100, threads = 100)
    @Required(average = 10)
    public void testSetUnacceptableValuePostCondition() {
        instanceWithConstrains.setQuantity(15);
    }

}
