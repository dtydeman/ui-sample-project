### Execution of tests

As these are JUnit tests, they can be executed directly within an IDE. Alternatively, as Gradle has been used as the 
package tool, all the tests can be run with the terminal command `./gradlew test`

In order to change the browser used for the test execution, please change the value of `browser` in the 
`test.properties` file. The following are valid options:
* chrome
* headless chrome
* firefox
* headless firefox
* edge

Test classes are currently set to run in parallel, resulting in at most 5 instances to run at the same time.

To change the parallelism to concurrent tests, not test classes, got to the `junit-platform.properties` resource 
file and uncomment `junit.jupiter.execution.parallel.mode.default`. Then comment out the 
`junit.jupiter.execution.parallel.mode.classes.default` property. This will result in test classes being sequential, 
but tests within each class will run in parallel.

To run all tests across all classes in parallel, got to the `junit-platform.properties` resource file and uncomment 
both `junit.jupiter.execution.parallel.mode.default` and `junit.jupiter.execution.parallel.mode.classes.default` 
properties. Please note, as this will result in a large number of concurrent WebDrivers and results my become flaky 
depending on the capabilities of the executing machine.

To disable parallelism of tests, got to the `junit-platform.properties` resource file and change 
`junit.jupiter.execution.parallel.enabled` to `false`