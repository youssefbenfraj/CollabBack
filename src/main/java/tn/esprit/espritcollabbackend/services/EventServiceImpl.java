package tn.esprit.espritcollabbackend.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.espritcollabbackend.entities.Event;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.EventRepository;
import tn.esprit.espritcollabbackend.entities.Reservation;
import tn.esprit.espritcollabbackend.repositories.ReservationRepository;
import tn.esprit.espritcollabbackend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ReservationRepository reservationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final FilesStorageService filesStorageService;


    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, FilesStorageService filesStorageService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.filesStorageService = filesStorageService;
    }


    public User assignUserToEvent(Long idUser, Long idEvent) {
        User user = userRepository.findById(idUser).orElse(null);
        Event event = eventRepository.findById(idEvent).orElse(null);

        if (user != null && event != null) {
            user.getEventList().add(event);
            return userRepository.save(user);
        } else {
            return null; // Handle null user or event
        }
    }
    @Override
    @Transactional
    public Event addEvent(Event event, MultipartFile imageFile, Long userId) {
        try {
            // Check if the image file is provided and save it
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = filesStorageService.saveImage(imageFile);
                event.setPhotoUrl(imageUrl);
            }

            // Retrieve user by userId
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Add the event to the user's list of events
            user.getEventList().add(event);

            // Save the user to ensure the association is persisted
            userRepository.save(user);

            // Add the user to the event's list of attendees
            event.getUserList().add(user);

            // Save the event
            Event savedEvent = eventRepository.save(event);

            return savedEvent;
        } catch (Exception e) {
            throw new RuntimeException("Failed to add event. Error: " + e.getMessage());
        }
    }





    @Override
    public Event retrieveById(long idEvent) {
        Optional<Event> optionalEvent = eventRepository.findById(idEvent);
        return optionalEvent.orElse(null);
    }

    @Override

    public Event updateEvent(Long idEvent, Event updateEvent) {
        Event existingEvent = eventRepository.findById(idEvent)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + idEvent));

        // Update fields; consider updating average rating irrespective of being non-zero
        if (updateEvent.getTitleEvent() != null) {
            existingEvent.setTitleEvent(updateEvent.getTitleEvent());
        }
        if (updateEvent.getDateEvent() != null) {
            existingEvent.setDateEvent(updateEvent.getDateEvent());
        }
        if (updateEvent.getDescription() != null) {
            existingEvent.setDescription(updateEvent.getDescription());
        }
        if (updateEvent.getNbMaxInscrits() != 0) {
            existingEvent.setNbMaxInscrits(updateEvent.getNbMaxInscrits());
        }
        if (updateEvent.getDuree() != null) {
            existingEvent.setDuree(updateEvent.getDuree());
        }
        if (updateEvent.getFormateurs() != null) {
            existingEvent.setFormateurs(updateEvent.getFormateurs());
        }
        if (updateEvent.getModalitesParticipation() != null) {
            existingEvent.setModalitesParticipation(updateEvent.getModalitesParticipation());
        }
        if (updateEvent.getCout() != 0) {
            existingEvent.setCout(updateEvent.getCout());
        }
        if (updateEvent.getPrerequis() != null) {
            existingEvent.setPrerequis(updateEvent.getPrerequis());
        }
        if (updateEvent.getAverageRating() != existingEvent.getAverageRating()) {
            existingEvent.setAverageRating(updateEvent.getAverageRating());
        }
        if (updateEvent.getPhotoUrl() != null) {
            existingEvent.setPhotoUrl(updateEvent.getPhotoUrl());
        }

        eventRepository.save(existingEvent);
        return existingEvent;
    }



    //*****************************************************************//
    @Override
    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }

//****************************************************************//

    @Override
    public void deleteEvent(long idEvent) {
        eventRepository.deleteById(idEvent);
    }


    // Implémentation des autres méthodes de base

//****************************************************************//
@Override
public Reservation addReservation(long userId, long eventId, Reservation reservation) {
    Event event = eventRepository.findById(eventId).orElse(null);
    User user = userRepository.findById(userId).orElse(null);

    if (event != null && user != null && event.getNbMaxInscrits() > 0) {
        // Check if there are available slots for reservation
        event.setNbMaxInscrits(event.getNbMaxInscrits() - 1); // Decrement available slots
        reservation.setEvent(event);
        reservation.setUser(user);
        return reservationRepository.save(reservation);
    }

    return null;
}

    public List<Event> getEventsByCategory(String category) {
        return eventRepository.findByCategory(category);
    }

    @Override
    public List<Event> getEventsByPrerequis(String prerequis) {
        return eventRepository.findByPrerequis(prerequis);
    }

    @Override
    public List<String> getDistinctCategories() {
        return eventRepository.findAll().stream()
                .map(Event::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    public List<String> getDistinctPrerequies() {
        return eventRepository.findAll().stream()
                .map(Event::getPrerequis)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> getReservationsByEventId(long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            return event.getReservations();
        }
        return null;
    }
    @Override
    public List<Event> findReservedEventsByUserId(Long userId) {
        return eventRepository.findEventsByReservationsUserId(userId);
    }
//**************************************************************//
    @Override
    public void deleteReservation(long reservationId) {
        eventRepository.deleteById(reservationId);
    }
    @Override
    public double calculateAverageRating(long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            // Code pour calculer la moyenne des notes des évaluations de l'événement
            return 0.0; // Remplacer par le calcul réel
        }
        return 0.0; // Valeur par défaut si l'événement n'est pas trouvé
    }

    @Override
    public void addRating(long eventId, int rating) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            // Code pour ajouter une nouvelle évaluation et mettre à jour la moyenne
        }
    }
    public Event getHighestRatedEvent() {
        return eventRepository.findHighestRatedEvent();
    }

    public List<Event> getLeaderboard() {
        return eventRepository.findEventsSortedByAverageRating();
    }

}
