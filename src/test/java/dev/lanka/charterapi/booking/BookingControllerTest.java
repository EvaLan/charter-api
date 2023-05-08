package dev.lanka.charterapi.booking;

import dev.lanka.charterapi.booking.model.BookingPriceRequest;
import dev.lanka.charterapi.booking.model.BookingPriceResponse;
import dev.lanka.charterapi.booking.model.TicketPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(BookingController.class)
class BookingControllerTest {
    BookingController bookingController;

    @MockBean
    BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingController = new BookingController(bookingService);
    }

    @Test
    public void testDraftPriceWithValidRequest()
    {
        var request = new BookingPriceRequest(1, Collections.emptyList());

        var expectedResponse = new BookingPriceResponse(BigDecimal.valueOf(5), List.of(new TicketPrice(1, BigDecimal.valueOf(2), BigDecimal.valueOf(3))));
        when(bookingService.getBookingPrice(any(BookingPriceRequest.class))).thenReturn(expectedResponse);

        ResponseEntity<Object> response = bookingController.draftPrice(request);

        /*
         * Strictly speaking, units tests should have only one reason to fail but it makes sense to test both things here.
         * */
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testDraftPriceWithNoSuchArgumentException() {
        var request = new BookingPriceRequest(0, Collections.emptyList());

        var exceptionMessage = "No data found";
        when(bookingService.getBookingPrice(any(BookingPriceRequest.class))).thenThrow(new NoSuchElementException(exceptionMessage));

        ResponseEntity<Object> response = bookingController.draftPrice(request);

        /*
        * Strictly speaking, units tests should have only one reason to fail but it makes sense to test both things here.
        * */
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exceptionMessage, response.getBody());
    }
}