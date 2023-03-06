package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Menu;
import tn.esprit.spring.entities.Restaurant;

import java.util.Date;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {


}