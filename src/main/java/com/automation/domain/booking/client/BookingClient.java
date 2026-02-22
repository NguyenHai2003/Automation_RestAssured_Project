package com.automation.domain.booking.client;

import com.automation.constants.EndPointGlobal;
import com.automation.models.booking.BookingRequest;
import com.automation.keywords.ApiKeyword;
import io.restassured.response.Response;

public class BookingClient {

    public Response createBooking(BookingRequest bookingRequest) {
        return ApiKeyword.post(EndPointGlobal.BOOKING, bookingRequest);
    }

    public Response getBooking(int bookingId) {
        return ApiKeyword.get(EndPointGlobal.BOOKING + "/" + bookingId);
    }

    public Response updateBooking(int bookingId, BookingRequest bookingRequest) {
        return ApiKeyword.put(EndPointGlobal.BOOKING + "/" + bookingId, bookingRequest);
    }

    public Response deleteBooking(int bookingId) {
        return ApiKeyword.delete(EndPointGlobal.BOOKING + "/" + bookingId);
    }
}
