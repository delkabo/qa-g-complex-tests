package com.delkabo.tests.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.SelenideElementDescriber;
import com.delkabo.config.Project;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PageObjectLitres {

    SelenideElement
                    loginModule = $(".Login-module__loginLink"),
                    primaryOrange = $(".Button-module__primary_orange"),
                    authorizationLogin = $(".AuthorizationPopup-module__input"),
                    submitContainer = $(".childContainer-0-2-4"),
                    authorizationPassword = $(".AuthorizationPopup-module__input"),
                    profileSection = $(".Profile-module__profileLink"),
                    searchModule = $(".Search-module__input"),
                    searchContent = $("#search__content"),
                    genresButton = $(".LowerMenu-module__popoverContentWrapp"),
                    groupCollection = $(".biblio-genres-uplink"),
                    groupLeftMenu = $(".sorting_menu_left");

    public PageObjectLitres openLogin() {
        loginModule.click();
        primaryOrange.click();
        return this;
    }

    public PageObjectLitres setLogin() {
        authorizationLogin.setValue(Project.config.myLogin());
        submitContainer.click();
        return this;
    }

    public PageObjectLitres setPassword() {
        authorizationPassword.setValue(Project.config.myPassword());
        submitContainer.click();
        return this;
    }

    public PageObjectLitres checkLogin() {
        profileSection.shouldHave(visible);
        return this;
    }

    public PageObjectLitres searchBook(String book) {
        searchModule.setValue(book).pressEnter();
        return this;
    }

    public PageObjectLitres checkBook(String book) {
        searchContent.shouldHave(text(book));
        return this;
    }

    public  PageObjectLitres genresClick() {
        genresButton.click();
        return this;
    }

    public PageObjectLitres selectGenres(String genres) {
        $$(".Column-module__title").findBy(text(genres)).click();
        return this;
    }

    public PageObjectLitres checkGenres(String genres) {
        groupCollection.shouldHave(text(genres));
        return this;
    }

    public PageObjectLitres clickTopMenuGenres(String genres) {
        $$(".LowerMenu-module__item").findBy(text(genres)).click();
        return this;
    }

    public PageObjectLitres checkTopMenuGenres(String genres) {
        groupLeftMenu.shouldHave(text(genres));
        return this;
    }


}
