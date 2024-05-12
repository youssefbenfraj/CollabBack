package tn.esprit.espritcollabbackend.services;

import tn.esprit.espritcollabbackend.entities.Comment;
import tn.esprit.espritcollabbackend.entities.Event;
import tn.esprit.espritcollabbackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment addComment(Comment comment, Event event, User user);
    List<Comment> getCommentsForEvent(Long eventId);

    Optional<Comment> getCommentById(Long commentId);

    Comment updateComment(Long commentId, Comment updatedComment);

    void deleteComment(Long commentId);
}
