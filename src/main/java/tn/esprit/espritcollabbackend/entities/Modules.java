package tn.esprit.espritcollabbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Modules implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idM ;
    private String nomModule;

    @Enumerated(EnumType.STRING)
    private Niveau niveau ;

    @OneToMany(mappedBy = "module")
    // private List<Book> books ;
    //@OneToMany(mappedBy = "module")
    private List<Document> documents;
    @OneToMany(mappedBy = "module")
    private List<Revision> revisions;
    @ManyToOne
    @JsonIgnore
    private User user;

}
