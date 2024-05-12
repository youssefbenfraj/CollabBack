package tn.esprit.espritcollabbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import tn.esprit.espritcollabbackend.entities.Role;
import tn.esprit.espritcollabbackend.entities.Role;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.espritcollabbackend.security.BooleanToIntegerConverter;

import java.io.Serializable;
import java.time.Duration;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    private String firstName;
    @Pattern(regexp = "[a-zA-Z]+", message = "Last name should contain only letters")
    private String lastName;
    @Getter
    private String email;
    private String username;
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    private String password;
    private String imageUser ;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String description;
    private String level;
    private String section;
    private String phoneNumber;

    private int classNumber;

    private String major;
    private String facebookUsername;
    private String instagramUsername;
    private String youtubeProfileUrl;
    private String linkedinProfileUrl;

    private String resetPasswordToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date resetPasswordTokenExpiry;
    @Convert(converter = BooleanToIntegerConverter.class)

    private boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deactivationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reactivationDate;
    @Transient
    private Duration deactivationDuration;
    @ManyToOne
    private Badge badge;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnore
    private List<Event> eventList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Complaint> complaints;
    @OneToMany(mappedBy = "user")
    private List<Revision> revisions;
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
    @OneToMany(mappedBy = "user")
    private List<Book> books;

    @OneToMany(mappedBy = "user")
    private List<Modules> modules;
    private boolean verified;
    private String verificationToken;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore

    private List<Pomodoro> pomodoros = new ArrayList<>();
    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    public User() {
    }

    public User(long idUser, String firstName, String lastName, String email, String username, Date birthdate, String password, String imageUser, Role role, String description, String level, int classNumber, String major) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.birthdate = birthdate;
        this.password = password;
        this.imageUser = imageUser;
        this.role = role;
        this.description = description;
        this.level = level;
        this.classNumber = classNumber;
        this.major = major;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String email, String username, String password, Role role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String firstName, String lastName, String email, String username, Date birthdate, String password, String imageUser, String level,  int classNumber, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.birthdate = birthdate;
        this.password = password;
        this.imageUser = imageUser;
        this.level = level;

        this.classNumber = classNumber;
        this.major = major;
    }

    public User(String imageUser) {
    }

    public User(String firstName, String lastName, String email, String username, Date birthdate, String password, String imageUser, Role role, String description, String level, String section, int classNumber, String major, String facebookUsername, String instagramUsername, String youtubeProfileUrl, String linkedinProfileUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.birthdate = birthdate;
        this.password = password;
        this.imageUser = imageUser;
        this.role = role;
        this.description = description;
        this.level = level;
        this.section = section;
        this.classNumber = classNumber;
        this.major = major;
        this.facebookUsername = facebookUsername;
        this.instagramUsername = instagramUsername;
        this.youtubeProfileUrl = youtubeProfileUrl;
        this.linkedinProfileUrl = linkedinProfileUrl;
    }

    public User(String firstName, String lastName, String email, String username, Date birthdate, String encode, String imageUser, String level, int classNumber, String major, String description, String facebookUsername, String instagramUsername, String linkedinProfileUrl, String youtubeProfileUrl) {
    }

    public User(String firstName, String lastName, String email, String username, Date birthdate, String encode, String imageUrl, String level, int classNumber, String major, String description) {
    }

    public User(long idUser, String email) {
        this.idUser = idUser;
        this.email = email;
    }



    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                // Include IDs of related entities instead of full objects
                ", eventsCount=" + (eventList != null ? eventList.size() : 0) +
                '}';
    }


}
