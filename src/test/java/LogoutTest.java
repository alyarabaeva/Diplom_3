import api_methods.User;
import api_methods.UserStep;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page_objects.LoginPage;
import page_objects.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static page_objects.MainBurgerPage.PROD_URI;

public class LogoutTest {
    UserStep userStep = new UserStep();
    User user = new User("uitestuser1@yandex.ru", "Aa12345", "Ui User1");

    @Before
    public void setUp() throws JsonProcessingException {
        System.setProperty("webdriver.chrome.driver", "/Users/ayarabaeva/yandexdriver");
        System.setProperty("selenide.browser", "Chrome");
        RestAssured.baseURI = PROD_URI;
        userStep.createUser(user.getEmail(), user.getPassword(), user.getName());
    }

    @Test
    public void logoutUserTest() {
        LoginPage loginPage = open(PROD_URI + "/login", LoginPage.class);
        loginPage.checkLoginUser(user.getEmail(), user.getPassword());
        ProfilePage profilePage = new ProfilePage();
        profilePage.openProfileWithAuth();
        profilePage.checkLogoutUser();
        loginPage.checkOpenLoginPage();
    }

    @After
    public void deleteUser() throws JsonProcessingException {
        String token = userStep.getAccessToken(user.getEmail(), user.getPassword());
        if (token != null) {
            userStep.deleteUser(token).then().statusCode(202);
        }
    }

    @After
    public void closeBrowser(){
        Selenide.closeWebDriver();
    }
}
