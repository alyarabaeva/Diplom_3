package page_objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainBurgerPage {
    public static final String PROD_URI = "https://stellarburgers.nomoreparties.site";
    private SelenideElement profile = $(byLinkText("Личный Кабинет"));
    private SelenideElement stellarBurgersLogo = $(byClassName("AppHeader_header__logo__2D0X2"));
    private SelenideElement constructorButton = $(byXpath("//a[@href='/']/p[text()='Конструктор']"));
    public SelenideElement siteTitle = $(byXpath("//h1[text()='Соберите бургер']"));
    private SelenideElement bunButton = $$(byXpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div")).get(0);
    private SelenideElement saucesButton = $$(byXpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div")).get(1);
    private SelenideElement fillingsButton = $$(byXpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div")).get(2);
    private SelenideElement bunTitle = $(byXpath("//h2[text()='Булки']"));
    private SelenideElement saucesTitle = $(byXpath("//h2[text()='Соусы']"));
    private SelenideElement fillingsTitle = $(byXpath("//h2[text()='Начинки']"));

    @Step("Open constructor page")
    public void checkOpenConstructorPage() {
        constructorButton.shouldBe(Condition.visible).click();
        // Проверка, что тайтл главной страницы отобразился
        siteTitle.shouldBe(Condition.visible);
        bunButton.shouldBe(Condition.visible);
        saucesButton.shouldBe(Condition.visible);
        fillingsButton.shouldBe(Condition.visible);
    }

    @Step("Open constructor page by logo")
    public void checkOpenConstructorPageFromLogo() {
        stellarBurgersLogo.shouldBe(Condition.visible).click();
        // Проверка, что тайтл главной страницы отобразился
        siteTitle.shouldBe(Condition.visible);
    }

    @Step("Open sauces section")
    public void checkOpenSaucesSection() {
        siteTitle.shouldBe(Condition.visible);
        saucesButton.shouldBe(Condition.visible).click();
        // проверка, что кнопка выбрана
        checkButtonSelection(saucesButton, "Соусы");
    }

    @Step("Open fillings section")
    public void checkOpenFillingsSection() {
        siteTitle.shouldBe(Condition.visible);
        fillingsButton.shouldBe(Condition.visible).click();
        // проверка, что кнопка выбрана
        checkButtonSelection(fillingsButton, "Начинки");
    }

    @Step("Transition to fillings section")
    public MainBurgerPage transitionToFillings() {
        fillingsButton.shouldBe(Condition.visible).click();
        checkButtonSelection(fillingsButton, "Начинки");
        return this;
    }

    @Step("Open buns section")
    public void checkOpenBunsSection() {
        siteTitle.shouldBe(Condition.visible);
        bunButton.shouldBe(Condition.visible).click();
        // проверка, что кнопка выбрана
        checkButtonSelection(bunButton, "Булки");
    }

    public void checkButtonSelection(SelenideElement element, String name) {
        element.shouldHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
        element.shouldHave(Condition.text(name));
    }
}
