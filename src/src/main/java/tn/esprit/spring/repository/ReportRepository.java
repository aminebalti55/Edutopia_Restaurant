package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Report;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {


    List<Report> findByTraiteeTrueAndCreatedAtBefore(Date thresholdDate);
    @Query("SELECT r FROM Report r WHERE r.traitee = true AND r.createdAt < :thresholdDate")
    List<Report> findArchivedReports(@Param("thresholdDate") Date thresholdDate);

    List<Report> findByTraiteeFalse();
    @Query("SELECT COUNT(r) FROM Report r WHERE r.traitee = true")
    long countTreatedReports();

    @Query("SELECT COUNT(r) FROM Report r WHERE r.traitee = false")
    long countUntreatedReports();
    List<Report> findByTraiteeFalseAndReportId(int reportId);

}