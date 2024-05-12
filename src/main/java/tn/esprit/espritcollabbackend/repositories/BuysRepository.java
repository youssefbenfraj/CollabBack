package tn.esprit.espritcollabbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.espritcollabbackend.entities.Buys;
import tn.esprit.espritcollabbackend.entities.User;

import java.util.List;

public interface BuysRepository extends JpaRepository<Buys,Long> {
    List<Buys> findAllByUser(User user);
}
