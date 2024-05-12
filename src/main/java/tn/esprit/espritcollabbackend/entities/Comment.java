package tn.esprit.espritcollabbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;

    @ManyToOne(cascade = CascadeType.ALL)
    private Event event;

    @ManyToOne
    private User user;

    private String content;
    public Comment(String content) {
        this.content = content;
    }

    public Comment(Event event, User user, String content) {
        this.event = event;
        this.user = user;
        this.content = content;
    }

    public Comment(Long idC, Event event, User user, String content) {
        this.idC = idC;
        this.event = event;
        this.user = user;
        this.content = content;
    }
}
