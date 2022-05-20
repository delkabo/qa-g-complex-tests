package com.delkabo.tests;

import com.delkabo.config.Project;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.Selectors.byText;


public class LitresTests extends TestBase {


    @Test
    @DisplayName("Найти книгу 'Война и Мир'")
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
    @Description("Найти книгу 'Война и Мир'")
    @DisplayName("Найти книгу 'Война и Мир'")
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
    @DisplayName("Найти раздел Подработки")
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
    @Description("Найти раздел 'Книжные бестселлеры'")
    @DisplayName("Найти раздел 'Книжные бестселлеры'")
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
    @DisplayName("Проверить раздел 'хобби и досуг'")
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