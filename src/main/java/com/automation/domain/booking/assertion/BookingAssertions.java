package com.automation.domain.booking.assertion;

import com.automation.models.booking.BookingRequest;
import com.automation.models.booking.BookingResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public final class BookingAssertions {

    private BookingAssertions() {
    }

    public static BookingResponse assertCreateBookingSuccess(Response response, BookingRequest expectedRequest, File schemaFile) {
        response.then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(schemaFile));

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        if (bookingResponse.getBookingid() <= 0) {
            throw new AssertionError("Booking ID must be greater than 0");
        }

        response.then().body("booking.firstname", equalTo(expectedRequest.getFirstname()))
                .body("booking.lastname", equalTo(expectedRequest.getLastname()));

        return bookingResponse;
    }

    public static void assertGetBookingSuccess(Response response, BookingRequest expectedRequest) {
        response.then().statusCode(200)
                .body("firstname", equalTo(expectedRequest.getFirstname()))
                .body("lastname", equalTo(expectedRequest.getLastname()));
    }

    public static void assertUpdateBookingSuccess(Response response, String updatedFirstName) {
        response.then().statusCode(200)
                .body("firstname", equalTo(updatedFirstName));
    }

    public static void assertDeleteBookingSuccess(Response response) {
        response.then().statusCode(201);
    }
}
