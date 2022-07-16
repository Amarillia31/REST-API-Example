package com.elena.booking;

import static com.elena.specs.Specs.request;
import static com.elena.specs.Specs.responseSpec;
import static io.restassured.RestAssured.given;

public class Auth extends BaseTestAPI{

    public String authToken() {
        String authUserBody = "{ \"username\": \"admin\", \"password\": \"password123\" }";
        return given()
                .spec(request)
                .body(authUserBody)
                .when()
                .post("auth")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .extract().path("token");
    }

    public Integer getBookingId() {
        String bodyAddBooking = "{ \"firstname\" : \"Helen\", \"lastname\" : \"Green\", \"totalprice\" : 111, \"depositpaid\" : true, \"bookingdates\" : { \"checkin\" : \"2022-01-01\", \"checkout\" : \"2022-01-15\" }, \"additionalneeds\" : \"Breakfast\" }";
        return given()
                .spec(request)
                .body(bodyAddBooking)
                .when()
                .post("booking")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .extract().path("bookingid");
    }

}
