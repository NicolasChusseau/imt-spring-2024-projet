package org.imt.tournamentmaster.ui.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.service.match.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/match")
public class DisplayMatchesController {

    private final MatchService matchService;

    public DisplayMatchesController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping()
    public String displayMatches(Model model) {

        List<Match> matches = matchService.getAll();

        model.addAttribute("matches", matches);

        return "match/displayMatches";
    }

}
