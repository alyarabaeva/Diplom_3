import api_methods.User;
import api_methods.UserStep;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page_objects.LoginPage;
import page_objects.ProfilePage;
import page_objects.SignUpPage;

import static com.codeborne.selenide.Selenide.open;
import static page_objects.MainBurgerPage.PROD_URI;

@RunWith(Parameterized.class)
public class LoginParametrizedTest {
    UserStep userStep = new UserStep();
    User user = new User("uitestuser1@yandex.ru", "Aa12345", "Ui User1");
    static LoginPage loginPage = new LoginPage();
    static SignUpPage signUpPage = new SignUpPage();

    private final SelenideElement enterButton;
    private final String openLink;

    public LoginParametrizedTest(SelenideElement enterButton, String openLink) {
        this.enterButton = enterButton;
        this.openLink = openLink;
    }

    @Before
    public void setUp() throws JsonProcessingException {
        System.setProperty("webdriver.chrome.driver", "/Users/ayarabaeva/yandexdriver");
        System.setProperty("selenide.browser", "Chrome");

        RestAssured.baseURI = PROD_URI;
        userStep.createUser(user.getEmail(), user.getPassword(), user.getName());
    }

    @Parameterized.Parameters
    public static Object[][] enterData() {
        return new Object[][]{
                {loginPage.loginAccountButton, PROD_URI},
                {loginPage.profile, PROD_URI},
                {signUpPage.loginButton, PROD_URI + "/register"},
                {loginPage.loginForgerPasswordButton, PROD_URI + "/forgot-password"},
        };
    }

    @Test
    public void loginUserTest() {
        open(openLink, LoginPage.class)
                .checkOpenLoginPage(enterButton)
                .checkLoginUser(user.getEmail(), user.getPassword());
        ProfilePage profilePage = new ProfilePage();
        profilePage.openProfileWithAuth();
    }

    @After
    public void deleteUser() throws JsonProcessingException {
        String token = userStep.getAccessToken(user.getEmail(), user.getPassword());
        if (token != null) {
            userStep.deleteUser(token).then().statusCode(202);
        }
    }
}
