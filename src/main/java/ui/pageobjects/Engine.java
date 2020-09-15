package ui.pageobjects;

import org.openqa.selenium.WebDriver;

public class Engine {
    public final HomePage homepage;
    public final Weather weather;

    public Engine(WebDriver driver){
        homepage= new HomePage(driver);
        weather = new Weather(driver);
    }
}
