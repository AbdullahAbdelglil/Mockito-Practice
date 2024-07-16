package com.mockitotutorial.happyhotel.booking;

import com.example.happyHotel.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class Test08Spies {
    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = spy(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService (paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void should_CanselBook_When_InputOk() {

        // given
        BookingRequest bookingRequest = new BookingRequest
                ("A1", LocalDate.of(2020, 5, 13), LocalDate.of(2020, 5, 23), 3, true);
        bookingRequest.setRoomId("3.1");
        String bookingId = "1";

        // like when() .. thenReturn in mocks;
        doReturn(bookingRequest).when(bookingDAOMock).get(bookingId);

        // when
        bookingService.cancelBooking(bookingId);

        // then
    }
    /*
        Spies is a partial mocks, which is mean that:
        spy is a real object with real methods, that we can modify.
    */
}
