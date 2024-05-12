package tn.esprit.espritcollabbackend.services;

import org.springframework.web.bind.annotation.CrossOrigin;
import tn.esprit.espritcollabbackend.entities.Revision;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:4200", "https://2e97-197-31-160-181.ngrok-free.app"}, maxAge = 3600, allowCredentials="true")
public interface IRevision {
     public Revision addRevision(Revision r , Long UserId);
     public Revision retrievebyId (Long idr);
    public List<Revision> retrieveAll();
     public Revision updateRV (Revision RV, long id) ;
     public List<Revision> retrieveAllForUser(Long userId) ;

     public List<Revision> getRevisionsByUserId(Long userId);


    void deleteByIdb(Long idV);
}
