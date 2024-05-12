package tn.esprit.espritcollabbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.espritcollabbackend.entities.Comment;
import tn.esprit.espritcollabbackend.entities.Event;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("SELECT c FROM Comment c WHERE c.event.idEvent = :eventId")
    List<Comment> findByEventId(@Param("eventId") Long eventId);
}
