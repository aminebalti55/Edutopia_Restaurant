package tn.esprit.spring.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.QueueEntry;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.User;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface QueueRepository extends JpaRepository<QueueEntry, Integer> {

    @Query("Select count (a) From QueueEntry a ")
    int getQueueSize();

    @Query("Select a From QueueEntry a where a.user = :u")

    QueueEntry getQueueEntryByUser(@Param("u") User user);



}
