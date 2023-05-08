package dev.lanka.charterapi.booking.model;

import java.util.List;

public record BookingPriceRequest(int terminalId, List<Passenger> passengers) { }