package tn.esprit.espritcollabbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.espritcollabbackend.entities.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByCategory(String category);
    List<Event> findByPrerequis(String prerequis);

    @Query("SELECT e FROM Event e JOIN e.reservations r WHERE r.user.idUser = :userId")
    List<Event> findEventsByReservationsUserId(@Param("userId") Long userId);

    @Query("SELECT e FROM Event e WHERE e.idEvent = (SELECT r.event.idEvent FROM Rating r GROUP BY r.event.idEvent ORDER BY AVG(r.valueR) DESC, COUNT(r) DESC LIMIT 1)")
    Event findHighestRatedEvent();

    @Query("SELECT e FROM Event e JOIN e.ratings r GROUP BY e ORDER BY AVG(r.valueR) DESC")
    List<Event> findEventsSortedByAverageRating();
}
