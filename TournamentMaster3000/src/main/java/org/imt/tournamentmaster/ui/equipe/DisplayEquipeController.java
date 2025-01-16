package org.imt.tournamentmaster.ui.equipe;

import org.imt.tournamentmaster.error.EquipeNotFoundException;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.service.equipe.EquipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/equipe")
public class DisplayEquipeController {

    private final EquipeService equipeService;

    public DisplayEquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping("/{id}")
    public String displayEquipe(Model model, @PathVariable Long id) {

        Optional<Equipe> optionalEquipe = equipeService.getById(id);
        if (optionalEquipe.isPresent()) {
            model.addAttribute("equipe", optionalEquipe.get());
        } else {
            throw new EquipeNotFoundException(id);
        }

        return "equipe/displayEquipe";
    }

}
