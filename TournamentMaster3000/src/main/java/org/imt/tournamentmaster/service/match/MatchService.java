package org.imt.tournamentmaster.service.match;

import jakarta.persistence.EntityNotFoundException;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Match> getById(long id) {
        return matchRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Match> getByStatus(Match.Status status) {
        return matchRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Match> getByEquipe(String equipe) {
        return matchRepository.findByEquipe(equipe);
    }

    @Transactional(readOnly = true)
    public List<String> getJoueursByMatch(long id) {
        return matchRepository.findJoueursByMatch(id);
    }

    @Transactional(readOnly = true)
    public List<Match> getAll() {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .toList();
    }

    @Transactional(readOnly = false)
    public Optional<Match> update(long id, Match updatedMatch) {
        Optional<Match> existingMatch = matchRepository.findById(id);

        if (existingMatch.isPresent()) {
            Match match = existingMatch.get();

            if (updatedMatch.getEquipeA() != null) {
                match.setEquipeA(updatedMatch.getEquipeA());
            }
            if (updatedMatch.getEquipeB() != null) {
                match.setEquipeB(updatedMatch.getEquipeB());
            }
            if (updatedMatch.getRounds() != null) {
                match.setRounds(updatedMatch.getRounds());
            }
            if (updatedMatch.getStatus() != null) {
                match.setStatus(updatedMatch.getStatus());
            }

            matchRepository.save(match);

            return Optional.of(match);
        }

        return Optional.empty();
    }

    @Transactional(readOnly = false)
    public void delete(long id) {
        matchRepository.deleteResultsByMatchId(id);
        matchRepository.deleteById(id);
    }
}
