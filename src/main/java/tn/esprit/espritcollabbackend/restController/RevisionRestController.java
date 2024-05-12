package tn.esprit.espritcollabbackend.restController;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.espritcollabbackend.entities.Revision;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.RevisionRepository;
import tn.esprit.espritcollabbackend.repositories.UserRepository;
import tn.esprit.espritcollabbackend.services.IRevision;
import tn.esprit.espritcollabbackend.services.IUser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import tn.esprit.espritcollabbackend.security.services.UserDetailsImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials="true")

public class RevisionRestController {
    @Autowired
    private IRevision iRevision;
    @Autowired
    private RevisionRepository revisionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private IUser iUser;
    @PostMapping("/addRevision")
    public Revision addRevision(@RequestBody Map<String, Object> request) {
        Long userId = Long.parseLong(request.get("userId").toString());
        String sujetRev = (String) request.get("sujetRev");
        String objectif = (String) request.get("objectif");
        String dateString = (String) request.get("date_debut"); // Récupérer la chaîne de date

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date_debut;
        try {
            date_debut = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date string: " + e.getMessage());
        }
        try {
            // Créer l'objet Revision
            Revision revision = new Revision();
            revision.setObjectif(objectif);
            revision.setSujetRev(sujetRev);
            revision.setDate_debut(date_debut);

            // Ajouter l'objet Revision avec l'ID de l'utilisateur spécifié
            var res =  iRevision.addRevision(revision, userId);
            return res;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/userRv/{userId}")
    public ResponseEntity<List<Revision>> getAllRevisionsByUser(@PathVariable Long userId) {
        List<Revision> revisions = iRevision.getRevisionsByUserId(userId);
        if (revisions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(revisions);
    }

    @PostMapping("/addRevisionTest")
    public Revision addRevisiontest(@RequestBody Map<String, Object> request,
                                    Authentication authentication) {
        Long userId = Long.parseLong(request.get("userId").toString());
        String sujetRev = (String) request.get("sujetRev");
        String objectif = (String) request.get("objectif");
        String dateString = (String) request.get("date_debut"); // Récupérer la chaîne de date

        // Convertir la chaîne de date en objet Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date_debut;
        try {
            date_debut = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date string: " + e.getMessage());
        }
        // Créer l'objet Revision
        Revision revision = new Revision();
        revision.setObjectif(objectif);
        revision.setSujetRev(sujetRev);
        revision.setDate_debut(date_debut);

        // Ajouter l'objet Revision avec l'ID de l'utilisateur spécifié
        return iRevision.addRevision(revision, userId);
    }


    @GetMapping("/getRevisionById/{idr}")
    public Revision retrievebyId(@PathVariable Long idr){
        return iRevision.retrievebyId(idr);
    }

    @DeleteMapping("/deleteRV/{idV}")
    public void deleteById(@PathVariable Long idV){
        iRevision.deleteByIdb((long)idV);
    }

    @DeleteMapping("/delete")
    public void deleteById(){
        throw new RuntimeException("x");
    }

    @PutMapping("/updateRV/{id}")

    public Revision updateRV(@RequestBody  Revision RV, @PathVariable  Long id){
        return iRevision.updateRV(RV,(long)id);
    }


    @GetMapping("/userRV")
    public List<Revision> getAllRevisionsForUser(@RequestParam Long userId) {
        return iRevision.retrieveAllForUser(userId);
    }
    @GetMapping("/getAllRV")
    public List<Revision> retrieveAll(){
        return iRevision.retrieveAll();
    }
   /* @GetMapping("/getAllRV")
    public ResponseEntity<List<Revision>> retrieveAll(@RequestParam Long userId) {
        // Utilisez le service pour récupérer toutes les révisions de l'utilisateur
        List<Revision> revisions = iRevision.getAllRevisionsByUserId(userId);

        // Retournez les révisions récupérées
        return ResponseEntity.ok(revisions);
    }
*/

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }

        @GetMapping("/getAllRV/{userId}")
        public List<Revision> getAllRevisionsForUser(@PathVariable Long userId) {
            // Vérifie si l'utilisateur existe
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                // Gérer le cas où l'utilisateur n'est pas trouvé
                throw new UserNotFoundException("Utilisateur non trouvé avec l'ID: " + userId);
            }

            // Récupère toutes les révisions associées à cet utilisateur
            return revisionRepository.findByUser_IdUser(userId);
        }


    } }
