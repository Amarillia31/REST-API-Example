package com.restfulbooker.tests;

import com.restfulbooker.config.UserConfig;
import com.restfulbooker.models.User;
import io.qameta.allure.AllureId;
import jdk.jfr.Description;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static com.restfulbooker.specs.Specs.request;
import static com.restfulbooker.specs.Specs.responseSpec;
import static io.restassured.RestAssured.given;

public class LoginTests extends TestBase {
    UserConfig config = ConfigFactory.create(UserConfig.class);

    @Test
    @AllureId("11288")
    @Description("Login is accepted,status code 200 is sent ")
    void authTest() {
        User user = new User();
        user.setPassword(config.password());
        user.setUsername(config.username());

        String token = given()
                .spec(request)
                .body(user)
                .when()
                .post("auth")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .extract().path("token");
    }
}
