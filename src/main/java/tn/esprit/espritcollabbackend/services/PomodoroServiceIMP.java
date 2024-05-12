package tn.esprit.espritcollabbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.espritcollabbackend.entities.Pomodoro;
import tn.esprit.espritcollabbackend.entities.Revision;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.PomodoroRepository;
import tn.esprit.espritcollabbackend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PomodoroServiceIMP implements IPomodoro{
    @Autowired

    private UserRepository userRepository;
    @Autowired
    private PomodoroRepository pomodoroRepository ;
    /*@Override

   public Pomodoro saveOrUpdatePomodoro(Pomodoro pomodoro ,Long userId) {
       return pomodoroRepository.save(pomodoro);
   } */
    @Override
    public Pomodoro saveOrUpdatePomodoro(Pomodoro r, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            r.setUser(user);
            return pomodoroRepository.save(r);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found");
        }
    }
}
