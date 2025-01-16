package org.imt.tournamentmaster.model.match;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.imt.tournamentmaster.model.equipe.Equipe;

import java.util.Objects;

@Entity
public class Round {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "L'équipe A est obligatoire")
    @OneToOne(cascade = CascadeType.ALL)
    private Equipe equipeA;

    @NotNull(message = "L'équipe B est obligatoire")
    @OneToOne(cascade = CascadeType.ALL)
    private Equipe equipeB;

    @Min(value = 0, message = "Le score ne peut pas être négatif")
    @Max(value = 99, message = "Le score ne peut pas être supérieur à 99")
    private int scoreA;

    @Min(value = 0, message = "Le score ne peut pas être négatif")
    @Max(value = 99, message = "Le score ne peut pas être supérieur à 99")
    private int scoreB;

    @Min(value = 1, message = "Le numéro de round doit être supérieur ou égal à 1")
    private int roundNumber;

    public Round() {
    }

    public Round(long id, Equipe equipeA, Equipe equipeB, int scoreA, int scoreB, int roundNumber) {
        this.id = id;
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.roundNumber = roundNumber;
    }

    public long getId() {
        return id;
    }

    public Equipe getEquipeA() {
        return equipeA;
    }

    public Equipe getEquipeB() {
        return equipeB;
    }

    public int getScoreA() {
        return scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEquipeA(Equipe equipeA) {
        this.equipeA = equipeA;
    }

    public void setEquipeB(Equipe equipeB) {
        this.equipeB = equipeB;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Equipe determineWinner() {
        if (scoreA > scoreB) {
            return equipeA;
        } else {
            return equipeB;
        }
    }

    @Override
    public String toString() {
        return "Round{" +
                "equipeA=" + equipeA +
                ", equipeB=" + equipeB +
                ", scoreA=" + scoreA +
                ", scoreB=" + scoreB +
                ", roundNumber=" + roundNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return id == round.id && scoreA == round.scoreA && scoreB == round.scoreB && roundNumber == round.roundNumber && Objects.equals(equipeA, round.equipeA) && Objects.equals(equipeB, round.equipeB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, equipeA, equipeB, scoreA, scoreB, roundNumber);
    }
}
