package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ui.pageobjects.Engine;
import ui.utilities.SeleniumUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Initialize {

    public static WebDriver driver;
    protected static Engine engine;
    protected static SeleniumUtils seleniumUtils;
    final ObjectMapper objectMapper= new ObjectMapper();
    public static TestConfiguration testConfiguration;

    @SneakyThrows
    public void loadConfigProperties(){
            URL resource = getClass().getClassLoader().getResource("testconfiguration.json");
            File file = new File(resource.toURI());
            testConfiguration = objectMapper.readValue(file, TestConfiguration.class);

    }
    public void browserSetUp(String browser) {

        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        driver.get("https://www.ndtv.com/");
        engine = new Engine(driver);
        SeleniumUtils.initializeFluentWait(driver);
    }
}
