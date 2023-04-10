import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;
import pageObjects.LoginPage;
import pageObjects.MainBurgerPage;

import static com.codeborne.selenide.Selenide.open;

public class MainPageTest {
    @Test
    public void transitionToProfileTest() {
        LoginPage loginPage = open("https://stellarburgers.nomoreparties.site", LoginPage.class);
        loginPage.openProfileWithoutAuth();
    }

    @Test
    public void transitionToConstructorTest() {
        MainBurgerPage mainBurgerPage = open("https://stellarburgers.nomoreparties.site/login", MainBurgerPage.class);
        mainBurgerPage.openConstructorPage();
    }

    @Test
    public void transitionToConstructorFromLogoTest() {
        MainBurgerPage mainBurgerPage = open("https://stellarburgers.nomoreparties.site/login", MainBurgerPage.class);
        mainBurgerPage.openConstructorPageFromLogo();
    }

    @Test
    public void openBunsSectionTest() {
        open("https://stellarburgers.nomoreparties.site", MainBurgerPage.class).openBunsSection();
    }

    @Test
    public void openSaucesSectionTest() {
        open("https://stellarburgers.nomoreparties.site", MainBurgerPage.class).openSaucesSection();
    }

    @Test
    public void openFillingsSectionTest() {
        open("https://stellarburgers.nomoreparties.site", MainBurgerPage.class).openFillingsSection();
    }

}
