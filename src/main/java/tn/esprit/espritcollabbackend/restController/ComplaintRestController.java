package tn.esprit.espritcollabbackend.restController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.espritcollabbackend.entities.Complaint;
import tn.esprit.espritcollabbackend.entities.Traitement;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.services.IComplaint;
import tn.esprit.espritcollabbackend.services.IUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")// ajoutineha bch el front najmt tchouf el back 5ater ma3ndhech nafs el port w zidna configuration fel security fel crosconfiguration

@RestController
@AllArgsConstructor
public class ComplaintRestController {
    private IComplaint iComplaint;
    private IUser iUser;


    //@PostMapping("/addComplaint")
    //public Complaint addComplaint(@RequestBody Complaint c){
      //  return iComplaint.addComplaint(c);
    //}
    @PostMapping("/addComplaint")
    public Complaint addComplaint(@RequestBody Map<String, Object> request) {
        Long userId = Long.parseLong(request.get("userId").toString());
        String comment = (String) request.get("comment");
        String traitementString = (String) request.get("traitement");

        // Convertir la chaîne de traitement en enum Traitement
        Traitement traitement = Traitement.valueOf(traitementString);

        // Créer l'objet Complaint
        Complaint complaint = new Complaint();
        complaint.setComment(comment);
        complaint.setTraitement(traitement);
        complaint.setDateComplaint(LocalDate.now()); // Définir la date actuelle

        // Récupérer l'utilisateur par son ID
        User user = iUser.retrieveById(userId);
        complaint.setUser(user);

        // Enregistrer la plainte
        return iComplaint.addComplaint(complaint,userId);
    }
    @GetMapping("/userCM/{userId}")
    public ResponseEntity<List<Complaint>> getAllComplaintsByUser(@PathVariable Long userId) {
        List<Complaint> complaints = iComplaint.getComplaintsByUserId(userId);
        if (complaints.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(complaints);
    }




    @GetMapping("/getComplaintById/{idc}")
    public Complaint retrievebyId(@PathVariable Long idc){
        return iComplaint.retrievebyId(idc);
    }
    @GetMapping("/getAllCM")
    public List<Complaint> retrieveAll(){
        return iComplaint.retrieveAll();
    }
    @DeleteMapping("/deleteCM/{idc}")
    public void deleteById(@PathVariable Long idc){
        iComplaint.deleteById(idc);
    }

    @PutMapping("/updateCM/{id}")

    public Complaint updateCM(@RequestBody Complaint CM, @PathVariable  long id){
        return iComplaint.updateCM(CM,id);
    }

}
