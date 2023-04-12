import org.junit.Before;
import org.junit.Test;
import page_objects.LoginPage;
import page_objects.MainBurgerPage;

import static com.codeborne.selenide.Selenide.open;
import static page_objects.MainBurgerPage.PROD_URI;

public class MainPageTest {
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/ayarabaeva/yandexdriver");
        System.setProperty("selenide.browser", "Chrome");
    }

    @Test
    public void transitionToProfileTest() {
        LoginPage loginPage = open(PROD_URI, LoginPage.class);
        loginPage.openProfileWithoutAuth();
    }

    @Test
    public void transitionToConstructorTest() {
        MainBurgerPage mainBurgerPage = open(PROD_URI + "/login", MainBurgerPage.class);
        mainBurgerPage.checkOpenConstructorPage();
    }

    @Test
    public void transitionToConstructorFromLogoTest() {
        MainBurgerPage mainBurgerPage = open(PROD_URI + "/login", MainBurgerPage.class);
        mainBurgerPage.checkOpenConstructorPageFromLogo();
    }

    @Test
    public void openBunsSectionTest() {
        MainBurgerPage mainBurgerPage = open(PROD_URI + "/login", MainBurgerPage.class);
        mainBurgerPage.checkOpenConstructorPage();
        mainBurgerPage.transitionToFillings();
        mainBurgerPage.checkOpenBunsSection();
    }

    @Test
    public void openSaucesSectionTest() {
        open(PROD_URI, MainBurgerPage.class).checkOpenSaucesSection();
    }

    @Test
    public void openFillingsSectionTest() {
        open(PROD_URI, MainBurgerPage.class).checkOpenFillingsSection();
    }

}
