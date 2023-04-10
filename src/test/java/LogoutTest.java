import apiMethods.User;
import apiMethods.UserStep;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObjects.LoginPage;
import pageObjects.ProfilePage;

import static com.codeborne.selenide.Selenide.open;

public class LogoutTest {
    UserStep userStep = new UserStep();
    User user = new User("uitestuser1@yandex.ru", "Aa12345", "Ui User1");

    @Before
    public void setUp() throws JsonProcessingException {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        userStep.createUser(user.getEmail(), user.getPassword(), user.getName());
    }

    @Test
    public void logoutUserTest() {
        open("https://stellarburgers.nomoreparties.site/login", LoginPage.class)
                .loginUser(user.getEmail(), user.getPassword());
        ProfilePage profilePage = new ProfilePage();
        profilePage.openProfileWithAuth();
        profilePage.logoutUser();
    }

    @After
    public void deleteUser() throws JsonProcessingException {
        String token = userStep.getAccessToken(user.getEmail(), user.getPassword());
        if (token != null) {
            userStep.deleteUser(token).then().statusCode(202);
        }
    }
}
