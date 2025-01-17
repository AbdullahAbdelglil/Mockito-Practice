package com.mockitotutorial.happyhotel.booking;

import com.example.happyHotel.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Test07VerifyingBehaviour {
    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService (paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void should_InvokePayment_When_Prepaid() {

        // given
        BookingRequest bookingRequest = new BookingRequest
                ("A1", LocalDate.of(2020, 5, 13), LocalDate.of(2020, 5, 23), 3, true);
        double price = 10*3*50;
        // when
        String roomId = bookingService.makeBooking(bookingRequest);

        // then
        verify(paymentServiceMock, times(1)).pay(bookingRequest,price);
        verifyNoMoreInteractions(paymentServiceMock);

    }

    @Test
    void should_NotInvokePayment_When_NotPrepaid() {

        // given
        BookingRequest bookingRequest = new BookingRequest
                ("A1", LocalDate.of(2020, 5, 13), LocalDate.of(2020, 5, 23), 3, false);
        double price = 10*3*50;
        // when
        String roomId = bookingService.makeBooking(bookingRequest);

        // then
        verify(paymentServiceMock, never()).pay(any(), anyDouble());
    }
}
