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

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Intercepts method calls of classes which are annotated with
 * {@code javax.validation.constraints.*}.
 *
 * @author mase
 * @since 0.1.3
 */
@Aspect
public final class ValidationInterceptor {

    private static final Logger LOGGER = Logger
            .getLogger(ValidationInterceptor.class.getName());

    private final ExecutableValidator executableValidator;

    ValidationInterceptor() {
        executableValidator = Validation.buildDefaultValidatorFactory()
                .getValidator().forExecutables();
    }

    /**
     * Matches constructor parameters in class annotated with `@Validated`.
     * <p/>
     * *NOTE:* This will only work when class compiled with aspectj.
     */
    @Before("execution(*.new(.., @(javax.validation.constraints.* || org.hibernate.validator.constraints.*) (*), ..))")
    public final void validateConstructorParameters(final JoinPoint joinPoint) {

        final Constructor constructor = ((ConstructorSignature) joinPoint
                .getSignature()).getConstructor();

        final Set<ConstraintViolation<Object>> violations = executableValidator
                .validateConstructorParameters(constructor, joinPoint.getArgs());

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    /**
     * Validate arguments of a method invocation annotated with constraints
     */
    @Before("execution(* *(.., @(javax.validation.constraints.* || org.hibernate.validator.constraints.*) (*), ..))")
    public final void validateMethodInvocation(final JoinPoint pjp) {

        final Method method = ((MethodSignature) pjp.getSignature())
                .getMethod();
        final Object instance = pjp.getTarget();

        // Validate constraint(s) method parameters.
        final Object[] arguments = pjp.getArgs();
        final Set<ConstraintViolation<Object>> violations = executableValidator
                .validateParameters(instance, method, arguments);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    /**
     * Validate method response if annotated with constraints.
     */
    @AfterReturning(
            pointcut = "execution(@(javax.validation.constraints.*) * *(..))",
            returning = "result")
    public void after(final JoinPoint pjp, final Object result) {

        final Object instance = pjp.getTarget();

        if (instance != null) {

            final Method method = ((MethodSignature) pjp.getSignature())
                    .getMethod();

            if ((method != null) && !method.getReturnType().equals(Void.TYPE)) {
                // Validate constraint return value method
                final Set<ConstraintViolation<Object>> violations = executableValidator
                        .validateReturnValue(instance, method, result);
                if (!violations.isEmpty()) {
                    throw new ConstraintViolationException(violations);
                }
            } else {
                LOGGER
                        .fine("Skipped validation return value while method is null");
            }
        }
    }

}
