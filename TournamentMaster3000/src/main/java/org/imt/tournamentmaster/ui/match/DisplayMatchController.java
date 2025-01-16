package org.imt.tournamentmaster.ui.match;

import org.imt.tournamentmaster.error.MatchNotFoundException;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.service.match.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/match")
public class DisplayMatchController {

    private final MatchService matchService;

    public DisplayMatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{id}")
    public String displayMatch(Model model, @PathVariable Long id) {

        Optional<Match> optionalMatch = matchService.getById(id);
        if (optionalMatch.isPresent()) {
            model.addAttribute("match", optionalMatch.get());
        } else {
            throw new MatchNotFoundException(id);
        }

        return "match/displayMatch";
    }

}
