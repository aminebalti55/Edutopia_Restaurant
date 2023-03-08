package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.entities.Type;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ReportRepository;
import tn.esprit.spring.serviceInterface.IreportService;

import javax.persistence.EntityNotFoundException;
import java.util.*;


@Validated
@Service

public class ReportService implements IreportService {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    private Validator validator;
    /* Report createReport(Report report) {
        report.setTraitee(false);
        return reportRepository.save(report);
    }*/
    public Report createReport(String title, String note, Type type, User user) {
        Report report = new Report(title, note, type.QUALITE, user);
        report.setTraitee(false);
        return reportRepository.save(report);
    }


    public Report getReportById(long id) {
        return reportRepository.findById((int) id).orElse(null);
    }

    public List<Report> getAllReports() {
        return reportRepository.findByTraiteeFalse();
    }

    /*public Report updateReport(Report report) {
        return reportRepository.save(report);
    }*/
    public Report updateReport(Report report) {
        List<Report> reports = reportRepository.findByTraiteeFalseAndReportId(report.getReportId());
        if (!reports.isEmpty()) {
            Report existingReport = reports.get(0);
            existingReport.setTitle(report.getTitle());
            existingReport.setNote(report.getNote());
            existingReport.setType(report.getType());
            return reportRepository.save(existingReport);
        } else {
            throw new NoSuchElementException("No report found with ID " + report.getReportId());
        }
    }














   /* public void deleteReport(long id) {
        reportRepository.deleteById((int) id);
    }*/
   public void deleteReport(long id) {
       Optional<Report> optionalReport = reportRepository.findById((int) id);
       if (optionalReport.isPresent()) {
           Report report = optionalReport.get();
           if (!report.isTraitee()) {
               reportRepository.deleteById((int) id);
           } else {
               throw new IllegalArgumentException("Report with ID " + id + " has already been processed and cannot be deleted.");
           }
       } else {
           throw new NoSuchElementException("No report found with ID " + id);
       }
   }

    public List<Report> getArchivedReports() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -30); // soustrait 30 jours à la date d'aujourd'hui
        Date thresholdDate = calendar.getTime();

        return reportRepository.findByTraiteeTrueAndCreatedAtBefore(thresholdDate);
    }
    /*@Scheduled(cron = "0 * * ? * *")
    public void printArchivedReports() {
        List<Report> archivedReports = getArchivedReports();
        System.out.println("Archived Reports:");
        for (Report report : archivedReports) {
            System.out.println(report.toString());
        }
    }*/

    public void traiterReclamation(int reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with id " + reportId));

        report.setTraitee(true);

        reportRepository.save(report);
    }





}

