package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Ratings;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Integer> {
}