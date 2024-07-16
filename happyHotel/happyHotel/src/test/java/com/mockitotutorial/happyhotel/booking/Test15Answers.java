package com.mockitotutorial.happyhotel.booking;

import com.example.happyHotel.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class Test15Answers {

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

			double expected = 400 * 0.8;

			mockedConvertor.when(() -> CurrencyConverter.toEuro(anyDouble()))
					.thenAnswer(inv -> (double) inv.getArgument(0) * 0.8);

			// when
			double actual = bookingService.calculatePriceEuro(bookingRequest);

			// then
			assertEquals(expected, actual);
		}
	}

}
