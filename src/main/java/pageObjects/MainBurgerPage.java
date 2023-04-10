package pageObjects;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainBurgerPage {
    private SelenideElement profile = $(byLinkText("Личный Кабинет"));
    private SelenideElement stellarBurgersLogo = $(byClassName("AppHeader_header__logo__2D0X2"));
    private SelenideElement constructorButton = $(byXpath("//a[@href='/']/p[text()='Конструктор']"));
    public SelenideElement siteTitle = $(byXpath("//h1[text()='Соберите бургер']"));
    private SelenideElement bunButton = $(byXpath("//div/span[text()='Булки']"));
    private SelenideElement saucesButton = $(byXpath("//div/span[text()='Соусы']"));
    private SelenideElement fillingsButton = $(byXpath("//div/span[text()='Начинки']"));
    private SelenideElement bunTitle = $(byXpath("//h2[text()='Булки']"));
    private SelenideElement saucesTitle = $(byXpath("//h2[text()='Соусы']"));
    private SelenideElement fillingsTitle = $(byXpath("//h2[text()='Начинки']"));

    @Step
    public void openConstructorPage(){
        constructorButton.shouldBe(Condition.visible).click();
        siteTitle.shouldBe(Condition.visible);
    }

    @Step
    public void openConstructorPageFromLogo(){
        stellarBurgersLogo.shouldBe(Condition.visible).click();
        siteTitle.shouldBe(Condition.visible);
    }


    @Step("Open sauces section")
    public void openSaucesSection(){
        siteTitle.shouldBe(Condition.visible);
        saucesButton.shouldBe(Condition.visible).click();
        saucesTitle.shouldBe(Condition.visible);
    }

    @Step("Open fillings section")
    public void openFillingsSection(){
        siteTitle.shouldBe(Condition.visible);
        fillingsButton.shouldBe(Condition.visible).click();
        fillingsTitle.shouldBe(Condition.visible);
    }

    @Step("Open buns section")
    public void openBunsSection(){
        siteTitle.shouldBe(Condition.visible);
        saucesButton.shouldBe(Condition.visible).click();
        bunButton.shouldBe(Condition.visible).click();
        bunTitle.shouldBe(Condition.visible);
    }
}
