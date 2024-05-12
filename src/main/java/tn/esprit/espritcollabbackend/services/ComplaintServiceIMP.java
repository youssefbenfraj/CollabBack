package tn.esprit.espritcollabbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.espritcollabbackend.entities.Complaint;
import tn.esprit.espritcollabbackend.entities.Traitement;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.ComplaintRepository;
import tn.esprit.espritcollabbackend.repositories.UserRepository;

import java.util.List;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ComplaintServiceIMP implements IComplaint{
    private ComplaintRepository complaintRepository;
    private UserRepository userRepository ;



    // @Override
    //public Complaint addComplaint(Complaint c) {

      //  c.setTraitement(Traitement.NONTRAITE);
        //c.getDateComplaint()
        //return complaintRepository.save(c);
    //}

    @Override
    public Complaint addComplaint(Complaint c, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            c.setUser(user);
            c.setTraitement(Traitement.NONTRAITE);
            return complaintRepository.save(c);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found");
        }
    }

    @Override
    public List<Complaint> getComplaintsByUserId(Long userId) {
        return complaintRepository.findByUserIdUser(userId);
    }

    @Override
    public Complaint retrievebyId(Long idc) {
        return complaintRepository.findById(idc).orElse(null);
    }

    @Override
    public List<Complaint> retrieveAll() {
        return complaintRepository.findAll();
    }

    @Override
    public void deleteById(Long idc) {
        complaintRepository.deleteById(idc);

    }

    @Override
    public Complaint updateCM(Complaint CM, long id) {
        Complaint c = complaintRepository.findById(id).get();
        if (CM.getDateComplaint()!=null){
            c.setDateComplaint(CM.getDateComplaint());
        }
        if (CM.getComment()!=null){
            c.setComment(CM.getComment());
        }
        if (CM.getTraitement()!=null){
            c.setTraitement(CM.getTraitement());
        }

        return complaintRepository.save(c);    }
}
