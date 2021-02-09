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
package org.uniknow.agiledev.dbc4java.examples;

import org.junit.Test;

import javax.inject.Inject;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Verifies constraints which are applied in {@code ExampleValidationConstraint}
 * .
 */
public class ExampleValidationConstraintTest {

    @Inject
    private ExampleValidationConstraint example = new ExampleValidationConstraint();

    /**
     * Verify that price can't be below 0
     */
    @Test(expected = ValidationException.class)
    public void testPriceBelowMin() {
        example.setPrice(new BigDecimal("-0.01"));
    }

    /**
     * Verifies that price can't be above 100.00
     */
    @Test(expected = ValidationException.class)
    public void testPriceAboveMax() {
        example.setPrice(new BigDecimal("100.00"));
    }

    /**
     * Verifies that format of price other than xx.xx causes exception
     */
    @Test(expected = ValidationException.class)
    public void testSetPriceInvalidFormat() {
        example.setPrice(new BigDecimal("0.001"));
    }

    /**
     * Verifies that price is properly set
     */
    @Test
    public void testSetPrice() {
        // Random randomizer = new Random();
        // float price = randomizer.nextInt(9999) / 100;
        BigDecimal price = new BigDecimal("64.11");

        example.setPrice(price);
        assertEquals(price, example.getPrice());
    }

    /**
     * Verifies exception is thrown when method for which boolean always must be
     * {@code false} is set to {@code true}.
     */
    @Test(expected = ValidationException.class)
    public void testSetCustomerInactiveWithInvalidValue() {
        example.setCustomerInactive(true);
    }

    /**
     * Verifies status of customer is properly set.
     */
    @Test
    public void testSetCustomerInactive() {
        example.setCustomerInactive(false);
        assertFalse(example.isCustomerActive());
    }

    /**
     * Verifies exception is thrown when method for which boolean always must be
     * {@code true} is set to {@code false}.
     */
    @Test(expected = ValidationException.class)
    public void testSetCustomerActiveWithInvalidValue() {
        example.setCustomerActive(false);
    }

    /**
     * Verifies status of customer is properly set.
     */
    @Test
    public void testSetCustomerActive() {
        example.setCustomerActive(true);
        assertTrue(example.isCustomerActive());
    }

    /**
     * Verifies exception is thrown when speficied date is not within the future
     */
    @Test(expected = ValidationException.class)
    public void testSetEventDatePast() {
        Date eventDate = new Date(System.currentTimeMillis() - 1000);
        example.setEventDate(eventDate);
    }

    /**
     * Verifies event date is properly set
     */
    @Test
    public void testSetEventDate() {
        Date eventDate = new Date(System.currentTimeMillis() + 1000);
        example.setEventDate(eventDate);
    }

    /**
     * Verifies exception is thrown when passed integer value is below minimum.
     */
    @Test(expected = ValidationException.class)
    public void testSetQuantityBelowMinimum() {
        example.setQuantity(4);
    }

    /**
     * Verifies exception is thrown when passed integer value is above maximum.
     */
    @Test(expected = ValidationException.class)
    public void testSetQuantityAboveMaximum() {
        example.setQuantity(11);
    }

    /**
     * Verifies quantity is properly set
     */
    @Test
    public void testSetQuantity() {
        example.setQuantity(7);
        assertEquals(7, example.getQuantity());
    }

    /**
     * Verifies exception is thrown when passed value is {@code null}.
     */
    @Test(expected = ValidationException.class)
    public void testSetUserNameNull() {
        example.setUserName(null);
    }

    /**
     * Verifies user name is properly set.
     */
    @Test
    public void testSetUserName() {
        example.setUserName("TEST");
        assertEquals("TEST", example.getUserName());
    }

    /**
     * Verifies exception is thrown when passed value is not {@code null}.
     */
    @Test(expected = ValidationException.class)
    public void testSetUnusedValueNotNull() {
        example.setUnusedValue(new Object());
    }

    /**
     * Verifies unused value is properly set
     */
    @Test
    public void testSetUnusedValue() {
        example.setUnusedValue(null);
    }

    /**
     * Verifies exception is thrown when passed value is in the future.
     */
    @Test(expected = ValidationException.class)
    public void testSetBirthdayFuture() {
        Date birthday = new Date(System.currentTimeMillis() + 1000);
        example.setBirthday(birthday);
    }

    /**
     * Verifies birthday is properly set
     */
    @Test
    public void testSetBirthday() {
        Date birthday = new Date(System.currentTimeMillis() - 1000);
        example.setBirthday(birthday);
        assertEquals(birthday, example.getBirthday());
    }

    /**
     * Verifies exception is thrown when passed String is not within the
     * specified boundaries
     */
    @Test(expected = ValidationException.class)
    public void testSetIdentifierBelowMinLength() {
        example.setIdentifier("0");
    }

    /**
     * Verifies exception is thrown when passed String is not within the
     * specified boundaries
     */
    @Test(expected = ValidationException.class)
    public void testSetIdentifierAboveMaxLength() {
        example.setIdentifier("01234567890");
    }

    /**
     * Set identifier
     */
    @Test
    public void testSetIdentifier() {
        String identifier = "012345";

        example.setIdentifier(identifier);
        assertEquals(identifier, example.getIdentifier());
    }

    /**
     * Verifies exception is thrown when passed String doesn't comply to the
     * international phone number pattern.
     */
    @Test(expected = ValidationException.class)
    public void testSetPhoneNumberInvalid() {
        example.setPhoneNumber("234-567890");
    }

    /**
     * Set international phone number
     */
    @Test
    public void testSetPhoneNumber() {
        String phoneNumber = "+31 0123456789";

        example.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, example.getPhoneNumber());
    }

    /**
     * Verifies constraints are applied on methods with scope protected
     */
    @Test(expected = ValidationException.class)
    public void invokeMethodScopeProtected() {
        example.applyConstraintMethodScopeProtected(null);
    }

    /**
     * Verifies constraints are applied on methods with scope package
     */
    @Test(expected = ValidationException.class)
    public void invokeMethodScopePackage() {
        example.applyConstraintMethodScopePackage(null);
    }

    /**
     * Verifies constraints are applied on methods with scope package
     */
    @Test(expected = ValidationException.class)
    public void invokeMethodScopePrivate() {
        example.applyConstraintMethodScopePrivate();
    }

}
