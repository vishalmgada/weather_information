package ui.utilities;

import common.Initialize;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SeleniumUtils {

    public static FluentWait<WebDriver> fluentWait;

    public static void initializeFluentWait(WebDriver driver){
        fluentWait  = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Initialize.testConfiguration.getUiConfiguration().getWaitTimeout()))
                .pollingEvery(Duration.ofSeconds(Initialize.testConfiguration.getUiConfiguration().getPollingTimeout()));
    }

    public static void waitForDomComplete(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadStatus= driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").toString().equals("complete");
        fluentWait.until(pageLoadStatus);
    }
    /**
     * Method is used to click on Element
     * @param element
     */


    public static void clickElement(WebElement element) {

        fluentWait.until(ExpectedConditions.visibilityOf(element));
        element.click();

    }
    /**
     * Method is used to send text in the field
     * @param element
     * @param text
     */
    public static void sendText(WebElement element, String text) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);

    }
    /**
     * Method is used to send keys to the field
     * @param element
     * @param key
     */
    public static void sendText(WebElement element, Keys key) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(key);
    }

    /**
     * Method is used to scroll to the view
     * @param element
     */
    public static void scrollIntoView(WebElement element, WebDriver driver) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

    }
    /**
     * Method is used to check for the presence of Elements
     * @param element
     * @return
     */
    public static boolean isPresent(WebElement element) {
        if (element.isDisplayed()) {
            return true;
        } else {
            return false;
        }

    }
    /**
     * Method is used to check if the Element is enabled.
     * @param element
     * @return
     */
    public static boolean isEnabled(WebElement element) {

        return element.isEnabled();
    }
    /**
     * Method is used to fetch the label text
     * @param element
     * @return
     */
    public static String getLabelText(WebElement element) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
    /**
     * Method is used to validate the text ignoring the case of the characters.
     * @param element
     * @param expectedText
     * @return
     */
    public static Boolean validateTextIgnoreCase(WebElement element, String expectedText) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
        return element.getText().equalsIgnoreCase(expectedText);
    }

    /**
     * This method is used to get specific attribute value
     * @param element - WebElement
     * @param attributeName - Specific attribute name
     * @return - Return attribute value
     */

    public static String getSpecificAttribute(WebElement element, String attributeName){
        return element.getAttribute(attributeName);
    }
    /**
     * Method is used to validate the text along with case
     * @param element
     * @param expectedText
     * @return
     */

    public static Boolean validateText(WebElement element, String expectedText) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
        return element.getText().equals(expectedText);
    }
    /**
     * Method is used to provide wait.
     * @param millis
     */
    public static void waitformilli(long millis) {
        try {
            Thread.sleep(millis);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Method is used to switch to the window Handle
     */
    public static void switchHandle(WebDriver driver) {
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }
    /**
     * Below method is used to close the driver.
     */
    public static void closeDriver(WebDriver driver) {
        driver.close();
    }
    /**
     * Method is used to Quit Driver
     */
    public static void quitDriver(WebDriver driver) {
        driver.quit();
    }

    /**
     * Method captures screenshot and returns in byte format.
     * @return
     */
    public static byte[] captureScreenshot(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

}
