package com.delkabo.tests.web;

import com.delkabo.config.Project;
import com.delkabo.tests.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.Selectors.byText;

@Owner("syapuckovkr")
public class LitresTests extends TestBase {


    @Test
    @AllureId("10001")
    @DisplayName("Тест авторизации")
    @Tag("ui")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Тест авторизации")
    @Description("Тест авторизации")
    void titleTest() {
        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("выбор иконки лк", () ->
                $(".Login-module__loginLink").click());

        step("выбор ", () -> $(".Button-module__primary_orange").click());

        step("ввод логина", () -> $(".AuthorizationPopup-module__input").setValue(Project.config.myLogin()));

        step("клик по кнопке", () -> $(".childContainer-0-2-4").click());

        step("ввод пароля", () -> $(".AuthorizationPopup-module__input").setValue(Project.config.myPassword()));

        step("подтверждение ввода пароля", () -> $(".childContainer-0-2-4").click());

        step("проверка присутствия иконки аватарки", () ->
                $(".Profile-module__profileLink").shouldHave(visible));
    }

    @Test
    @AllureId("10002")
    @DisplayName("Найти книгу 'Война и Мир'")
    @Tag("ui")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Поиск книги")
    @Description("Найти книгу 'Война и Мир'")
    void findBook() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("ввод книги 'Война и Мир' в строку поиска", () ->
                $(".Search-module__input").setValue("Война и мир").pressEnter());

        step("проверка нахождения книги 'Война и Мир' в списке", () ->
                $("#search__content").shouldHave(text("Война и мир")));
    }

    @Test
    @AllureId("10006")
    @DisplayName("Найти раздел Подборки")
    @Tag("ui")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Поиск необходимого раздела")
    @Description("Найти раздел Подборки")
    void jobTest() {

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
    @AllureId("10003")
    @DisplayName("Найти раздел 'Книжные бестселлеры'")
    @Tag("ui")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Поиск необходимого раздела")
    @Description("Найти раздел 'Книжные бестселлеры'")
    void checkPopular() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("Выбрать раздел популярное из списка", () ->
                $$(".LowerMenu-module__item").findBy(text("Популярное")).click());

        step("choose enter in lk", () ->
                $(".book_ratings").shouldHave(text("Книжные бестселлеры")));
    }

    @Test
    @AllureId("10007")
    @DisplayName("Проверить раздел 'хобби и досуг'")
    @Tag("ui")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Поиск необходимого раздела")
    @Description("Проверить раздел 'хобби и досуг'")
    void hobbieTest() {

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