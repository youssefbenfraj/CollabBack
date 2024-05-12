package tn.esprit.espritcollabbackend.services;

import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.espritcollabbackend.entities.Reservation;
import tn.esprit.espritcollabbackend.entities.Event;
import tn.esprit.espritcollabbackend.entities.User;

import java.util.List;

public interface EventService {




    @Transactional
    Event addEvent(Event event, MultipartFile imageFile, Long userId);

    Event retrieveById(long idEvent);
    Event updateEvent(Long idEvent, Event updateEvent);
    List<Event> retrieveAllEvents();
    void deleteEvent(long idEvent);
    /*************************************/
    // Nouvelles méthodes pour la gestion des réservations

    //****************************************************************//
    Reservation addReservation(long userId, long eventId, Reservation reservation);

    List<String> getDistinctPrerequies();

    List<Reservation> getReservationsByEventId(long eventId);

    List<Event> findReservedEventsByUserId(Long userId);

    void deleteReservation(long reservationId);
    /**********************************/
    double calculateAverageRating(long eventId);
    void addRating(long eventId, int rating);

    List<Event> getEventsByCategory(String category);
    List<Event> getEventsByPrerequis(String prerequis);

    List<String> getDistinctCategories();

    List<Event> getLeaderboard();
}

