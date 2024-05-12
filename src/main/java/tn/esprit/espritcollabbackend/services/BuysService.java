package tn.esprit.espritcollabbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.espritcollabbackend.entities.Buys;
import tn.esprit.espritcollabbackend.entities.Document;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.BuysRepository;
import tn.esprit.espritcollabbackend.repositories.DocumentRepository;
import tn.esprit.espritcollabbackend.repositories.UserRepository;


import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BuysService implements IBuys{
    private BuysRepository buyRepository;
    private UserRepository userRepository;
    private DocumentRepository documentRepository;
    @Override
    public Buys addBuys(Buys buy) {
        int nb=0;
        List<Buys> allbuys=buyRepository.findAll();
        for (Buys b:allbuys){
            if (b.getUser().getIdUser()==buy.getUser().getIdUser() && b.getDocument().getIdDoc()==buy.getDocument().getIdDoc())
                nb++;
        }
        if (nb==0)
        return buyRepository.save(buy);
        return null;
    }

    @Override
    public List<Buys> getAllBuys() {
        return buyRepository.findAll();
    }

    @Override
    public Buys retrieveById(Long idBuys) {
        return buyRepository.findById(idBuys).orElse(null);
    }

    @Override
    public Buys updateBuys(Buys buy) {
        return buyRepository.save(buy);
    }

    @Override
    public void deleteBuys(Long idBuys) {
        buyRepository.deleteById(idBuys);
    }

    @Override
    public List<Buys> findAllByUser(User user) {
        List<Buys> res=new ArrayList<>();
        List<Buys> allbuys=buyRepository.findAll();
        for (Buys b : allbuys){
            if (b.getUser().getIdUser()==user.getIdUser())
                res.add(b);
        }
        return res;
    }

    @Override
    public int nbrAchat(Long idDoc) {
        int nb=0;
        Document document=documentRepository.findById(idDoc).get();
        List<Buys> allBuys = buyRepository.findAll();
        for (Buys b : allBuys){
            if (b.getDocument().getIdDoc()==document.getIdDoc())
                nb++;
        }
        return nb;
    }
}
