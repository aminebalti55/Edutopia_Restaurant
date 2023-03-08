package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.entities.Type;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ReportRepository;
import tn.esprit.spring.service.ReportService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@RestController

public class ReportController {
    @Autowired
    ReportService reportService;
    @Autowired
    ReportRepository reportRepository;
    /*@PostMapping("/reports")
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
    }*/
    @PostMapping("/create")
    public ResponseEntity<Report> createReport(@RequestParam String title,
                                               @RequestParam String note,
                                               @RequestParam Type type,
                                               @RequestParam User user) {
        Report report = reportService.createReport(title, note, type, user);
        return ResponseEntity.ok(report);
    }
    @GetMapping("/reports/{id}")
    public Report getReportById(@PathVariable int id) {
        return reportService.getReportById(id);
    }

    @GetMapping("/reports")
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    /*@PutMapping("/reports")
    public Report updateReport(@RequestBody Report report) {
        return reportService.updateReport(report);
    }
*/
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable("id") int reportId, @RequestBody Report report) {
        if (reportId != report.getReportId()) {
            throw new IllegalArgumentException("Report ID in path must match ID in request body");
        }
        Report updatedReport = reportService.updateReport(report);
        return ResponseEntity.ok(updatedReport);
    }

/*
    @DeleteMapping("/reports/{id}")
    public void deleteReport(@PathVariable int id) {
        reportService.deleteReport(id);
    }*/
@DeleteMapping("/reports/{id}")
public void deleteReport(@PathVariable long id) {
    try {
        reportService.deleteReport(id);
    } catch (IllegalArgumentException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (NoSuchElementException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
}

    @GetMapping("/archived")
    public List<Report> getArchivedReports() {
        return reportService.getArchivedReports();
    }
    @PutMapping("/report/{reportId}/traiter")
    public void traiterReclamation(@PathVariable("reportId") int reportId) {
         reportService.traiterReclamation(reportId);

    }
    @GetMapping("/reports/statistics")
    public ResponseEntity<Map<String, Long>> displayStatistics() {
        long treatedReportsCount = reportRepository.countTreatedReports();
        long untreatedReportsCount = reportRepository.countUntreatedReports();

        Map<String, Long> statistics = new HashMap<>();
        statistics.put("treatedReportsCount", treatedReportsCount);
        statistics.put("untreatedReportsCount", untreatedReportsCount);

        return ResponseEntity.ok(statistics);
    }





}
