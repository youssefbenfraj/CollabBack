package tn.esprit.espritcollabbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoc;
    private String titleDoc;
    private Long price;
    private String module;
    private boolean paid;
    private String content;
    @ManyToOne
    @JsonIgnore
    private  User user;
}
