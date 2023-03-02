package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.repository.ReportRepository;
import tn.esprit.spring.serviceInterface.IreportService;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Validated
@Service

public class ReportService implements IreportService {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    private Validator validator;
    public Report createReport(Report report) {
        report.setTraitee(false);
        return reportRepository.save(report);
    }

    public Report getReportById(long id) {
        return reportRepository.findById((int) id).orElse(null);
    }

    public List<Report> getAllReports() {
        return reportRepository.findByTraiteeFalse();
    }

    public Report updateReport(Report report) {
        return reportRepository.save(report);
    }

    public void deleteReport(long id) {
        reportRepository.deleteById((int) id);
    }
    @Scheduled(cron = "0 * * * * ?")
    public List<Report> getArchivedReports() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -30); // soustrait 30 jours à la date d'aujourd'hui
        Date thresholdDate = calendar.getTime();

        return reportRepository.findByTraiteeTrueAndCreatedAtBefore(thresholdDate);
    }
    public void traiterReclamation(int reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with id " + reportId));

        report.setTraitee(true);

        reportRepository.save(report);
    }





}

