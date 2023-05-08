package dev.lanka.charterapi.booking;

import dev.lanka.charterapi.booking.model.*;
import dev.lanka.charterapi.fare.FareService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(BookingService.class)
class BookingServiceTest {
    BookingService bookingService;

    @MockBean
    FareService fareService;

    @BeforeEach
    void setUp() {
        bookingService = new BookingService(fareService);
        when(fareService.getFarePrice(1, 1)).thenReturn(BigDecimal.valueOf(10).setScale(2));
        when(fareService.getFarePrice(1, 2)).thenReturn(BigDecimal.valueOf(5).setScale(2));
        when(fareService.getLuggagePrice(1, 1)).thenReturn(BigDecimal.valueOf(3).setScale(2));
    }

    @Test
    void testGetBookingPriceWithNoPassengers() {
        var request = new BookingPriceRequest(1, Collections.emptyList());
        var expectedResponse = new BookingPriceResponse(BigDecimal.valueOf(0).setScale(2), Collections.emptyList());
        assertEquals(expectedResponse, bookingService.getBookingPrice(request));
    }

    @Test
    void testGetBookingPriceWithPassengersAndLuggage() {
        var request = new BookingPriceRequest(1, List.of(
                new Passenger(1, 1, Collections.emptyList()),
                new Passenger(2, 2, List.of(new Luggage(1, 2)))
        ));
        var expectedResponse = new BookingPriceResponse(BigDecimal.valueOf(21).setScale(2), List.of(
                new TicketPrice(1, BigDecimal.valueOf(10).setScale(2), BigDecimal.valueOf(0).setScale(2)),
                new TicketPrice(2, BigDecimal.valueOf(5).setScale(2), BigDecimal.valueOf(6).setScale(2))
        ));
        var actualResponse = bookingService.getBookingPrice(request);
        assertEquals(expectedResponse, actualResponse);
    }
}