package tn.esprit.espritcollabbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Pomodoro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startTime;
    private Date endTime;
    private int breakTime;

    @ManyToOne
    @JsonIgnore
    private User user;
    private String status;
    private Long cycleCount;





    public Long getCycleCount() {
        return cycleCount;
    }

    public void setCycleCount(Long cycleCount) {
        this.cycleCount = cycleCount;
    }
}
