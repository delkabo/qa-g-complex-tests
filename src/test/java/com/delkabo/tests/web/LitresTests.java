package com.delkabo.tests.web;

import com.delkabo.config.Project;
import com.delkabo.tests.TestBase;
import com.delkabo.tests.pages.PageObjectLitres;
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
@Tag("web")
public class LitresTests extends TestBase {

    PageObjectLitres pageObjectLitres = new PageObjectLitres();

    @Test
    @Tag("web")
    @AllureId("10001")
    @DisplayName("Тест авторизации")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Тест авторизации")
    @Description("Тест авторизации")
    void titleTest() {
        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("выбор иконки лк", () -> pageObjectLitres.openLogin());

//        step("ввод логина", () -> $(".AuthorizationPopup-module__input").setValue(Project.config.myLogin()));
//        step("клик по кнопке", () -> $(".childContainer-0-2-4").click());
        step("ввод логина", () -> pageObjectLitres.setLogin());


//        step("ввод пароля", () -> $(".AuthorizationPopup-module__input").setValue(Project.config.myPassword()));
//        step("подтверждение ввода пароля", () -> $(".childContainer-0-2-4").click());
        step("ввод пароля", () -> pageObjectLitres.setPassword());


        step("проверка присутствия иконки аватарки", () ->
                pageObjectLitres.checkLogin());
//                $(".Profile-module__profileLink").shouldHave(visible));
    }

    @Test
    @Tag("web")
    @AllureId("10002")
    @DisplayName("Найти книгу 'Война и Мир'")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Поиск книги")
    @Description("Найти книгу 'Война и Мир'")
    void findBook() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("ввод книги 'Война и Мир' в строку поиска", () -> {
//                $(".Search-module__input").setValue("Война и мир").pressEnter();
                pageObjectLitres.searchBook("Война и мир");
        });

        step("проверка нахождения книги 'Война и Мир' в списке", () -> {
//                $("#search__content").shouldHave(text("Война и мир"));
                pageObjectLitres.checkBook("Война и мир");
        });
    }

    @Test
    @Tag("web")
    @AllureId("10006")
    @DisplayName("Найти раздел Подборки")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Поиск необходимого раздела")
    @Description("Найти раздел Подборки")
    void jobTest() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("Кликнуть по кнопке 'Жанры'", () ->
//                $(".LowerMenu-module__popoverContentWrapp").click());
                pageObjectLitres.genresClick());

        step("Выбрать из списка 'Подборки'", () ->
//                $$(".MoreMenu-module__text").findBy(text("Подборки")).click());
                pageObjectLitres.selectGenres("Хобби, досуг"));

        step("Найти Название 'Подборки' на странице", () ->
//                $(".grouped__collection").shouldHave(text("Подборки")));
                pageObjectLitres.checkGenres("Хобби, досуг"));
    }

    @Test
    @Tag("web")
    @AllureId("10003")
    @DisplayName("Найти раздел 'Книжные бестселлеры'")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Поиск необходимого раздела")
    @Description("Найти раздел 'Книжные бестселлеры'")
    void checkPopular() {

        step("Открыть url 'https://www.litres.ru/'", () ->
                open("https://www.litres.ru/"));

        step("Выбрать раздел популярное из списка", () ->
//                $$(".LowerMenu-module__item").findBy(text("Популярное")).click());
                pageObjectLitres.clickTopMenuGenres("Популярное"));

        step("choose enter in lk", () ->
//                $(".sorting_menu_left").shouldHave(text("Популярное")));
                pageObjectLitres.checkTopMenuGenres("Популярное"));
    }

}