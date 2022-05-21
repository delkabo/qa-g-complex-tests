package com.delkabo.tests;

import com.codeborne.selenide.Configuration;
import com.delkabo.config.Project;
import com.delkabo.helpers.web.AllureAttachments;
import com.delkabo.drivers.web.WebDriver;
import com.delkabo.drivers.web.DriverUtils;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.delkabo.drivers.mobile.BrowserstackMobileDriver;
import com.delkabo.drivers.mobile.EmulatorMobileDriver;
import com.delkabo.helpers.mobile.Attach;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;


@ExtendWith({AllureJunit5.class})
public class TestBase {

    public static String deviceHost = System.getProperty("deviceHost");
    public static String loginWP,
                          passwordWP;

    @BeforeAll
    static void setUp() {
        String deleteProp = "emulation";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        switch (deviceHost) {
            case "web":
                WebDriver.configure();
                break;
            case "real":
                Configuration.browser = EmulatorMobileDriver.class.getName();
                loginWP = EmulatorMobileDriver.configEmul.loginWP();
                passwordWP = EmulatorMobileDriver.configEmul.passwordWP();
                Configuration.browserSize = null;
            case "emulation":
                Configuration.browser = EmulatorMobileDriver.class.getName();
                loginWP = EmulatorMobileDriver.configEmul.loginWP();
                passwordWP = EmulatorMobileDriver.configEmul.passwordWP();
                Configuration.browserSize = null;
                break;
            case "browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                loginWP = BrowserstackMobileDriver.configBStack.loginWP();
                passwordWP = BrowserstackMobileDriver.configBStack.passwordWP();
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
        String sessionId = ""; // String sessionId = ""; DriverUtils.getSessionId()

        if (System.getProperty("deviceHost").equals("browserstack")) {
            sessionId = Attach.sessionId();
            Attach.video(sessionId);
        }

        AllureAttachments.addScreenshotAs("Last screenshot");

        if (System.getProperty("deviceHost").equals("web")) {
            AllureAttachments.addPageSource();
            AllureAttachments.addBrowserConsoleLogs();
        }
        Selenide.closeWebDriver();

        if (Project.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }

        if (System.getProperty("deviceHost").equals("browserstack")) {
            Attach.video(sessionId);
        }
    }
}
