package tn.esprit.espritcollabbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.espritcollabbackend.entities.Document;


public interface DocumentRepository extends JpaRepository<Document,Long> {
}
