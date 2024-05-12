package tn.esprit.espritcollabbackend.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.espritcollabbackend.entities.Event;
import tn.esprit.espritcollabbackend.entities.Rating;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.EventRepository;
import tn.esprit.espritcollabbackend.repositories.RatingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private EventServiceImpl eventService;

    @Override
    @Transactional
    public Rating addRating(Rating rating, Event event, User user) {
        rating.setEvent(event);
        rating.setUser(user);
        Rating savedRating = ratingRepository.save(rating);

        // Fetch all current ratings for the event, including the newly added one
        List<Rating> ratings = ratingRepository.findByEventId(event.getIdEvent());
        double averageRating = ratings.stream()
                .mapToInt(Rating::getValueR)
                .average()
                .orElse(0.0);

        // Debug output
        System.out.println("Ratings after adding new: " + ratings.stream().map(r -> Integer.toString(r.getValueR())).collect(Collectors.joining(", ")));
        System.out.println("Computed average rating: " + averageRating);

        // Set the new average and update the event
        event.setAverageRating(Math.round(averageRating * 100.0) / 100.0);
        eventService.updateEvent(event.getIdEvent(), event); // This method saves the event

        return savedRating;
    }


    @Override
    public List<Rating> getAllRatingsForEvent(Long eventId) {
        return ratingRepository.findByEventId(eventId);

    }


    @Override
    public Rating updateRating(Long id, Rating newRating) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));

        existingRating.setValueR(newRating.getValueR()); // Assuming you can update the value only
        return ratingRepository.save(existingRating);
    }

    @Override
    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));

        ratingRepository.delete(rating);
    }

    @Override
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
}
