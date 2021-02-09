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

import javax.validation.constraints.Min;

public class PositiveInteger {

    int value = 0;

    @Min(0)
    public int add(int addedValue) {
        value += addedValue;
        return value;
    }

    @Min(0)
    public int subtract(int subtractedValue) {
        value -= subtractedValue;
        return value;
    }

    @Min(0)
    public int toInt() {
        return value;
    }
}
