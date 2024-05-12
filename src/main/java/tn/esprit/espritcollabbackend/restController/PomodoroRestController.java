package tn.esprit.espritcollabbackend.restController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.espritcollabbackend.entities.Pomodoro;
import tn.esprit.espritcollabbackend.entities.Revision;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.PomodoroRepository;
import tn.esprit.espritcollabbackend.repositories.UserRepository;
import tn.esprit.espritcollabbackend.services.IPomodoro;
import tn.esprit.espritcollabbackend.services.IUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

public class PomodoroRestController {
    private IPomodoro iPomodoro;
    private PomodoroRepository pomodoroRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private IUser iUser;
    @PostMapping("/saveOrUpdatePomodoro")
    public Pomodoro saveOrUpdatePomodoro(@RequestBody Map<String, Object> request) {
        Long userId = Long.parseLong(request.get("userId").toString());
        int breakTime = (int) request.get("breakTime");
        int cycleCount = (int) request.get("cycleCount");
        String status = (String) request.get("status");
        String dateString = (String) request.get("endTime"); // Récupérer la chaîne de date
        // String startTime = (String) request.get("startTime"); // Récupérer la chaîne de date

        // Convertir la chaîne de date en objet Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date endTime ;
        try {
            endTime = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date string: " + e.getMessage());
        }


        // Créer l'objet Revision
        Pomodoro pomodoro = new Pomodoro() ;
        pomodoro.setBreakTime(breakTime);
        pomodoro.setStatus(status);
        pomodoro.setCycleCount((long) cycleCount);
        pomodoro.setEndTime(endTime);
//pomodoro.setStartTime(startTime);
        // Ajouter l'objet Revision avec l'ID de l'utilisateur spécifié
        return iPomodoro.saveOrUpdatePomodoro(pomodoro, userId);
    }


    /*@PostMapping("/api/pomodoro")
    public ResponseEntity<Pomodoro> addBook(@ModelAttribute Pomodoro pomodoro,
                                         @RequestParam("userId") Long idUser
    ) {
        User user = iUser.retrieveById(idUser);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // User not found
        }
        // Set the user to the book
        pomodoro.setUser(user);
        //user.getBooks().add(book);  // Optionally, maintain the relationship
        userRepository.save(user);  // Save user to update the relationship

        // Save the book
        Pomodoro savedBook = pomodoroRepository.save(pomodoro);
        return ResponseEntity.ok(savedBook);
    } */

}
    /* @PostMapping("/api/pomodoro")
     public Pomodoro createOrUpdatePomodoro(@RequestBody Pomodoro pomodoro) {
         return iPomodoro.saveOrUpdatePomodoro(pomodoro);
     }*/
   /* @PostMapping("/api/pomodoro")
    public Pomodoro createOrUpdatePomodoro(@ModelAttribute Pomodoro pomodoro,
                                           @RequestParam("userId") Long idUser ) {
        return iPomodoro.saveOrUpdatePomodoro(pomodoro ,idUser);
    } } */
   /* @PostMapping("/addBook")
    public ResponseEntity<Pomodoro> createOrUpdatePomodoro(@ModelAttribute Pomodoro pomodoro,
                                                @RequestParam("userId") Long idUser
    ) {

        User user = iUser.retrieveById(idUser);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // User not found
        }



        // Set the user to the book
        pomodoro.setUser(user);
        //  user.getBooks().add(book);  // Optionally, maintain the relationship
        userRepository.save(user);  // Save user to update the relationship

        // Save the book
        Pomodoro savedP = pomodoroRepository.save(pomodoro);
        return ResponseEntity.ok(savedP);
    }


}
*/