package tn.esprit.espritcollabbackend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.espritcollabbackend.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}