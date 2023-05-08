package dev.lanka.charterapi.booking.model;

import java.util.List;

public record Passenger(int id, int fareTypeId, List<Luggage> luggageItems) { }

