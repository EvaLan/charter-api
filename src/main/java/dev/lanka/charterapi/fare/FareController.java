package dev.lanka.charterapi.fare;

import dev.lanka.charterapi.fare.model.FareType;
import dev.lanka.charterapi.fare.model.LuggageType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fares")
public class FareController {

    private final FareService fareService;
    public FareController(FareService fareService)
    {
        this.fareService = fareService;
    }

    @GetMapping("/fare-types")
    public List<FareType> fareTypes()
    {
        return fareService.getFareTypes();
    }

    @GetMapping("/luggage-types")
    public List<LuggageType> luggageTypes()
    {
        return fareService.getLuggageTypes();
    }
}
