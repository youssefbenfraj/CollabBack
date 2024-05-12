package tn.esprit.espritcollabbackend.services;


import tn.esprit.espritcollabbackend.entities.Buys;
import tn.esprit.espritcollabbackend.entities.Document;
import tn.esprit.espritcollabbackend.entities.User;

import java.util.List;

public interface IBuys {
    public Buys addBuys(Buys buy);
    public List<Buys> getAllBuys();
    public Buys retrieveById(Long idBuys);
    public Buys updateBuys(Buys buy);
    public void deleteBuys(Long idBuys);
    public List<Buys> findAllByUser(User user);
    public int nbrAchat(Long id);
}
