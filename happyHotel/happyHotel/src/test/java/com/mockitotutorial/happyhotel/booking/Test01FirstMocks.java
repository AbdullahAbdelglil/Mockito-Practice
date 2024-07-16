package com.mockitotutorial.happyhotel.booking;

import com.example.happyHotel.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class Test01FirstMocks {
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
    void should_CalculateCorrectPrice_When_BockingRequestIsCorrect() {

        // given
        BookingRequest bookingRequest = new BookingRequest
                ("A1", LocalDate.of(2020, 5, 13), LocalDate.of(2020, 5, 23), 3, false );
        double expectedPrice = 10 * 3 * 50.0;

        // when
        double actualPrice = bookingService.calculatePrice(bookingRequest);

        // then
        assertEquals(expectedPrice, actualPrice);
    }
}
