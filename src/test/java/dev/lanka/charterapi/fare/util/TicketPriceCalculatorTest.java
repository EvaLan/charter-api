package dev.lanka.charterapi.fare.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketPriceCalculatorTest {

    @Test
    public void testCalculateFarePrice() {
        BigDecimal basePrice = BigDecimal.valueOf(10);
        BigDecimal multiplier = BigDecimal.valueOf(0.5);
        BigDecimal taxRate = BigDecimal.valueOf(0.21);

        BigDecimal expectedPrice = BigDecimal.valueOf(6.05).setScale(2);
        BigDecimal actualPrice = TicketPriceCalculator.calculateFarePrice(basePrice, multiplier, taxRate);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testCalculateLuggagePrice() {
        BigDecimal basePrice =  BigDecimal.valueOf(10);
        BigDecimal multiplier = BigDecimal.valueOf(0.3);
        BigDecimal taxRate =  BigDecimal.valueOf(0.21);

        BigDecimal expectedPrice =  BigDecimal.valueOf(3.63).setScale(2);
        BigDecimal actualPrice = TicketPriceCalculator.calculateLuggagePrice(basePrice, multiplier, taxRate);

        assertEquals(expectedPrice, actualPrice);
    }
}