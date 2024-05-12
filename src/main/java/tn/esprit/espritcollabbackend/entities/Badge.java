package tn.esprit.espritcollabbackend.entities;
import jakarta.persistence.*;

@Entity
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;
    private int threshold; // Number of cycles required to achieve this badge

    public Badge() {}

    public Badge(String name, String imageUrl, int threshold) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.threshold = threshold;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

}