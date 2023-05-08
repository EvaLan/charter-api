package dev.lanka.charterapi.booking;

import dev.lanka.charterapi.booking.model.BookingPriceRequest;
import dev.lanka.charterapi.booking.model.BookingPriceResponse;
import dev.lanka.charterapi.booking.model.TicketPrice;
import dev.lanka.charterapi.fare.FareService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@Component
public class BookingService {

    private final FareService fareService;

    public BookingService(FareService fareService)
    {
        this.fareService = fareService;
    }

    public BookingPriceResponse getBookingPrice(BookingPriceRequest request)
    {
        var responseItems = new ArrayList<TicketPrice>();
        var totalPrice = BigDecimal.valueOf(0);
        if (request.passengers() != null) {
            for (var passenger : request.passengers()) {
                var farePrice = fareService.getFarePrice(request.terminalId(), passenger.fareTypeId());
                var luggagePrice = BigDecimal.valueOf(0);
                if (passenger.luggageItems() != null) {
                    for (var luggageItem : passenger.luggageItems()) {
                        luggagePrice = luggagePrice.add(fareService.getLuggagePrice(request.terminalId(), luggageItem.typeId()).multiply(BigDecimal.valueOf(luggageItem.count())));
                    }
                }
                responseItems.add(new TicketPrice(passenger.id(), farePrice, luggagePrice.setScale(2, RoundingMode.HALF_UP)));
                totalPrice = totalPrice.add(farePrice).add(luggagePrice);
            }
        }
        return new BookingPriceResponse(totalPrice.setScale(2, RoundingMode.HALF_UP), responseItems);
    }
}
