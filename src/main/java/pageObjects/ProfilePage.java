package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    private SelenideElement profile = $(byLinkText("Личный Кабинет"));
    private SelenideElement profileButton = $(byLinkText("Профиль"));
    private SelenideElement ordersHistory = $(byLinkText("История заказов"));
    private SelenideElement logoutButton = $(byXpath("//button[text()='Выход']"));
    private SelenideElement nameField = $(byXpath("//label[text()='Имя']"));
    private SelenideElement emailField = $(byXpath("//label[text()='Логин']"));
    private SelenideElement passwordField = $(byXpath("//label[text()='Пароль']"));
    private SelenideElement saveButton = $(byXpath("//button[text()='Сохранить']"));

    @Step("Transition to profile page")
    public void openProfileWithAuth() {
        profile.shouldBe(Condition.visible).click();
        profileButton.shouldBe(Condition.visible);
        nameField.shouldBe(Condition.visible);
        emailField.shouldBe(Condition.visible);
    }

    @Step("Logout user")
    public void logoutUser() {
        logoutButton.shouldBe(Condition.visible).click();
        profileButton.shouldBe(Condition.disappear);
        logoutButton.shouldBe(Condition.disappear);
    }
}
