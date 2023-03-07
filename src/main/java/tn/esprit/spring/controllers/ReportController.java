package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.entities.Type;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ReportRepository;
import tn.esprit.spring.service.ReportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @PutMapping("/reports")
    public Report updateReport(@RequestBody Report report) {
        return reportService.updateReport(report);
    }

    @DeleteMapping("/reports/{id}")
    public void deleteReport(@PathVariable int id) {
        reportService.deleteReport(id);
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
