package org.imt.tournamentmaster.controller.match;

import jakarta.validation.Valid;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.service.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Match> getById(@PathVariable long id) {
        Optional<Match> match = matchService.getById(id);

        return match.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<Match> getByStatus(@PathVariable Match.Status status) {
        return matchService.getByStatus(status);
    }

    @GetMapping("/equipe/{equipe}")
    public List<Match> getByEquipe(@PathVariable String equipe) {
        return matchService.getByEquipe(equipe);
    }

    @GetMapping("/{id}/joueurs")
    public ResponseEntity<List<String>> getJoueursByMatch(@PathVariable long id) {
        Optional<Match> match = matchService.getById(id);

        if (match.isPresent()) {
            List<String> joueurs = matchService.getJoueursByMatch(id);

            return ResponseEntity.ok(joueurs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Match> getAll() {
        return matchService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> update(@Valid @PathVariable long id, @RequestBody Match match) {
        Optional<Match> updatedMatch = matchService.update(id, match);

        return updatedMatch.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable long id) {
        matchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
