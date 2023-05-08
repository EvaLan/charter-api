package dev.lanka.charterapi.terminal;

import dev.lanka.charterapi.terminal.model.Terminal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/terminals")
public class TerminalController {
    private final TerminalService terminalService;

    public TerminalController(TerminalService terminalService)
    {
        this.terminalService = terminalService;
    }

    @GetMapping("")
    public List<Terminal> terminals()
    {
        return terminalService.getTerminals();
    }
}
