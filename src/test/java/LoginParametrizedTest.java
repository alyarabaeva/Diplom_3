import apiMethods.User;
import apiMethods.UserStep;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObjects.LoginPage;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@RunWith(Parameterized.class)
public class LoginParametrizedTest {
    UserStep userStep = new UserStep();
    User user = new User("uitestuser1@yandex.ru", "Aa12345", "Ui User1");

    private final SelenideElement enterButton;
    private final String openLink;

    public LoginParametrizedTest(SelenideElement enterButton, String openLink) {
        this.enterButton = enterButton;
        this.openLink = openLink;
    }

    @Before
    public void setUp() throws JsonProcessingException {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        userStep.createUser(user.getEmail(), user.getPassword(), user.getName());
    }

    @Parameterized.Parameters
    public static Object[][] enterData() {
        return new Object[][]{
                {$(byXpath("//button[text()='Войти в аккаунт']")), "https://stellarburgers.nomoreparties.site/"},
                {$(byLinkText("Личный Кабинет")), "https://stellarburgers.nomoreparties.site/"},
                {$(byLinkText("Войти")), "https://stellarburgers.nomoreparties.site/register"},
                {$(byLinkText("Войти")), "https://stellarburgers.nomoreparties.site/forgot-password"},
        };
    }

    @Test
    public void loginUserTest() {
        open(openLink, LoginPage.class)
                .openLoginPage(enterButton)
                .loginUser(user.getEmail(), user.getPassword());
    }

    @After
    public void deleteUser() throws JsonProcessingException {
        String token = userStep.getAccessToken(user.getEmail(), user.getPassword());
        if (token != null) {
            userStep.deleteUser(token).then().statusCode(202);
        }
    }
}
