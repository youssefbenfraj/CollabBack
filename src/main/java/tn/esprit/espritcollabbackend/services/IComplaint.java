package tn.esprit.espritcollabbackend.services;


import tn.esprit.espritcollabbackend.entities.Complaint;

import java.util.List;

public interface IComplaint {
    //public Complaint addComplaint(Complaint c);
    public Complaint addComplaint(Complaint c, Long userId);
    public List<Complaint> getComplaintsByUserId(Long userId);
    public Complaint retrievebyId (Long idc);
    public List<Complaint> retrieveAll();
    public void deleteById (Long idc) ;
    public Complaint updateCM (Complaint CM, long id) ;
}
