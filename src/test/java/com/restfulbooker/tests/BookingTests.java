package com.restfulbooker.tests;

import com.restfulbooker.config.UserConfig;
import com.restfulbooker.models.BookingData;
import com.restfulbooker.models.Booking;
import com.restfulbooker.models.BookingDates;
import com.restfulbooker.models.User;
import com.restfulbooker.precondition.AuthPrecondition;
import io.qameta.allure.AllureId;
import jdk.jfr.Description;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static com.restfulbooker.specs.Specs.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingTests extends TestBase {
    UserConfig config = ConfigFactory.create(UserConfig.class);

    @Test
    @AllureId("11286")
    @Description("Check it is possible to add booking")
    void addBookingTest() {
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

        assertEquals(config.lastName(), data.getBooking().getLastName());
        assertEquals(config.firstName(), data.getBooking().getFirstName());
        assertEquals(config.totalPrice(), data.getBooking().getTotalPrice());
        assertEquals(config.depositPaid(), data.getBooking().getDepositPaid());
        assertEquals(config.checkIn(), data.getBooking().getBookingDates().getCheckIn());
        assertEquals(config.checkOut(), data.getBooking().getBookingDates().getCheckOut());
        assertEquals(config.additionalNeeds(), data.getBooking().getAdditionalNeeds());
    }

    @Test
    @AllureId("11287")
    @Description("Update non-existent Booking")
    void updatedNonExistentBooking() {
        AuthPrecondition ids = new AuthPrecondition();

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

        assertEquals(config.lastName(), data.getLastName());
        assertEquals(config.firstName(), data.getFirstName());
        assertEquals(config.totalPrice(), data.getTotalPrice());
        assertEquals(config.depositPaid(), data.getDepositPaid());
        assertEquals(config.checkIn(), data.getBookingDates().getCheckIn());
        assertEquals(config.checkOut(), data.getBookingDates().getCheckOut());
        assertEquals(config.additionalNeeds(), data.getAdditionalNeeds());
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
