package tn.esprit.espritcollabbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.espritcollabbackend.entities.Revision;

import java.util.List;

public interface RevisionRepository extends JpaRepository<Revision,Long> {

 List<Revision> findByUser_IdUser(Long userId);
 List<Revision> findByUserIdUser(Long userId);


}
