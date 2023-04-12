import api_methods.UserStep;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page_objects.SignUpPage;
import api_methods.User;

import static com.codeborne.selenide.Selenide.open;
import static page_objects.MainBurgerPage.PROD_URI;

public class SignUpTest {
    UserStep userStep = new UserStep();
    User user = new User("uitestuser3@yandex.ru", "Aa12345", "Ui User3");

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/ayarabaeva/yandexdriver");
        System.setProperty("selenide.browser", "Chrome");
        RestAssured.baseURI = PROD_URI;
    }

    @Test
    public void registrationPositiveTest() {
        SignUpPage signUpPage =
                open(PROD_URI + "/register", SignUpPage.class);
        signUpPage.checkUserCreation(user.getName(), user.getEmail(), user.getPassword());
    }

    @Test
    public void registrationIncorrectPasswordTest() {
        SignUpPage signUpPage =
                open(PROD_URI + "/register", SignUpPage.class);
        signUpPage.checkUserCreationWithIncorrectPassword("user1", "user1@yandex.ru", "1234");
    }

    @After
    public void deleteUser() throws JsonProcessingException {
        String token = userStep.getAccessToken(user.getEmail(), user.getPassword());
        if (token != null) {
            userStep.deleteUser(token).then().statusCode(202);
        }
    }
}
