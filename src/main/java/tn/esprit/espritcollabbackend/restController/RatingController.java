package tn.esprit.espritcollabbackend.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.espritcollabbackend.entities.Event;
import tn.esprit.espritcollabbackend.entities.Rating;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.services.EventService;
import tn.esprit.espritcollabbackend.services.RatingService;
import tn.esprit.espritcollabbackend.services.UserServiceIMP;

import java.util.List;

@CrossOrigin(origins  = {"http://localhost:4200", "https://b94d-197-27-101-27.ngrok-free.app "}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserServiceIMP userService;

    @PostMapping("/events/{eventId}/ratings/{userId}")
    public Rating addRating(@PathVariable Long eventId, @RequestBody Rating rating, @PathVariable Long userId) {

        Event event = eventService.retrieveById(eventId);
        User user = userService.retrieveById(userId);

        return ratingService.addRating(rating, event, user);
    }
    @GetMapping("/events/{eventId}/ratings")
    public List<Rating> getAllRatingsForEvent(@PathVariable Long eventId) {
        return ratingService.getAllRatingsForEvent(eventId);
    }
    @GetMapping("/events/{eventId}/average-rating")
    public double getAverageRatingForEvent(@PathVariable Long eventId) {
        List<Rating> ratings = ratingService.getAllRatingsForEvent(eventId);
        Event event = eventService.retrieveById(eventId);
        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }

        double averageRating = 0.0;
        if (!ratings.isEmpty()) {
            int totalRating = ratings.stream().mapToInt(Rating::getValueR).sum();
            averageRating = (double) totalRating / ratings.size();
            averageRating = Math.round(averageRating * 100.0) / 100.0;
        }

        // Save the calculated average rating in the event
        event.setAverageRating(averageRating);
        eventService.updateEvent(eventId, event); // Assuming you have a method to update event details

        return averageRating;
    }


    @GetMapping("/events/highest-rated")
    public Event getHighestRatedEvent() {
        List<Event> allEvents = eventService.retrieveAllEvents();
        double highestAvgRating = 0;
        Event highestRatedEvent = null;

        for (Event event : allEvents) {
            double avgRating = getAverageRatingForEvent(event.getIdEvent());
            if (avgRating > highestAvgRating) {
                highestAvgRating = avgRating;
                highestRatedEvent = event;
            }
        }
        return highestRatedEvent;
    }


}
