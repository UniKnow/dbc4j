# What is dcb4java

 `dbc4java` is a pragmatic and extensible general purpose validation framework for any kind of java objects and allows you:

 * to easily validate objects on demand
 * to specify constraints for class fields and methods.
     * specifying constraints for method parameters that are automatically checked when a method is called (pre-conditions).
     * requiring a certain object state before a method is called (pre-conditions).
     * enforcing object validation after an object has been created (invariants).
     * enforcing object validation after a method on an object has been called (invariants).
     * specifying constraints for a method's return value (post-conditions).
 * to configure constraints via annotations.

 Within [introduction design by contract](docs/introduction_dbc.md) we provide a deeper understanding of design by contract. The [Usage dbc4spring](docs/usage.md) chapter describes how the `dbc4java` framework can be used within your project. The [Validation Constraints](docs/validation_constraints.md) provides an overview of the constraint annotations that are default supported by `dbc4java`.
 
## Compile

To compile `dbc4java` execute: `mvnw clean install`

To release `dbc4java` execute: `mvnw clean deploy -P release-signed-artifacts`

