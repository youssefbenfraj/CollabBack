package tn.esprit.espritcollabbackend.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEvent;
    @NotNull
    private String titleEvent;
    @Temporal(TemporalType.DATE)
    private Date dateEvent;
    private String description;
    private int nbMaxInscrits;
    private String location; // Lieu de l'événement (physique)
    private String category; // Lieu de l'événement (en ligne)
    private String duree; // Durée de l'événement
    private String formateurs; // Formateurs de l'événement
    private String modalitesParticipation; // Modalités de participation
    private double cout; // Coût de participation
    private String prerequis;
    private double averageRating; // Nouvel attribut pour la notation moyenne
    private String photoUrl; // URL de la photo de l'événement
    private double latitude;
    private double longitude;


    @ManyToMany(mappedBy = "eventList") // Assuming you want to cascade all operations
    private List<User> userList = new ArrayList<>();

    public void setDateEventFromString(String dateString) {
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(dateString);
            this.dateEvent = date;
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
    }
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<>();


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;

    public Event(long idEvent, String titleEvent, Date dateEvent, String description, int nbMaxInscrits, String location, String category, String duree, String formateurs, String modalitesParticipation, double cout, String prerequis) {
        this.idEvent = idEvent;
        this.titleEvent = titleEvent;
        this.dateEvent = dateEvent;
        this.description = description;
        this.nbMaxInscrits = nbMaxInscrits;
        this.location = location;
        this.category = category;
        this.duree = duree;
        this.formateurs = formateurs;
        this.modalitesParticipation = modalitesParticipation;
        this.cout = cout;
        this.prerequis = prerequis;
    }
    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", titleEvent='" + titleEvent + '\'' +
                ", dateEvent=" + dateEvent +
                ", description='" + description + '\'' +
                ", userListSize=" + (userList != null ? userList.size() : 0) +
                '}';
    }

}
