package dev.lanka.charterapi.terminal;

import dev.lanka.charterapi.terminal.model.Terminal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TerminalService {
    public List<Terminal> getTerminals() {
        return  List.of(
                new Terminal(1, "Vilnius"),
                new Terminal(2, "Warsaw")
        );
    }
}