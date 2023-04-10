import apiMethods.UserStep;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObjects.SignUpPage;
import apiMethods.User;

import static com.codeborne.selenide.Selenide.open;

public class SignUpTest {
    UserStep userStep = new UserStep();
    User user = new User("uitestuser3@yandex.ru", "Aa12345", "Ui User3");

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void registrationPositiveTest() {
        SignUpPage signUpPage =
                open("https://stellarburgers.nomoreparties.site/register", SignUpPage.class);
        signUpPage.createUser(user.getName(), user.getEmail(), user.getPassword());
    }

    @Test
    public void registrationIncorrectPasswordTest() {
        SignUpPage signUpPage =
                open("https://stellarburgers.nomoreparties.site/register", SignUpPage.class);
        signUpPage.createUserWithIncorrectPassword("user1", "user1@yandex.ru", "1234");
    }

    @After
    public void deleteUser() throws JsonProcessingException {
        String token = userStep.getAccessToken(user.getEmail(), user.getPassword());
        if (token != null) {
            userStep.deleteUser(token).then().statusCode(202);
        }
    }
}
