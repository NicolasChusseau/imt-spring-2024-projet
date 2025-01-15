package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.model.match.Match;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> findByStatus(Match.Status status);

    @Query("SELECT m FROM Match m WHERE m.equipeA.nom = :equipe OR m.equipeB.nom = :equipe")
    List<Match> findByEquipe(String equipe);

    @Query("SELECT j.prenom, j.nom " +
            "FROM Match m " +
            "JOIN m.equipeA.joueurs j " +
            "WHERE m.id = :id " +
            "UNION ALL " +
            "SELECT j.prenom, j.nom " +
            "FROM Match m " +
            "JOIN m.equipeB.joueurs j " +
            "WHERE m.id = :id")
    List<String> findJoueursByMatch(long id);

    @Modifying
    @Query("DELETE FROM Resultat r WHERE r.match.id = :id")
    void deleteResultsByMatchId(long id);
}
