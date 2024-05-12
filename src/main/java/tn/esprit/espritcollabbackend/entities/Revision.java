package tn.esprit.espritcollabbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Revision implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRev;
    @Temporal(TemporalType.DATE)
    private Date date_debut;
    private String sujetRev;
    private String objectif;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id_user ")
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Document> Documents;
    @ManyToOne
    private Modules module;

}
