package stepdefinition;

import api.utilities.RequestCreation;
import common.Initialize;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import ui.utilities.SeleniumUtils;
import ui.utilities.TemperatureConversionUtils;

import java.util.HashMap;

public class StepDefinition extends Initialize {
    HashMap<String, String> weatherParametersUI = new HashMap<>();
    final HashMap<String, String> requestParameters = new HashMap<>();
    final RequestCreation requestCreation = new RequestCreation();
    float kelvinTemperatureFromAPI;
    @Before
    public void load_config(){
        loadConfigProperties();
    }

    @Given("user accesses NDTV website")
    public void user_access_ndtv_website() {
        browserSetUp(testConfiguration.getUiConfiguration().getBrowser());
    }

    @Given("accesses the Weather Link")
    public void accesses_the_weather_link() {
        engine.homepage.clickSubMenu();
        engine.homepage.clickWeather();
        SeleniumUtils.waitForDomComplete(driver);
    }

    @When("provides the {string} to fetch the weather information")
    public void provides_the_to_fetch_the_weather_information(String cityName) {
        SeleniumUtils.waitForDomComplete(driver);
        engine.weather.unSelectCities();
        engine.weather.enterCity(cityName);
        engine.weather.selectCity(cityName);
        SeleniumUtils.waitForDomComplete(driver);
        engine.weather.clickCityOnMap(cityName);
        weatherParametersUI =engine.weather.getWeatherInformations();
    }

    @When("user calls Weather API to fetch the information for {string}")
    public void user_calls_weather_api_to_fetch_the_information_for(String cityName) {
        requestCreation.setBaseURI(testConfiguration.getApiConfiguration().getBaseuri());
        requestParameters.put("q","Warangal");
        requestParameters.put("appid", testConfiguration.getApiConfiguration().getKey());
        requestCreation.addQueryParameters(requestParameters);
        Response response=requestCreation.getRequest(testConfiguration.getApiConfiguration().getResource());
        kelvinTemperatureFromAPI =response.jsonPath().get("main.temp");

    }

    @Then("compare the weather information retrieved from both sources")
    public void compare_the_weather_information_retrieved_from_both_sources() {
        Assert.assertEquals(Float.parseFloat(weatherParametersUI.get("Temp in Degrees")),
                TemperatureConversionUtils.convertTempToCelsius(kelvinTemperatureFromAPI),
                testConfiguration.getVariance());
        Assert.assertEquals(Float.parseFloat(weatherParametersUI.get("Temp in Fahrenheit")),
                TemperatureConversionUtils.convertTempToFahrenheit(kelvinTemperatureFromAPI),
                testConfiguration.getVariance());
    }

    @After
    public void clean_up(Scenario scenario){
        if(scenario.isFailed()) {
            byte[] screenshot=SeleniumUtils.captureScreenshot(driver);
            scenario.attach(screenshot, "image/png","failure");
        }
        SeleniumUtils.closeDriver(driver);
        SeleniumUtils.quitDriver(driver);
    }
}
