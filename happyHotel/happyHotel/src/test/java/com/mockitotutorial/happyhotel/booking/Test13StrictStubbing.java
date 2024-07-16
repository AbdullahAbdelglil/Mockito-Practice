package com.mockitotutorial.happyhotel.booking;

import com.example.happyHotel.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Test13StrictStubbing {

	@InjectMocks
	private BookingService bookingService;

	@Mock
	private PaymentService paymentServiceMock;
	@Mock
	private RoomService roomServiceMock;
	@Mock
	private BookingDAO bookingDAOMock;
	@Mock
	private MailSender mailSenderMock;
	@Captor
	private ArgumentCaptor<Double> doubleCaptor;

	@Test
	void should_InvokePayment_When_Prepaid() {

		// given
		BookingRequest bookingRequest = new BookingRequest
				("A1", LocalDate.of(2020, 5, 13), LocalDate.of(2020, 5, 23), 3, false);

		//note: pay() will not invoked because prepaid is false,
		// so if you didn't use lenient() mockito will throw exception because of strict stubbing.
		lenient().when(paymentServiceMock.pay(any(), anyDouble())).thenReturn("1");

		// when
		String roomId = bookingService.makeBooking(bookingRequest);

		// then
		// will not throw an exception
	}
}
