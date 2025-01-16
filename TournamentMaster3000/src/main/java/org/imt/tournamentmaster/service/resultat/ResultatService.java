package org.imt.tournamentmaster.service.resultat;

import org.imt.tournamentmaster.dto.ImportReport;
import org.imt.tournamentmaster.dto.Report;
import org.imt.tournamentmaster.model.resultat.Resultat;
import org.imt.tournamentmaster.repository.resultat.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ResultatService {

    private final ResultatRepository resultatRepository;

    @Autowired
    public ResultatService(ResultatRepository resultatRepository) {
        this.resultatRepository = resultatRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Resultat> getById(long id) {
        return resultatRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Resultat> getAll() {
        return StreamSupport.stream(resultatRepository.findAll().spliterator(), false)
                .toList();
    }

    @Transactional
    public ImportReport importResultat(List<Resultat> resultats) {
        ImportReport importReport = new ImportReport();

        for (Resultat resultat : resultats) {
            String equipeAName = resultat.getMatch().getEquipeA().getNom();
            String equipeBName = resultat.getMatch().getEquipeB().getNom();
            LocalDate date = resultat.getMatch().getDate();
            Boolean alreadyExists = resultatRepository.findByMatch_EquipeA_NomAndMatch_EquipeB_NomAndMatch_Date(
                    equipeAName, equipeBName, date).isPresent();

            Report report;

            if (alreadyExists) {
                report = new Report(false, null, "Resultat already exists");
            } else {
                resultatRepository.save(resultat);
                report = new Report(true, resultat.determineWinner().getNom(), "Resultat imported successfully");
            }

            importReport.addReport(report);

        }
        importReport.setBasicGlobalMessageTemplate();
        return importReport;
    }

}
