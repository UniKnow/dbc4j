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

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Verifies post conditions are properly handled.
 */
public class PostConditionExample {

    private Object property;

    private int calculatedValue = 0;

    @NotNull
    public Object getProperty(Object value) {
        property = value;
        return property;
    }

    /**
     * Causes hibernate validator constraint violation because getter doesn't
     * apply to property of class.
     */
    @NotNull
    public Object getValueNonClassMember(Object value) {
        return value;
    }

    /**
     * Results in valid calculated value
     */
    public void calculateValid() {
        for (int i = 0; i < 10; i++) {
            calculatedValue++;
        }
    }

    /**
     * Results in invalid calculated value
     */
    public void calculateInvalid() {
        for (int i = 0; i < 15; i++) {
            calculatedValue++;
        }
    }

    /**
     * Results in invalid calculated value
     */
    @Max(10)
    public int calculateValidIterative(int intialValue, final int maxValue) {
        if (intialValue < maxValue) {
            return calculateValidIterative(++intialValue, maxValue);
        } else {
            return intialValue;
        }
    }

}
