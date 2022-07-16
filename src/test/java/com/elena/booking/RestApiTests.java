package com.elena.booking;

import com.elena.lombok.BookingData;
import com.elena.lombok.LombokBooking;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.elena.specs.Specs.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RestApiTests extends BaseTestAPI {
    Integer totalprice = 111;
    Boolean depositpaid = true;
    String authUserBody = "{ \"username\": \"admin\", \"password\": \"password123\" }",
            bodyAddBooking = "{ \"firstname\" : \"Jim\", \"lastname\" : \"Brown\", \"totalprice\" : 111, \"depositpaid\" : true, \"bookingdates\" : { \"checkin\" : \"2022-01-01\", \"checkout\" : \"2022-01-15\" }, \"additionalneeds\" : \"Breakfast\" }",
            firstname = "Jim", lastname = "Brown", additionalneeds = "Breakfast", checkin = "2022-01-01", checkout = "2022-01-15";

    @Test
    @Description("Login is accepted,status code 200 is sent ")
    void authTest() {
        String token = given()
                .spec(request)
                .body(authUserBody)
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
    @Description("Check it is possible to add booking")
    void addBookingTest() {
        LombokBooking data = given()
                .spec(request)
                .body(bodyAddBooking)
                .when()
                .post("booking")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .extract().as(LombokBooking.class);
        assertEquals(lastname, data.getBooking().getLastname());
        assertEquals(firstname, data.getBooking().getFirstname());
        assertEquals(totalprice, data.getBooking().getTotalprice());
        assertEquals(depositpaid, data.getBooking().getDepositpaid());
        assertEquals(checkin, data.getBooking().getBookingdates().getCheckin());
        assertEquals(checkout, data.getBooking().getBookingdates().getCheckout());
        assertEquals(additionalneeds, data.getBooking().getAdditionalneeds());
    }

    @Test
    @Description("Update Booking")
    void updatedNonExistentBooking() {
        Auth ids = new Auth();
        given()
                .spec(request)
                .header("Cookie", "token=" + ids.authToken())
                .body(bodyAddBooking)
                .when()
                .put("booking/-1")
                .then().log().body()
                .spec(response405);
    }

    @Test
    @Description("Update Booking")
    void updatedBooking() {
        Auth ids = new Auth();
        BookingData data = given()
                .spec(request)
                .header("Cookie", "token=" + ids.authToken())
                .pathParam("id", ids.getBookingId())
                .body(bodyAddBooking)
                .when()
                .put("booking/{id}")
                .then().log().body()
                .spec(responseSpec)
                .extract().as(BookingData.class);
        assertEquals(lastname, data.getLastname());
        assertEquals(firstname, data.getFirstname());
        assertEquals(totalprice, data.getTotalprice());
        assertEquals(depositpaid, data.getDepositpaid());
        assertEquals(checkin, data.getBookingdates().getCheckin());
        assertEquals(checkout, data.getBookingdates().getCheckout());
        assertEquals(additionalneeds, data.getAdditionalneeds());
    }

    @Test
    @Description("It is possible to Delete Booking")
    void deleteBooking() {
        Auth ids = new Auth();
        given()
                .spec(request)
                .header("Cookie", "token=" + ids.authToken())
                .pathParam("id", ids.getBookingId())
                .when()
                .delete("booking/{id}")
                .then().log().body()
                .spec(response201);
    }
}
