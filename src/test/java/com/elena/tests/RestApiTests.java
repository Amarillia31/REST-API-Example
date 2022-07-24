package com.elena.tests;

import com.elena.config.UserConfig;
import com.elena.models.BookingData;
import com.elena.models.Booking;
import com.elena.models.BookingDates;
import com.elena.models.User;
import io.qameta.allure.AllureId;
import jdk.jfr.Description;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static com.elena.specs.Specs.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestApiTests extends BaseTestAPI {
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
        System.out.println("Token: " + token);
    }

    @Test
    @AllureId("11286")
    @Description("Check it is possible to add booking")
    void addBookingTest() {
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

        Booking data = given()
                .spec(request)
                .body(body)
                .when()
                .post("booking")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .extract().as(Booking.class);
        assertEquals(config.lastname(), data.getBooking().getLastname());
        assertEquals(config.firstname(), data.getBooking().getFirstname());
        assertEquals(config.totalprice(), data.getBooking().getTotalprice());
        assertEquals(config.depositpaid(), data.getBooking().getDepositpaid());
        assertEquals(config.checkin(), data.getBooking().getBookingdates().getCheckin());
        assertEquals(config.checkout(), data.getBooking().getBookingdates().getCheckout());
        assertEquals(config.additionalneeds(), data.getBooking().getAdditionalneeds());
    }

    @Test
    @AllureId("11287")
    @Description("Update non-existent Booking")
    void updatedNonExistentBooking() {
        AuthPrecondition ids = new AuthPrecondition();
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

        given()
                .spec(request)
                .header("Cookie", "token=" + ids.authToken())
                .body(body)
                .when()
                .put("booking/-1")
                .then().log().body()
                .spec(response405);
    }

    @Test
    @AllureId("11285")
    @Description("Update Booking")
    void updatedBooking() {
        AuthPrecondition ids = new AuthPrecondition();
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

        BookingData data = given()
                .spec(request)
                .header("Cookie", "token=" + ids.authToken())
                .pathParam("id", ids.getBookingId())
                .body(body)
                .when()
                .put("booking/{id}")
                .then().log().body()
                .spec(responseSpec)
                .extract().as(BookingData.class);
        assertEquals(config.lastname(), data.getLastname());
        assertEquals(config.firstname(), data.getFirstname());
        assertEquals(config.totalprice(), data.getTotalprice());
        assertEquals(config.depositpaid(), data.getDepositpaid());
        assertEquals(config.checkin(), data.getBookingdates().getCheckin());
        assertEquals(config.checkout(), data.getBookingdates().getCheckout());
        assertEquals(config.additionalneeds(), data.getAdditionalneeds());
    }

    @Test
    @AllureId("11284")
    @Description("It is possible to Delete Booking")
    void deleteBooking() {
        AuthPrecondition ids = new AuthPrecondition();
        given()
                .spec(request)
                .header("Cookie", "token=" + ids.authToken())
                .pathParam("id", ids.getBookingId())
                .when()
                .delete("booking/{id}")
                .then().log().all()
                .spec(response201);
    }
}
