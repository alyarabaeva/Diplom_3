package pageObjects;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SignUpPage {
    private SelenideElement registrationTitle = $(byXpath(".//h2[text()='Регистрация']"));
    private SelenideElement nameField = $(byXpath("//input[@name='name']"));
    private SelenideElement emailField = $$(byXpath("//input[@name='name']")).last();
    private SelenideElement passwordField = $(byXpath("//input[@name='Пароль']"));
    private SelenideElement signUpButton = $(byXpath("//button[text()='Зарегистрироваться']"));
    private SelenideElement errorPassword = $(byXpath("//p[text()='Некорректный пароль']"));

    @Step("Enter user data")
    public void enterUserData(String name, String email, String password) {
        registrationTitle.shouldBe(visible);
        nameField.shouldBe(visible).setValue(name);
        emailField.shouldBe(visible).setValue(email);
        passwordField.shouldBe(visible).setValue(password);
    }

    @Step("Positive sign up")
    public void createUser(String name, String email, String password) {
        enterUserData(name, email, password);
        signUpButton.shouldBe(visible).click();
        signUpButton.shouldBe(Condition.disappear);
    }

    @Step("Error sign up")
    public void createUserWithIncorrectPassword(String name, String email, String password){
        enterUserData(name, email, password);
        signUpButton.shouldBe(visible).click();
        errorPassword.shouldBe(visible);
        signUpButton.shouldBe(visible);
    }


}
