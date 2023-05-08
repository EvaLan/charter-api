package dev.lanka.charterapi.fare;

import dev.lanka.charterapi.fare.model.FareType;
import dev.lanka.charterapi.fare.model.LuggageType;
import dev.lanka.charterapi.fare.util.TicketPriceCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class FareService {
    public List<FareType> getFareTypes()
    {
        return List.of(
                new FareType(1, "Standard"),
                new FareType(2, "Child")
        );
    }

    public List<LuggageType> getLuggageTypes()
    {
        return List.of(
                new LuggageType(1, "Standard size")
        );
    }

    public BigDecimal getFarePrice(int terminalId, int fareTypeId)
    {
        var basePrice = getBasePrice(terminalId);
        var multiplier = getFareMultiplier(fareTypeId);
        var taxRate = getTaxRates().get(0);
        return TicketPriceCalculator.calculateFarePrice(basePrice, multiplier, taxRate);
    }

    public BigDecimal getLuggagePrice(int terminalId, int luggageTypeId)
    {
        var basePrice = getBasePrice(terminalId);
        var multiplier = getLuggageMultiplier(luggageTypeId);
        var taxRate = getTaxRates().get(0);
        return TicketPriceCalculator.calculateLuggagePrice(basePrice, multiplier, taxRate);
    }

    private List<BigDecimal> getTaxRates()
    {
        return List.of(
            new BigDecimal("0.21"),
            new BigDecimal("0.18")
        );
    }

    private BigDecimal getBasePrice(int terminalId) throws NoSuchElementException
    {
        switch (terminalId)
        {
            case 1:
                return new BigDecimal("10");
            case 2:
                return new BigDecimal("22.5");
            default:
                throw new NoSuchElementException("No data found for terminal ID " + terminalId);
        }
    }

    private BigDecimal getFareMultiplier(int fareTypeId) throws NoSuchElementException
    {
        switch (fareTypeId)
        {
            case 1:
                return new BigDecimal("1");
            case 2:
                return new BigDecimal("0.5");
            default:
                throw new NoSuchElementException("No data found for fare type ID " + fareTypeId);
        }
    }
    private BigDecimal getLuggageMultiplier(int luggageTypeId) throws NoSuchElementException
    {
        switch (luggageTypeId)
        {
            case 1:
                return new BigDecimal("0.3");
            default:
                throw new NoSuchElementException("No data found for luggage type ID " + luggageTypeId);
        }
    }
}
