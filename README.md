
**Pre condition**
JAVA_HOME & MAVEN_HOME  are set
currently this code is tested using
 jdk 1.8 is used & Project level language  set to  1.8   (required for Lambdas expression)
 
 **please note:** 
Chrome driver used in test ( Version 88.0.4324.190 (Official Build) (64-bit)
( Version 88.0.4324.190 (Official Build) (64-bit))

**project config file is available->**
${user-dir}/src/test/resources/config.properties
Currently only added support for chrome browser in the automation
test.browser.name=Chrome


**Report config files are available in the folder location ->**
${user-dir}/src/test/resources/confi/

**Feature file location->**
${user-dir}/src/test/resources/features/webUi
${user-dir}/src/test/resources/features/api

**Step definitions**
for webUi->${user-dir}/src/test/java\com\acceptance\tests\api\stepdefinitions
for api->${user-dir}/src/test/java\com\acceptance\tests\web\stepdefinitions

**CucumberTest report will be generated here->**
${user-dir}/test-output/JsonReport
${user-dir}/test-output/PdfReport
${user-dir}/test-output/SparkReport

**cucumber runners path**
for webUi->${user-dir}/src/test/java\com\acceptance\tests\runners

**To run all test  via command line run below command line:**
(open terminal and navigate to project directory or just open terminal from IDE ) & run->
mvn test -Dcucumber.options="--tag @cucumberTest"


**This project is using following maven main repo**

Rest-asssured -> for api testing
io-cucumber -> BDD acceptance test
io-picocontainer -> sharing test data between steps
selenium-webdriver
Junit runner  for running cucumber test via JVM and via Cucumber test runner.
POI to  reading and writing test data
assertion-> junit, assertj-core
logging : log4j ( WIP)

**Taken help from**
Google &
https://grasshopper.tech/340/
**Report helper classes is taken from here following git repository**
https://github.com/grasshopper7/extentreports-cucumber6-adapter
https://github.com/executeautomation/restassured
https://www.baeldung.com/jackson-deserialize-json-unknown-properties


**User Guide line to convert older version to new version can be found ( specially for DataTable to POJO**
https://cucumber.io/blog/open-source/announcing-cucumber-jvm-3-0-0/

**TO do list:**
read test data from json file from testDataFolder 

refactor api models ( some of them are duplicated and can be combined)
Simplify  provider classes
add support for XMl file
add support for reading test data from json file
Db support
Create new branch  for selenium and cucumber using spring framework to share test data instead of using picocontainer
new branch with example using Docker
new branch using testNg framework
new branch to run test in parallel
new branch for mobile automation using Appium for ios and Android
Refactor request builder
Run selenium on Grid
Mock setup using wire mock as this is very useful for API testing

SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
http://www.slf4j.org/codes.html#StaticLoggerBinder