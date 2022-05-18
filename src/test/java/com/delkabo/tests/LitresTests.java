package com.delkabo.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.Selectors.byText;
import static io.restassured.RestAssured.given;


public class LitresTests extends TestBase {

    @Test
    void restTest() {
        String document =
        given()
                .body("")
                .when()
                .get("https://www.litres.ru/")
                .then()
                .extract().asString();

    }

    @Test
    @Description("Тест авторизации")
    @DisplayName("Test 1")
    void titleTest() {
        String myLogin = System.getProperty("myLogin");
        String myPassword = System.getProperty("myPassword");

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("выбор иконки лк", () ->
                $(".Login-module__loginLink").click());

        step("выбор ", () -> $(".Button-module__primary_orange").click());

        step("ввод логина", () -> $(".AuthorizationPopup-module__input").setValue(myLogin));

        step("клик по кнопке", () -> $(".childContainer-0-2-4").click());

        step("ввод пароля", () -> $(".AuthorizationPopup-module__input").setValue(myPassword));

        step("подтверждение ввода пароля", () -> $(".childContainer-0-2-4").click());

        step("проверка присутствия иконки аватарки", () ->
                $(".Profile-module__profileLink").shouldHave(visible));
    }

    @Test
    @Description("Найти книгу 'Война и Мир'")
    @DisplayName("Test 2")
    void findBook() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("ввод книги 'Война и Мир' в строку поиска", () ->
                $(".Search-module__input").setValue("Война и мир").pressEnter());

        step("проверка нахождения книги 'Война и Мир' в списке", () ->
                $("#search__content").shouldHave(text("Война и мир")));
    }

    @Test
    @Description("Найти раздел Подработки")
    @DisplayName("Test 3")
    void test1() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("Кликнуть по кнопке 'Жанры'", () ->
                $(".LowerMenu-module__popoverContentWrapp").click());

        step("Выбрать из списка 'Подборки'", () ->
                $$(".MoreMenu-module__text").findBy(text("Подборки")).click());

        step("Найти Название 'Подборки' на странице", () ->
                $(".grouped__collection").shouldHave(text("Подборки")));
    }

    @Test
    @Description("Найти раздел 'Книжные бестселлеры'")
    @DisplayName("Test 4")
    void checkPopular() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("Выбрать раздел популярное из списка", () ->
                $$(".LowerMenu-module__item").findBy(text("Популярное")).click());

        step("choose enter in lk", () ->
                $(".book_ratings").shouldHave(text("Книжные бестселлеры")));
    }

    @Test
    @Description("Проверить раздел 'хобби и досуг'")
    @DisplayName("Test 5")
    void test3() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("Кликнуть по кнопке жанры", () ->
                $(".LowerMenu-module__genres").click());

        step("Найти в списке 'Хобби, досуг'", () ->
                $("div[data-header-popup=\"genres\"]").$(byText("Хобби, досуг")).click());

        step("Проверить название 'Хобби, досуг' на странице", () ->
                $(".new-container").shouldHave(text("Хобби, досуг")));
    }

}