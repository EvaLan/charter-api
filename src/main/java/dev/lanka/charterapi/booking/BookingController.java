package dev.lanka.charterapi.booking;

import dev.lanka.charterapi.booking.model.BookingPriceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService)
    {
        this.bookingService = bookingService;
    }

    @PostMapping("/draft-price")
    public ResponseEntity<Object> draftPrice(@RequestBody BookingPriceRequest request)
    {
        try {
            return ResponseEntity.ok(bookingService.getBookingPrice(request));
        }
        catch (NoSuchElementException ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
