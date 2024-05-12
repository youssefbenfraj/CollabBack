package tn.esprit.espritcollabbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.espritcollabbackend.entities.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
