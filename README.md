## **Weather Information**

This project fetches the weather information for cities within India 
from news information website NDTV and Open weather API and compares them. A variance tolerance level is specified for comparison. If the difference is 
outside the variance tolerance, the test returns with failure. This utility 
compares the temperature in Celsius and Fahrenheit.

**Pre-Requisites:**
1. Java-11
2. Maven

**Configuration Properties**

"testConfiguration.json" contains the configuration for the code.
In order to execute the code locally, update the below properties:

_ui.browser_: Code supports Chrome or Firefox. Provide anyone of then name. Default value is set to chrome.

_ui.driverPath_: Path of the driver exe for the browser.

_ui.waitTimeout_: Parameter to configure wait time out for Fluent wait.

_ui.pollingTimeout_: Parameter to configure polling timeout for Fluent wait.

_ui.headless_: Parameter to enable headless execution. Default value is false. Set true to run in headless mode.

_api.baseuri_ : Parameter to configure base URI for the API.

_api.resource_ : Parameter for resource

_api.key_ : Parameter for API key. Since, this is secret key; do not commit the property file with this key and value. For local, execution please add this parameter.
While executing the project on jenkins, configure key vault to inject the api key.

_variance_: Parameter to provide variance tolerance. 

Cucumber feature file contains the list of cities. Feature file location: `src/test/resources/featurefile/CompareWeatherInformation.feature` 

**Execution**  

_Execute using maven command:_ `mvn test -DAPIKey=<APIkey>`   

**Reports**
Execution reports are available in .\reports. The reports are in html and json format.



