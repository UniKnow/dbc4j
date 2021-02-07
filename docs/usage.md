# Usage dbc4java

To use `dbc4java` within your project you need to include the following dependency within your `pom.xml`:

    <!-- Dependencies to implement Design by Contract -->
    <dependency>
        <groupId>org.uniknow.agiledev</groupId>
        <artifactId>dbc4java</artifactId>
        <version>${project.version}</version>
    </dependency>

Classes for which its constraints need to be verified require to be annotated with `javax.validation.constraints` on fields, methods, etc are also added as annotations to the class/method.

    @Named
    @Validated
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public class PositiveInteger {

        @Min(0)
        private int value = 0;

        public void add(int addedValue) {
            value += addedValue;
        }

        public void subtract(int subtractedValue) {
            value -= subtractedValue;
        }

        public int toInt() {
            return value;
        }
    }

In the example above we assure by the constraint `@Min(0)` that the integer is always positive.

To assure that the constraints are validated at every method invocation all classes need to be compiled with `aspectj`. For that the following snippet needs to be added to your `pom.xml`:

    <!--
    AspectJ compile time weaving. Required for Design by Contract
    -->
    <plugin>
        <groupId>com.nickwongdev</groupId>
        <artifactId>aspectj-maven-plugin</artifactId>
        <version>${aspectj-maven-plugin.version}</version>
        <executions>
            <execution>
                <goals>
                    <goal>compile</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <source>${maven.compiler.source}</source>
            <target>${maven.compiler.target}</target>
            <showWeaveInfo>true</showWeaveInfo>
            <complianceLevel>${maven.compiler.target}</complianceLevel>

            <encoding>${project.build.sourceEncoding}</encoding>

            <weaveDependencies>
                <weaveDependency>
                    <groupId>org.uniknow.agiledev</groupId>
                    <artifactId>dbc4java</artifactId>
                </weaveDependency>
            </weaveDependencies>

        </configuration>
        <!--
         Ensure aspectj tools version used by compiler is the same version used as dependency. Avoids warning
         -->
        <dependencies>
            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjtools</artifactId>
                <version>${aspectj.version}</version>
            </dependency>
        </dependencies>
    </plugin>

*NOTE:* `dbc4java` has been tested with aspectj version `1.9.6`.
