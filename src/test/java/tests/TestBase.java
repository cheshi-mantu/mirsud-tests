package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class TestBase {
    static final String nameToSearch = System.getProperty("search_name", "Северный город");
    static final String hostname = System.getProperty("host_name","https://mirsud.spb.ru/cases/");

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.startMaximized = true;
        Configuration.timeout = 20000;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = System.getProperty("remote_driver_url", "http://192.168.1.123:4444/wd/hub/");

    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Latest screenshot");
        closeWebDriver();
    }

}
