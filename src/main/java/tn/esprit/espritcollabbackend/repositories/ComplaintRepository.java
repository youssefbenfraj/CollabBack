package tn.esprit.espritcollabbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.espritcollabbackend.entities.Complaint;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
    List<Complaint> findByUserIdUser(Long userId);

}
