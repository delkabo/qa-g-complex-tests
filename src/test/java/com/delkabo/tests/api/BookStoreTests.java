package com.delkabo.tests.api;

import com.delkabo.tests.models.Credentials;
import com.delkabo.tests.models.GenerateTokenResponse;
import com.delkabo.tests.models.lombok.CredentialsLombok;
import com.delkabo.tests.models.lombok.GenerateTokenResponseLombok;
import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.delkabo.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static com.delkabo.tests.models.Specs.request;
import static com.delkabo.tests.models.Specs.responseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@Tag("api")
public class BookStoreTests {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://demoqa.com/";
    }

    @Test
    @Tag("api")
    void getBookWithSomeLogsTestSpec() {
        given()
                .spec(request)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .log().status()
                .log().body()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    @Tag("api")
    void generatedTokenTest() {
        String data = "{ \"userName\": \"alex\", " +
                "\"password\": \"asdsad#frew_DFS2\" }";

        given()
                .contentType(JSON)
                .body(data)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", greaterThan(10));
    }

    @Test
    @Tag("api")
    void generatedTokenTestSpec() {

        String data = "{ \"userName\": \"alex\", " +
                "\"password\": \"asdsad#frew_DFS2\" }";

        Credentials credentials = new Credentials();
        credentials.setUserName("alex");
        credentials.setPassword("asdsad#frew_DFS2");

        given()
                .spec(request)
                .body(data)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .spec(responseSpec)
                .log().status()
                .log().body()
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", greaterThan(10));

    }

    @Test
    @Tag("api")
    @Description("получить токен")
    void getTokenTest() {
        String data = "{ \"userName\": \"alex\", " +
                "\"password\": \"asdsad#frew_DFS2\" }";

        String token =
                given()
                        .contentType(JSON)
                        .body(data)
                        .log().uri()
                        .log().body()
                        .when()
                        .post("/Account/v1/GenerateToken")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .body("status", is("Success"))
                        .body("result", is("User authorized successfully."))
                        .extract().path("token");

        System.out.println("Token: " + token);
    }

    @Test
    @Tag("api")
    void generatedTokenWithAllureListenerTest() {
        String data = "{ \"userName\": \"alex\", " +
                "\"password\": \"asdsad#frew_DFS2\" }";

//        RestAssured.filters(new AllureRestAssured()); move to BeforeAll

        given()
                .filter(new AllureRestAssured())
                .contentType(JSON)
                .body(data)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", greaterThan(10));
    }

    @Test
    @Tag("api")
    void generatedTokenWithCustomAllureListenerTest() {
        String data = "{ \"userName\": \"alex\", " +
                "\"password\": \"asdsad#frew_DFS2\" }";

        given()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .body(data)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", greaterThan(10));
    }

    @Test
    @Tag("api")
    void generatedTokenWithModelTest() {

        Credentials credentials = new Credentials();
        credentials.setUserName("alex");
        credentials.setPassword("asdsad#frew_DFS2");

        GenerateTokenResponse tokenResponse =
        given()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .body(credentials)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/GeneratedToken_response_scheme.json"))
                .extract().as(GenerateTokenResponse.class);

        assertThat(tokenResponse.getStatus()).isEqualTo("Success");
        assertThat(tokenResponse.getResult()).isEqualTo("User authorized successfully.");
        assertThat(tokenResponse.getExpires()).hasSizeGreaterThan(10);
        assertThat(tokenResponse.getToken()).hasSizeGreaterThan(10).startsWith("eyJ");
    }

    @Test
    @Tag("api")
    void generatedTokenWithLombokTest() {

        CredentialsLombok credentials = new CredentialsLombok();
        credentials.setUserName("alex");
        credentials.setPassword("asdsad#frew_DFS2");

        GenerateTokenResponseLombok tokenResponse =
        given()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .body(credentials)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/GeneratedToken_response_scheme.json"))
                .extract().as(GenerateTokenResponseLombok.class);

        assertThat(tokenResponse.getStatus()).isEqualTo("Success");
        assertThat(tokenResponse.getResult()).isEqualTo("User authorized successfully.");
        assertThat(tokenResponse.getExpires()).hasSizeGreaterThan(10);
        assertThat(tokenResponse.getToken()).hasSizeGreaterThan(10).startsWith("eyJ");
    }


}

