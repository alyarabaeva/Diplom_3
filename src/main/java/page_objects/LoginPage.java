package page_objects;

import api_methods.UserStep;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginTitle = $(byXpath(".//h2[text()='Вход']"));
    private SelenideElement emailField = $(byXpath("//input[@name='name']"));
    private SelenideElement passwordField = $(byXpath("//input[@name='Пароль']"));
    public SelenideElement loginButton = $(byXpath("//button[text()='Войти']"));
    public SelenideElement profile = $(byLinkText("Личный Кабинет"));
    public SelenideElement loginAccountButton = $(byXpath("//button[text()='Войти в аккаунт']"));
    public SelenideElement loginForgerPasswordButton = $(byLinkText("Войти"));

    @Step("Open login from main page")
    public void openProfileWithoutAuth(){
        profile.shouldBe(visible).click();
        // проверка что кнопка входа и тайтл Вход есть, значит пользователь перешел на страницу логина
        checkOpenLoginPage();
    }

    @Step("Open login from main page")
    public void checkOpenLoginPage(){
        loginTitle.shouldBe(visible);
        loginButton.shouldBe(visible);
        emailField.shouldBe(visible);
        passwordField.shouldBe(visible);
    }


    @Step("Enter user data")
    public void enterUserLoginData(String email, String password){
        loginTitle.shouldBe(visible);
        emailField.shouldBe(visible).setValue(email);
        passwordField.shouldBe(visible).setValue(password);
    }

    @Step("Login user")
    public void checkLoginUser(String email, String password){
        enterUserLoginData(email, password);
        loginButton.shouldBe(visible).click();
        // проверка, что кнопка входа пропала, значит пользователь залогинился
        loginButton.shouldBe(disappear);
    }

    @Step("Open login page")
    public LoginPage checkOpenLoginPage(SelenideElement loginElement){
        loginElement.shouldBe(visible);
        loginElement.click();
        // проверка что кнопка входа есть, значит пользователь перешел на страницу логина
        loginButton.shouldBe(visible);
        return this;
    }

}
