package tn.esprit.espritcollabbackend.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBook;
    private String titleBook;
    private String description;
    private String language;
    private String coverPicture;
    private IsAvailable isAvailable;
    private String phoneNumber;

    @ElementCollection
    private Set<Long> likedBy = new HashSet<>();
    @ElementCollection
    private Set<Long> dislikedBy = new HashSet<>();
    public int getLikes() {
        return likedBy.size();
    }

    public int getDislikes() {
        return dislikedBy.size();
    }
    @ManyToOne
    @JsonIgnore

    private  User user;
}
