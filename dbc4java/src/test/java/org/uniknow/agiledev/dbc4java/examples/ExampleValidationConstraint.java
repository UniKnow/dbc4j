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

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Examples of appliance constraints.
 */
public class ExampleValidationConstraint {

    private BigDecimal price = new BigDecimal(0);

    private boolean active;

    private Date eventDate;

    private Date birthday;

    private int quantity;

    private String userName;

    private String identifier;

    private String phoneNumber;

    /**
     * Method by which price is set.
     *
     * @param price
     *         price, should be in the range of 0.00 till 99.99 and have the
     *         format xx.xx
     */
    public void setPrice(@DecimalMin("0.00") @DecimalMax("99.99") @Digits(
            integer = 2, fraction = 2) BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Example of method for which passed boolean parameter should always be
     * {@code false}.
     *
     * @param active
     *         boolean indicating whether customer is active; should always
     *         be {@code false}.
     */
    public void setCustomerInactive(@AssertFalse boolean active) {
        this.active = active;
    }

    /**
     * Example of method for which passed boolean parameter should always be
     * {@code true}.
     *
     * @param active
     *         boolean indicating whether customer is active; should always
     *         be {@code true}.
     */
    public void setCustomerActive(@AssertTrue boolean active) {
        this.active = active;
    }

    /**
     * Method by which status of customer is returned.
     */
    public boolean isCustomerActive() {
        return active;
    }

    /**
     * Example of method for which the passed date must be in the future.
     */
    public void setEventDate(@Future Date eventDate) {
        this.eventDate = eventDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    /**
     * Example of method for which the passed integer value must be in the range
     * 5 till 10.
     */
    public void setQuantity(@Min(5) @Max(10) int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Example of method for which the passed value may not be {@code null},
     * empty or blank string.
     */
    public void setUserName(@NotNull String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    /**
     * Example of method for which the passed value must be {@code null}.
     */
    public void setUnusedValue(@Null Object value) {
    }

    /**
     * Example of method for which the passed date must be in the past.
     */
    public void setBirthday(@Past Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    /**
     * Example of method for which the value must match a particular reqular
     * expression
     */
    public void setPhoneNumber(
            @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Example of method for which the size of the String must be within certain
     * boundaries
     */
    public void setIdentifier(@Size(min = 2, max = 10) String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    protected void applyConstraintMethodScopeProtected(@NotNull Object value) {
    }

    void applyConstraintMethodScopePackage(@NotNull Object value) {
    }

    public void applyConstraintMethodScopePrivate() {
        applyConstraintMethodScopePrivate(null);
    }

    private void applyConstraintMethodScopePrivate(@NotNull Object value) {
    }

}
