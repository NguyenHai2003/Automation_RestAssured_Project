package com.automation.domain.booking.service;

import com.automation.domain.booking.client.BookingClient;
import com.automation.models.booking.BookingRequest;
import io.restassured.response.Response;

public class BookingService {

    private final BookingClient bookingClient = new BookingClient();

    public Response createBooking(BookingRequest bookingRequest) {
        return bookingClient.createBooking(bookingRequest);
    }

    public Response getBooking(int bookingId) {
        return bookingClient.getBooking(bookingId);
    }

    public Response updateBooking(int bookingId, BookingRequest bookingRequest) {
        return bookingClient.updateBooking(bookingId, bookingRequest);
    }

    public Response deleteBooking(int bookingId) {
        return bookingClient.deleteBooking(bookingId);
    }
}
