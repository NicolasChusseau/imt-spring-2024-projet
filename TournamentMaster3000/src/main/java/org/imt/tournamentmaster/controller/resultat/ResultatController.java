package org.imt.tournamentmaster.controller.resultat;

import jakarta.validation.Valid;
import org.imt.tournamentmaster.dto.ImportReport;
import org.imt.tournamentmaster.model.resultat.Resultat;
import org.imt.tournamentmaster.service.resultat.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resultat")
public class ResultatController {

    private final ResultatService resultatService;

    @Autowired
    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getById(@PathVariable long id) {
        Optional<Resultat> resultat = resultatService.getById(id);

        return resultat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Resultat> getAll() {
        return resultatService.getAll();
    }

    @PostMapping("/import")
    public ImportReport importResultat(@Valid @RequestBody List<Resultat> resultats) {
        return resultatService.importResultat(resultats);
    }

}
