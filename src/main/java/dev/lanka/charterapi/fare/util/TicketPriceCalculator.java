package dev.lanka.charterapi.fare.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TicketPriceCalculator {

    public static BigDecimal calculateFarePrice(BigDecimal basePrice, BigDecimal multiplier, BigDecimal taxRate)
    {
        return basePrice.multiply(multiplier).multiply(BigDecimal.valueOf(1).add(taxRate)).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateLuggagePrice(BigDecimal basePrice, BigDecimal multiplier, BigDecimal taxRate)
    {
        return basePrice.multiply(multiplier).multiply(BigDecimal.valueOf(1).add(taxRate)).setScale(2, RoundingMode.HALF_UP);
    }
}
