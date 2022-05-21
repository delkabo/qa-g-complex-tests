//package com.delkabo.tests.mobile;
//
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.Selenide;
//import com.delkabo.drivers.web.WebDriver;
//import com.delkabo.mobile.config.Project;
//import com.delkabo.mobile.drivers.BrowserstackMobileDriver;
//import com.delkabo.mobile.drivers.EmulatorMobileDriver;
//import com.delkabo.mobile.helpers.Attach;
//import io.qameta.allure.selenide.AllureSelenide;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//
//import static com.codeborne.selenide.Selenide.open;
//import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
//import static io.qameta.allure.Allure.step;
//
//public class TestBase {
//
//    @BeforeAll
//    public static void setup() {
//
//    addListener("AllureSelenide", new AllureSelenide());
//
//        switch (System.getProperty("deviceHost")) {
//            case "web":
//                WebDriver.configure();
//            case "real":
//            case "emulation":
//                Configuration.browser = EmulatorMobileDriver.class.getName();
//                break;
//            case "browserstack":
//                Configuration.browser = BrowserstackMobileDriver.class.getName();
//                break;
//            default:
//                throw new IllegalArgumentException("need choose deviceHost");
//        }
//        Configuration.browserSize = null;
//    }
//
//    @BeforeEach
//    public void startDriver() {
//        open();
//    }
//
//    @AfterEach
//    public void afterEach() {
//
//        String sessionId = "";
//
//        if (Project.ifBrowserStack()) {
//            sessionId = Attach.sessionId();
//            Attach.video(sessionId);
//        }
//
//        Attach.screenshotAs("Last screenshot");
//        Attach.pageSource();
//
//        step("Close driver", Selenide::closeWebDriver);
//
//        if (Project.ifBrowserStack()) {
//            Attach.video(sessionId);
//        }
//
//    }
//}