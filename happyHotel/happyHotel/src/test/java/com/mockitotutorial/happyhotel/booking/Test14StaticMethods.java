package com.mockitotutorial.happyhotel.booking;

import com.example.happyHotel.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.propertyeditors.CurrencyEditor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Test14StaticMethods {

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
		// to test static methods: mockito team recommend you to use try with resources, like this:
		try(MockedStatic<CurrencyConverter> mockedConvertor = mockStatic(CurrencyConverter.class)) {

			// given
			BookingRequest bookingRequest = new BookingRequest
					("A1", LocalDate.of(2020, 5, 13), LocalDate.of(2020, 5, 23), 3, false);
			double expected = 400;
			mockedConvertor.when(() -> CurrencyConverter.toEuro(anyDouble())).thenReturn(400.0);

			// when
			double actual = bookingService.calculatePriceEuro(bookingRequest);

			// then
			assertEquals(expected, actual);
		}
	}

}
