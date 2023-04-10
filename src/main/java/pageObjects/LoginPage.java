package pageObjects;

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
    private SelenideElement loginButton = $(byXpath("//button[text()='Войти']"));
    private SelenideElement profile = $(byLinkText("Личный Кабинет"));

    @Step("Open login from main page")
    public void openProfileWithoutAuth(){
        profile.shouldBe(visible).click();
        loginTitle.shouldBe(visible);
        loginButton.shouldBe(visible);
    }


    @Step("Enter user data")
    public void enterUserLoginData(String email, String password){
        loginTitle.shouldBe(visible);
        emailField.shouldBe(visible).setValue(email);
        passwordField.shouldBe(visible).setValue(password);
    }

    @Step("Login user")
    public void loginUser(String email, String password){
        enterUserLoginData(email, password);
        loginButton.shouldBe(visible).click();
        loginButton.shouldBe(disappear);
    }

    @Step("Open login page")
    public LoginPage openLoginPage(SelenideElement loginElement){
        loginElement.shouldBe(visible);
        loginElement.click();
        loginButton.shouldBe(visible);
        return this;
    }
}
