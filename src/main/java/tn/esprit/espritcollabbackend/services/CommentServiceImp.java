package tn.esprit.espritcollabbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.espritcollabbackend.entities.Comment;
import tn.esprit.espritcollabbackend.entities.Event;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service

public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment, Event event, User user) {
        comment.setEvent(event);
        comment.setUser(user);
        return commentRepository.save(comment);
    }


    @Override
    public List<Comment> getCommentsForEvent(Long idEvent) {
        return commentRepository.findByEventId(idEvent);
    }

    @Override
    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public Comment updateComment(Long commentId, Comment updatedComment) {
        // Retrieve the existing comment by its ID
        Optional<Comment> existingCommentOptional = commentRepository.findById(commentId);
        if (existingCommentOptional.isPresent()) {
            Comment existingComment = existingCommentOptional.get();
            // Update the existing comment with the new data
            existingComment.setContent(updatedComment.getContent());
            // Save the updated comment
            return commentRepository.save(existingComment);
        } else {
            // Handle the case where the comment doesn't exist
            throw new RuntimeException("Comment not found with id: " + commentId);
        }
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
