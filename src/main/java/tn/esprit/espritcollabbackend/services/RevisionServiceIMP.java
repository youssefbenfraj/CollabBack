package tn.esprit.espritcollabbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import tn.esprit.espritcollabbackend.entities.Revision;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.RevisionRepository;
import tn.esprit.espritcollabbackend.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

@CrossOrigin(origins = {"http://localhost:4200", "https://2e97-197-31-160-181.ngrok-free.app"}, maxAge = 3600, allowCredentials="true")
public class RevisionServiceIMP implements IRevision{
    @Autowired
    private RevisionRepository revisionRepository ;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Revision addRevision(Revision r, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            r.setUser(user);
            return revisionRepository.save(r);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found");
        }
    }

    @Override
    public Revision retrievebyId(Long idr) {
        return revisionRepository.findById(idr).orElse(null);
    }

    @Override
    public List<Revision> retrieveAll() {
        return revisionRepository.findAll();
    }


    @Override
    public Revision updateRV(Revision RV, long id) {
        Revision r = revisionRepository.findById(id).get();
        if (RV.getDate_debut()!=null){
            r.setDate_debut(RV.getDate_debut());
        }

        if (RV.getSujetRev()!=null){
            r.setSujetRev(RV.getSujetRev());
        }


        if (RV.getObjectif()!=null){
            r.setObjectif(RV.getObjectif());}

        return revisionRepository.save(r);
    }

    @Override
    public List<Revision> retrieveAllForUser(Long userId) {
        List<Revision> allRevisions = revisionRepository.findAll();
        List<Revision> revisionsForUser = new ArrayList<>();

        for (Revision revision : allRevisions) {
            if (revision.getUser().getIdUser() == userId) {
                revisionsForUser.add(revision);
            }
        }
        return revisionsForUser;
    }

    @Override
    public List<Revision> getRevisionsByUserId(Long userId) {
        return revisionRepository.findByUserIdUser(userId);

    }

    @Override
    public void deleteByIdb(Long idV) {
        try{
            revisionRepository.deleteById(idV);

        }
        catch (Exception ex){
            throw ex;
        }

    }

}
