package tn.esprit.espritcollabbackend.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import tn.esprit.espritcollabbackend.entities.Comment;
import tn.esprit.espritcollabbackend.entities.Event;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.services.CommentServiceImp;
import tn.esprit.espritcollabbackend.services.EventService;
import tn.esprit.espritcollabbackend.services.UserServiceIMP;

import java.util.List;

@CrossOrigin(origins  = {"http://localhost:4200", "https://2e97-197-31-160-181.ngrok-free.app"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentServiceImp commentService;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserServiceIMP userService;

    @PostMapping("/events/{eventId}/comments/{userId}")
    public Comment addComment(@PathVariable Long eventId, @RequestBody Comment comment, @PathVariable Long userId) {
        Event event = eventService.retrieveById(eventId);
        User user = userService.retrieveById(userId);
        return commentService.addComment(comment, event, user);
    }

    @GetMapping("/events/{eventId}/comments")
    public List<Comment> getAllCommentsForEvent(@PathVariable Long eventId) {
        return commentService.getCommentsForEvent(eventId);
    }




    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
