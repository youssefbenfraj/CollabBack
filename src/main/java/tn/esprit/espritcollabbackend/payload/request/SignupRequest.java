package tn.esprit.espritcollabbackend.payload.request;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class SignupRequest {


    private String username;

    @Size(max = 50)
    private String email;

    private Set<String> role;


    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String lastName;


    @Temporal(TemporalType.DATE)
    private Date birthdate;

    private MultipartFile imageUser;


    private String level;


    private String section;


    private int classNumber;


    private String major;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private String facebookUsername;
    private String instagramUsername;
    private String youtubeProfileUrl;
    private String linkedinProfileUrl;
    private String description;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public MultipartFile  getImageUser() {
        return imageUser;
    }

    public void setImageUser(MultipartFile  imageUser) {
        this.imageUser = imageUser;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }

    public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

    public String getInstagramUsername() {
        return instagramUsername;
    }

    public void setInstagramUsername(String instagramUsername) {
        this.instagramUsername = instagramUsername;
    }

    public String getYoutubeProfileUrl() {
        return youtubeProfileUrl;
    }

    public void setYoutubeProfileUrl(String youtubeProfileUrl) {
        this.youtubeProfileUrl = youtubeProfileUrl;
    }

    public String getLinkedinProfileUrl() {
        return linkedinProfileUrl;
    }

    public void setLinkedinProfileUrl(String linkedinProfileUrl) {
        this.linkedinProfileUrl = linkedinProfileUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBirthdateFromString(String dateString) {
        try {
            DateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            Date date = inputFormat.parse(dateString);
            this.birthdate = date;
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
    }



}

