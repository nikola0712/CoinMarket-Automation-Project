# CoinMarket-Automation-Project

Project for automated tests via Selenium and Rest Assured technologies and Cucumber framework. Tests are written in Java and one some code is in JavaScript.

Download or clone repo and open it via some IDE (preferably IntelliJ or Eclipse.)

pom.xml file has dependencies to Cucumber, Junit, TestNG, Selenium, RestAssured. Download chrome driver and extract into folder where project is. Set up JRE or JDK (version 8+) into project settings.

To run tests directly from feature files, right click and press run button.

To run tests via configuration junit runner use this snippet:

-Denvironment=coinmarket -Dwebdriver.chrome.driver="C:\Projects\chromedriver\chromedriver.exe" -Dcucumber.options="--tags @Backend,@Frontend" -Dchrome.user.data.path="C:\Projects\profiles"

To run via maven CLI:
open terminal and execute command:

mvn test -Dcucumber.options="--tags @Backend,Frontend"
