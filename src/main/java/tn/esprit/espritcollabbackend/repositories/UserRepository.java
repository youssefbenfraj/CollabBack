package tn.esprit.espritcollabbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.espritcollabbackend.dto.UserLeaderboardDTO;
import tn.esprit.espritcollabbackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    User findByResetPasswordToken(String resetPasswordToken);
    User findByVerificationToken(String verificationToken);
    @Query("SELECT new tn.esprit.espritcollabbackend.dto.UserLeaderboardDTO(u.idUser, u.username, u.imageUser, COALESCE(SUM(p.cycleCount), 0)) " +
            "FROM User u LEFT JOIN u.pomodoros p " +
            "GROUP BY u.idUser, u.username, u.imageUser " +
            "ORDER BY COALESCE(SUM(p.cycleCount), 0) DESC")
    List<UserLeaderboardDTO> findAllUsersWithTotalCycles();




}
