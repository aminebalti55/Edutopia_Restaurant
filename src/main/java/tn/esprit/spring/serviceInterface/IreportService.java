package tn.esprit.spring.serviceInterface;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.entities.Subscription;

import java.util.Date;
import java.util.List;

public interface IreportService {
    void deleteReport(long id);
    Report createReport(Report report);
    Report getReportById(long id);

    List<Report> getAllReports();
    Report updateReport(Report report);


}
