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
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idR;

    private int valueR;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Event event;

    @ManyToOne
    private User user;
    // Constructors
    public Rating(int valueR) {
        this.valueR = valueR;
    }

    public Rating(Long idR, int valueR, Event event, User user) {
        this.idR = idR;
        this.valueR = valueR;
        this.event = event;
        this.user = user;
    }
    public Rating(int valueR, Event event, User user) {

        this.valueR = valueR;
        this.event = event;
        this.user = user;
    }
}
