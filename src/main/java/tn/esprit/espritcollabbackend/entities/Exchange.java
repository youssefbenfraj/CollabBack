package tn.esprit.espritcollabbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Exchange implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idExch;
    @Temporal(TemporalType.DATE)
    private Date dateExch;
    @Enumerated(EnumType.STRING)
    private State state;
    @ManyToOne
    @JsonIgnore
    private User user;
    @ManyToOne
    @JsonIgnore
    private Book book;
}
