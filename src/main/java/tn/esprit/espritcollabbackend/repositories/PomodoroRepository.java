package tn.esprit.espritcollabbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
 import tn.esprit.espritcollabbackend.entities.Pomodoro;

public interface PomodoroRepository  extends JpaRepository<Pomodoro,Long> {
}
