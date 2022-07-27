package com.restfulbooker.precondition;

import com.restfulbooker.config.AuthUserConfig;
import com.restfulbooker.models.BookingData;
import com.restfulbooker.models.BookingDates;
import com.restfulbooker.models.User;
import com.restfulbooker.tests.TestBase;
import org.aeonbits.owner.ConfigFactory;

import static com.restfulbooker.specs.Specs.request;
import static com.restfulbooker.specs.Specs.responseSpec;
import static io.restassured.RestAssured.given;

public class AuthPrecondition extends TestBase {
    AuthUserConfig config = ConfigFactory.create(AuthUserConfig.class);

    public String authToken() {
        User user = new User();
        user.setPassword(config.password());
        user.setUsername(config.username());
        return given()
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

    public Integer getBookingId() {
        BookingDates dates = new BookingDates();
        dates.setCheckIn(config.checkIn());
        dates.setCheckOut(config.checkOut());

        BookingData body = new BookingData();
        body.setFirstName(config.firstName());
        body.setLastName(config.lastName());
        body.setTotalPrice(config.totalPrice());
        body.setDepositPaid(config.depositPaid());
        body.setBookingDates(dates);
        body.setAdditionalNeeds(config.additionalNeeds());

        return given()
                .spec(request)
                .body(body)
                .when()
                .post("booking")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .extract().path("bookingid");
    }
}
