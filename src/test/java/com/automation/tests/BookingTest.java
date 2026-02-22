package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.domain.auth.service.AuthService;
import com.automation.domain.booking.assertion.BookingAssertions;
import com.automation.domain.booking.service.BookingService;
import com.automation.models.booking.BookingRequest;
import com.automation.models.booking.BookingResponse;
import com.automation.utils.LogUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BookingTest extends BaseTest {

    private final BookingService bookingService = new BookingService();
    private final AuthService authService = new AuthService();
    private static final String BOOKING_DATA_PATH = "src/test/resources/testdata/booking-requests.json";
    private static final File BOOKING_SCHEMA_FILE = new File("src/test/resources/schemas/booking_schema.json");

    @DataProvider(name = "bookingData")
    public Object[][] bookingData() {
        List<BookingRequest> bookingRequests = loadBookingRequests(BOOKING_DATA_PATH);
        Object[][] data = new Object[bookingRequests.size()][1];
        for (int i = 0; i < bookingRequests.size(); i++) {
            data[i][0] = bookingRequests.get(i);
        }
        return data;
    }

    @Test(dataProvider = "bookingData")
    public void bookingCrudFlow(BookingRequest bookingRequest) {
        LogUtils.info("Test: Booking CRUD flow for " + bookingRequest.getFirstname() + " " + bookingRequest.getLastname());

        authService.loginWithDefaultCredentials();

        Response createResponse = bookingService.createBooking(bookingRequest);
        BookingResponse bookingResponse = BookingAssertions.assertCreateBookingSuccess(createResponse, bookingRequest, BOOKING_SCHEMA_FILE);
        int bookingId = bookingResponse.getBookingid();

        Response getResponse = bookingService.getBooking(bookingId);
        BookingAssertions.assertGetBookingSuccess(getResponse, bookingRequest);

        String updatedFirstName = bookingRequest.getFirstname() + "_Updated";
        bookingRequest.setFirstname(updatedFirstName);
        Response updateResponse = bookingService.updateBooking(bookingId, bookingRequest);
        BookingAssertions.assertUpdateBookingSuccess(updateResponse, updatedFirstName);

        Response deleteResponse = bookingService.deleteBooking(bookingId);
        BookingAssertions.assertDeleteBookingSuccess(deleteResponse);
    }

    private List<BookingRequest> loadBookingRequests(String relativePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(relativePath), new TypeReference<List<BookingRequest>>() {
            });
        } catch (IOException exception) {
            throw new RuntimeException("Cannot load booking test data from: " + relativePath, exception);
        }
    }
}
