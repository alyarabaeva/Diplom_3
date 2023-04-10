package apiMethods;

import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.response.Response;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserStep {
    @Step("Creat new user")
    public Response createUser(String email, String password, String name) throws JsonProcessingException {
        String json = getJsonWithThreeParams("email", email, "password", password, "name", name);
        return given()
                .header("Content-type", "application/json; charset=utf-8")
                .and()
                .body(json)
                .when()
                .post("/api/auth/register");
    }

    @Step("Login user")
    public Response loginUser(String email, String password) throws JsonProcessingException {
        String json = getJsonWithTwoParams("email", email, "password", password);
        return given()
                .header("Content-type", "application/json; charset=utf-8")
                .and()
                .body(json)
                .when()
                .post("/api/auth/login");
    }

    @Step("Login")
    public void login(String email, String password) throws JsonProcessingException {
        String json = getJsonWithTwoParams("email", email, "password", password);
        given()
                .header("Content-type", "application/json; charset=utf-8")
                .and()
                .body(json)
                .when()
                .post("/api/auth/login");
    }

    @Step("Get access token")
    public String getAccessToken(String email, String password) throws JsonProcessingException {
        return loginUser(email, password).as(AuthUserResponse.class).getAccessToken();
    }

    @Step("Delete user")
    public Response deleteUser(String token) {
        return given()
                .headers("authorization", token)
                .delete("/api/auth/user");
    }

    public String getJsonWithThreeParams(String param1, String value1, String param2, String value2, String param3, String value3) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        map.put(param1, value1);
        map.put(param2, value2);
        map.put(param3, value3);
        return objectMapper.writeValueAsString(map);
    }

    public String getJsonWithTwoParams(String param1, String value1, String param2, String value2) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        map.put(param1, value1);
        map.put(param2, value2);
        return objectMapper.writeValueAsString(map);
    }
}

