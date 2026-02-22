package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.constants.EndPointGlobal;
import com.automation.keywords.ApiKeyword;
import com.automation.keywords.Authentication;
import com.automation.models.booking.BookingRequest;
import com.automation.models.booking.BookingResponse;
import com.automation.utils.LogUtils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class BookingTest extends BaseTest {

    private int bookingId;
    private BookingRequest bookingRequest;

    @BeforeClass
    public void setup() {
        Authentication.login();
        bookingRequest = BookingRequest.builder()
                .firstname("Jim")
                .lastname("Brown")
                .totalprice(111)
                .depositpaid(true)
                .bookingdates(BookingRequest.BookingDates.builder()
                        .checkin("2023-01-01")
                        .checkout("2023-01-02")
                        .build())
                .additionalneeds("Breakfast")
                .build();
    }

    @Test(priority = 1)
    public void createBooking() {
        LogUtils.info("Test: Create Booking");
        Response response = ApiKeyword.post(EndPointGlobal.BOOKING, bookingRequest);
        response.then().statusCode(200);
        response.then().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/schemas/booking_schema.json")));

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        bookingId = bookingResponse.getBookingid();
        
        Assert.assertEquals(bookingResponse.getBooking().getFirstname(), bookingRequest.getFirstname());
        LogUtils.info("Created Booking ID: " + bookingId);
    }

    @Test(priority = 2)
    public void getBooking() {
        LogUtils.info("Test: Get Booking " + bookingId);
        Response response = ApiKeyword.get(EndPointGlobal.BOOKING + "/" + bookingId);
        response.then().statusCode(200)
                .body("firstname", equalTo(bookingRequest.getFirstname()))
                .body("lastname", equalTo(bookingRequest.getLastname()));
    }

    @Test(priority = 3)
    public void updateBooking() {
        LogUtils.info("Test: Update Booking " + bookingId);
        bookingRequest.setFirstname("James");
        
        // Auth is handled automatically by ApiKeyword.put
        Response response = ApiKeyword.put(EndPointGlobal.BOOKING + "/" + bookingId, bookingRequest);
        response.then().statusCode(200)
                .body("firstname", equalTo("James"));
    }

    @Test(priority = 4)
    public void deleteBooking() {
        LogUtils.info("Test: Delete Booking " + bookingId);
        // Auth is handled automatically by ApiKeyword.delete
        Response response = ApiKeyword.delete(EndPointGlobal.BOOKING + "/" + bookingId);
        response.then().statusCode(201);
    }
}
