package com.delkabo.tests;

import com.codeborne.selenide.Configuration;
import com.delkabo.helpers.Attach;
import com.delkabo.drivers.web.WebDriver;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.delkabo.drivers.mobile.BrowserstackMobileDriver;
import com.delkabo.drivers.mobile.EmulatorMobileDriver;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;


public class TestBase {


    public static String deviceHost = System.getProperty("deviceHost");


    @BeforeAll
    static void setUp() {
        System.setProperty("deviceHost", "local");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        switch (deviceHost) {
            case "web":
                WebDriver.configure();
                break;
            case "real":
                Configuration.browser = EmulatorMobileDriver.class.getName();
                Configuration.browserSize = null;
            case "emulation":
                Configuration.browser = EmulatorMobileDriver.class.getName();
                Configuration.browserSize = null;
                break;
            case "browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                Configuration.browserSize = null;
                break;
            default:
                throw new IllegalArgumentException("need choose deviceHost");
        }
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void addAttachments() {

        String sessionId;

        switch (deviceHost) {
            case "web":
                sessionId = Attach.sessionId();
                Attach.addPageSource();
                Attach.addBrowserConsoleLogs();
                break;
            case "browserstack":
                sessionId = Attach.sessionId();
                Attach.video(sessionId);
                break;
            default:
                sessionId = "";
        }

        Attach.addScreenshotAs("Last screenshot");
        Attach.addPageSource();
        Selenide.closeWebDriver();

        if (WebDriver.isVideoOn()) {
            Attach.addVideo(sessionId);
        }

        if (deviceHost.equals("browserstack")) {
            Attach.video(sessionId);
        }
    }
}
