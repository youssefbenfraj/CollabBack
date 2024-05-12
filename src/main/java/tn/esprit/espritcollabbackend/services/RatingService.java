package tn.esprit.espritcollabbackend.services;

import tn.esprit.espritcollabbackend.entities.Event;
import tn.esprit.espritcollabbackend.entities.Rating;
import tn.esprit.espritcollabbackend.entities.User;

import java.util.List;
import java.util.Map;

public interface RatingService {
    Rating addRating(Rating rating, Event event, User user);
    List<Rating> getAllRatingsForEvent(Long eventId);


    Rating updateRating(Long id, Rating newRating);

    void deleteRating(Long id);

    Rating getRatingById(Long id);

    List<Rating> getAllRatings();
}
