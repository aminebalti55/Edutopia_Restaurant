package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.service.ReportService;

import java.util.List;


@RestController

public class ReportController {
    @Autowired
    ReportService reportService;
    @PostMapping("/reports")
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
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



}
