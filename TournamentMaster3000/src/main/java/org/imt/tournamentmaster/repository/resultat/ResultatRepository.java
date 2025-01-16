package org.imt.tournamentmaster.repository.resultat;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.resultat.Resultat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ResultatRepository extends CrudRepository<Resultat, Long> {

    Optional<Resultat> findByMatch_EquipeA_NomAndMatch_EquipeB_NomAndMatch_Date(String equipeA, String equipeB, LocalDate date);

}
