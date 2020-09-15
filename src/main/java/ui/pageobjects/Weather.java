package ui.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.utilities.SeleniumUtils;

import java.util.HashMap;
import java.util.List;

public class Weather {

    final WebDriver driver;
    @FindBy(id="searchBox")
    private WebElement pinYourCitySearchBox;

    @FindBy(xpath = "//div[@id='messages']/div/label/input")
    private List<WebElement> pinYourCityList;

    @FindBy(xpath = "//div[@class='cityText']")
    private List<WebElement> mapCityList;

    @FindBy(xpath= "//div[contains(@class,'leaflet-popup-content')]/div/span/b")
    private List<WebElement> weatherInformations;

    public Weather(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void unSelectCities(){
        pinYourCityList.stream().filter(s -> SeleniumUtils.getSpecificAttribute(s,"class")
                .equalsIgnoreCase("defaultChecked")).forEach(SeleniumUtils::clickElement);
    }

    public void enterCity(String cityName){
        SeleniumUtils.sendText(pinYourCitySearchBox, cityName);
    }

    public void selectCity(String cityName){
        pinYourCityList.stream().filter(s -> SeleniumUtils.getSpecificAttribute(s,"id")
                .equalsIgnoreCase(cityName)).forEach(SeleniumUtils::clickElement);
    }

    public void clickCityOnMap(String cityName){
        mapCityList.stream().filter(s -> SeleniumUtils.getLabelText(s).equalsIgnoreCase(cityName))
                .forEach(SeleniumUtils::clickElement);
    }

    public HashMap<String,String> getWeatherInformations(){
        HashMap<String, String> weatherParameters = new HashMap<>();
        for(WebElement weatherInformation: weatherInformations){
            weatherParameters.put(SeleniumUtils.getLabelText(weatherInformation).split(":")[0],
                    SeleniumUtils.getLabelText(weatherInformation).split(":")[1]);
        }

        return weatherParameters;
    }
}
