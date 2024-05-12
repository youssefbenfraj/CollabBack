package tn.esprit.espritcollabbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Complaint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComplaint;
    @Column(name = "date_complaint")
    private LocalDate dateComplaint;

    private  String comment;
    @Enumerated(EnumType.STRING)
    private  Traitement traitement;


    @ManyToOne
    @JsonIgnore
    private User user;
}