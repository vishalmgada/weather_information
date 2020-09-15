Feature: Fetch and compare weather information from NDTV and weather API with a variance for a city in India.

  Scenario Outline: Validate the weather information from NDTV and Weather API
    Given user accesses NDTV website
    And accesses the Weather Link
    When provides the "<cityName>" to fetch the weather information
    And user calls Weather API to fetch the information for "<cityName>"
    Then compare the weather information retrieved from both sources

    Examples:
    |cityName|
    |warangal|
    |Ajmer   |
    |Bengaluru|