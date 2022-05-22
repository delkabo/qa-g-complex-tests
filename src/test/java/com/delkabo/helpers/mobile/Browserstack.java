package com.delkabo.helpers.mobile;

import static com.delkabo.drivers.mobile.BrowserstackMobileDriver.configBStack;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

    public static String
            browserstackLogin = configBStack.loginBS(),
            browserstackPassword = configBStack.passwordBS();

    public static String videoUrl(String sessionId) {
        String url = format("https://api-cloud.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(browserstackLogin, browserstackPassword)
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
