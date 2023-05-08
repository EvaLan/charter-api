package dev.lanka.charterapi.booking.model;

import java.math.BigDecimal;
import java.util.List;

public record BookingPriceResponse(BigDecimal totalPrice, List<TicketPrice> ticketPrices) { }

