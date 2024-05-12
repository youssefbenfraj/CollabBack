package tn.esprit.espritcollabbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.espritcollabbackend.entities.Badge;
import tn.esprit.espritcollabbackend.entities.Book;

import java.util.List;

public interface BadgeRepository extends JpaRepository<Badge,Long> {
    @Query("SELECT b FROM Badge b ORDER BY b.threshold DESC")
    List<Badge> findAllByOrderByThresholdDesc();
}
