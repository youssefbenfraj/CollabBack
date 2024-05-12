package tn.esprit.espritcollabbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.espritcollabbackend.entities.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query("SELECT r FROM Rating r WHERE r.event.idEvent = :eventId")
    List<Rating> findByEventId(@Param("eventId") Long eventId);}
