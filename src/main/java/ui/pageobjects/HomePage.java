package ui.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.utilities.SeleniumUtils;

public class HomePage {

    final WebDriver driver;
    @FindBy(id="h_sub_menu")
    private WebElement subMenu;

    @FindBy(linkText = "WEATHER")
    private WebElement weatherLink;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSubMenu(){
        SeleniumUtils.clickElement(subMenu);
    }
    public void clickWeather(){
        SeleniumUtils.clickElement(weatherLink);
    }


}
