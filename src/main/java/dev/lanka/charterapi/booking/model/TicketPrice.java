package dev.lanka.charterapi.booking.model;

import java.math.BigDecimal;

public record TicketPrice(int passengerId, BigDecimal farePrice, BigDecimal luggagePrice) { }
