package tn.esprit.espritcollabbackend.services;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.espritcollabbackend.dto.UserLeaderboardDTO;
import tn.esprit.espritcollabbackend.entities.User;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUser  {


    User addUser(User user, MultipartFile imageFile);

    User retrieveById(Long id);


    void updateUser(Long userId, User updateUser);

    List<User> retrieveAllUsers();
    void deleteUser(Long id);

  User findByEmail(String email);

    User findByUsername(String username);


    boolean verifyEmail(String verificationToken);


    void deactivateAccount(long userId, Duration duration);


    void reactivateAccount(long userId);
    boolean isUserActive(long userId);
    public User assignUserToEvent(Long idUser, Long idEvent);

    List<UserLeaderboardDTO> getLeaderboardS();
     void updatePomodoro(Long pomodoroId, Long newCycleCount) ;
}

