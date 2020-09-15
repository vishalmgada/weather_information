package common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ui.pageobjects.Engine;
import ui.utilities.SeleniumUtils;

import java.io.File;
import java.net.URL;

public class Initialize {

    protected static WebDriver driver;
    protected static Engine engine;
    final ObjectMapper objectMapper= new ObjectMapper();
    public static TestConfiguration testConfiguration;

    private static final String CHROME_PROPERTY ="webdriver.chrome.driver";
    private static final String FIREFOX_PROPERTY ="webdriver.gecko.driver";
    private static final String CONFIG_FILE_NAME= "testConfiguration.json";

    @SneakyThrows
    public void loadConfigProperties(){
            URL resource = getClass().getClassLoader().getResource(CONFIG_FILE_NAME);
            File file = new File(resource.toURI());
            testConfiguration = objectMapper.readValue(file, TestConfiguration.class);

    }
    public static void browserSetUp(String browser) {

        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty(CHROME_PROPERTY,testConfiguration.getUiConfiguration().getDriverPath()+"\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
            options.setHeadless(testConfiguration.getUiConfiguration().isHeadless());
            driver = new ChromeDriver(options);

        }else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty(FIREFOX_PROPERTY,testConfiguration.getUiConfiguration().getDriverPath()+"\\geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
            options.setHeadless(testConfiguration.getUiConfiguration().isHeadless());
            driver = new FirefoxDriver(options);
        }
        driver.manage().window().maximize();
        driver.get(testConfiguration.getUiConfiguration().getWebsite());
        engine = new Engine(driver);
        SeleniumUtils.initializeFluentWait(driver);
    }


}
