package tn.esprit.espritcollabbackend.restController;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.Duration;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.espritcollabbackend.dto.UserLeaderboardDTO;
import tn.esprit.espritcollabbackend.entities.Role;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.payload.request.SignupRequest;
import tn.esprit.espritcollabbackend.payload.request.response.MessageResponse;
import tn.esprit.espritcollabbackend.repositories.UserRepository;
import tn.esprit.espritcollabbackend.security.jwt.JwtUtils;
import tn.esprit.espritcollabbackend.services.FilesStorageService;
import tn.esprit.espritcollabbackend.services.IUser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins  = {"*", "https://b94d-197-27-101-27.ngrok-free.app "}, maxAge = 3600, allowCredentials="true")
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
    @Autowired
    private IUser userService;
    @Autowired
    private IUser iUser;
    @Autowired
    FilesStorageService storageService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;
    @CrossOrigin(origins  = {"http://localhost:4200", "https://2e97-197-31-160-181.ngrok-free.app"}, maxAge = 3600, allowCredentials="true")
    @PostMapping("/{userId}/deactivate")
    public ResponseEntity<String> deactivateAccount(@PathVariable long userId, @RequestParam("duration") String durationStr) {
        try {
            // Parse the duration
            Duration duration = Duration.parse(durationStr);

            // Deactivate the account
            userService.deactivateAccount(userId, duration);

            return ResponseEntity.ok("User account deactivated successfully.");
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid duration format. Please provide duration in ISO-8601 format.");
        }
    }

    @PostMapping("/{userId}/reactivate")
    public ResponseEntity<String> reactivateAccount(@PathVariable long userId) {
        logger.info("Attempting to reactivate user with ID: {}", userId);
        try {
            userService.reactivateAccount(userId);
            return ResponseEntity.ok("User account reactivated successfully.");
        } catch (Exception e) {
            logger.error("Error reactivating user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reactivating user");
        }
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @ModelAttribute SignupRequest signUpRequest,
                                     @RequestParam("file") MultipartFile file) throws ParseException {
        try {
            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
            }

            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already in use!"));
            }

            String imageUser = storageService.saveImage(file);
            signUpRequest.setBirthdateFromString(String.valueOf(signUpRequest.getBirthdate()));
            String imageUrl = "http://localhost:8087/uploads/" + file.getOriginalFilename(); // Adjust if necessary

            User user = new User(signUpRequest.getFirstName(),
                    signUpRequest.getLastName(),
                    signUpRequest.getEmail(),
                    signUpRequest.getUsername(),
                    signUpRequest.getBirthdate(),
                    encoder.encode(signUpRequest.getPassword()),
                    imageUrl,
                    signUpRequest.getLevel(),
                    signUpRequest.getClassNumber(),
                    signUpRequest.getMajor(),
                    signUpRequest.getDescription()

            );

            user.setRole(Role.STUDENT);

            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Failed to register user: " + e.getMessage()));
        }
    }


    //   @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUserById/{id}")
    public User retrieveById(@PathVariable Long id) {
        return iUser.retrieveById(id);
    }
  // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllUsers")
    public List<User> retrieveAllUsers() {
        return iUser.retrieveAllUsers();
    }
  //  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/updateUser/{id}")
  public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable Long id) {
      try {
          iUser.updateUser(id, updateUser);
          return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
      } catch (EntityNotFoundException e) {
          return ResponseEntity.notFound().build();
      } catch (Exception e) {
          return ResponseEntity
                  .status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(new MessageResponse("An error occurred while updating user"));

      }
  }

   //@PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping("/deleteUser/{id}")
    public void deleteById(@PathVariable Long id) {
        try {
            System.out.println("Received user ID: " + id);
            iUser.deleteUser(id); // Assuming iUser is an instance of a service class
            System.out.println("User deleted successfully");
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    @PutMapping("/assigneventtouser/{idUser}/{idEvent}")
    public User AssignUserToList(@PathVariable Long idUser,@PathVariable Long idEvent){
        return iUser.assignUserToEvent(idUser,idEvent);
    }
    @GetMapping("/leaderboardStudent")
    public ResponseEntity<List<UserLeaderboardDTO>> getLeaderboardS() {
        try {
            List<UserLeaderboardDTO> leaderboard = userService.getLeaderboardS();
            if (leaderboard.isEmpty()) {
                logger.info("No leaderboard data found.");
                return ResponseEntity.noContent().build();  // Appropriate for no content scenario
            }
            return ResponseEntity.ok(leaderboard);
        } catch (Exception e) {
            logger.error("Failed to fetch leaderboard: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/{pomodoroId}")
    public ResponseEntity<?> updatePomodoro(@PathVariable Long pomodoroId, @RequestBody Long newCycleCount) {
        try {
            userService.updatePomodoro(pomodoroId, newCycleCount);
            return ResponseEntity.ok().body("Pomodoro updated and badge recalculated successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error updating Pomodoro: " + e.getMessage());
        }
    }
}
