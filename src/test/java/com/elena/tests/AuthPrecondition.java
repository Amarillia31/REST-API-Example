package com.elena.tests;

import com.elena.config.AuthUserConfig;
import com.elena.models.BookingData;
import com.elena.models.BookingDates;
import com.elena.models.User;
import org.aeonbits.owner.ConfigFactory;

import static com.elena.specs.Specs.request;
import static com.elena.specs.Specs.responseSpec;
import static io.restassured.RestAssured.given;

public class AuthPrecondition extends BaseTestAPI{
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
        BookingData body = new BookingData();
        BookingDates dates = new BookingDates();

        dates.setCheckin(config.checkin());
        dates.setCheckout(config.checkout());

        body.setFirstname(config.firstname());
        body.setLastname(config.lastname());
        body.setTotalprice(config.totalprice());
        body.setDepositpaid(config.depositpaid());
        body.setBookingdates(dates);
        body.setAdditionalneeds(config.additionalneeds());

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
