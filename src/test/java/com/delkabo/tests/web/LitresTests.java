package com.delkabo.tests.web;

import com.delkabo.tests.TestBase;
import com.delkabo.tests.pages.PageObjectLitres;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Owner("syapuckovkr")
@Tag("web")
public class LitresTests extends TestBase {

    PageObjectLitres pageObjectLitres = new PageObjectLitres();

    @Tag("web")
    @Test
    @AllureId("10001")
    @DisplayName("Тест авторизации")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Тест авторизации")
    @Description("Тест авторизации")
    void titleTest() {
        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("выбор иконки лк", () -> pageObjectLitres.openLogin());

        step("ввод логина", () -> pageObjectLitres.setLogin());

        step("ввод пароля", () -> pageObjectLitres.setPassword());


        step("проверка присутствия иконки аватарки", () ->
                pageObjectLitres.checkLogin());
    }

    @Tag("web")
    @Test
    @AllureId("10002")
    @DisplayName("Найти книгу 'Война и Мир'")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Поиск книги")
    @Description("Найти книгу 'Война и Мир'")
    void findBook() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("ввод книги 'Война и Мир' в строку поиска", () -> {
            pageObjectLitres.searchBook("Война и мир");
        });

        step("проверка нахождения книги 'Война и Мир' в списке", () -> {
            pageObjectLitres.checkBook("Война и мир");
        });
    }

    @Tag("web")
    @ValueSource(strings = {"Хобби, досуг", "Знания и навыки"})
    @ParameterizedTest(name = "Поиск товара по параметру: {0}")
    @AllureId("10006")
    @DisplayName("Найти раздел")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Поиск необходимого раздела")
    @Description("Найти раздел")
    void checkGenres(String genres) {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("Кликнуть по кнопке 'Жанры'", () ->
                pageObjectLitres.genresClick());

        step("Выбрать из списка 'Хобби, досуг'", () ->
                pageObjectLitres.selectGenres(genres));

        step("Найти Название 'Хобби, досуг' на странице", () ->
                pageObjectLitres.checkGenres(genres));
    }

    @Tag("web")
    @Test
    @AllureId("10003")
    @DisplayName("Найти раздел 'Книжные бестселлеры'")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Поиск необходимого раздела")
    @Description("Найти раздел 'Подборки'")
    void checkTopMenu() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("Нажать кнопку Еще", () ->
        pageObjectLitres.clickMore());

        step("Выбрать раздел популярное из списка", () ->
                pageObjectLitres.clickTopMenuGenres("Подборки"));

        step("choose enter in lk", () ->
                pageObjectLitres.checkTopMenuGenres("Подборки"));
    }

    @Tag("web")
    @Test
    @AllureId("10004")
    @DisplayName("Проверить раздел 'Популярное' 'Книжные бестселлеры'")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Поиск необходимого раздела")
    @Description("Проверить раздел 'Популярное' 'Книжные бестселлеры'")
    void checkSection() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("Кликнуть по кнопке жанры", () ->
                pageObjectLitres.selectButtonGenres("Популярное"));

        step("Найти в списке 'Хобби, досуг'", () ->
                pageObjectLitres.findAndCheck("Книжные бестселлеры"));
    }

}