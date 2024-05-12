package tn.esprit.espritcollabbackend.restController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import tn.esprit.espritcollabbackend.entities.Buys;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.services.IBuys;
import tn.esprit.espritcollabbackend.services.IUser;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials="true")// ajoutineha bch el front najmt tchouf el back 5ater ma3ndhech nafs el port w zidna configuration fel security fel crosconfiguration
@RestController
@AllArgsConstructor
public class BuyRestController {
    private IBuys iBuys;
    private IUser iUser;
    @PostMapping("/addBuy")
    public Buys addBuy(@RequestBody Buys buy){
        return iBuys.addBuys(buy);
    }
    @GetMapping("/getAllBuy")
    public List<Buys> getAllBuy(){
        return iBuys.getAllBuys();
    }
    @GetMapping("/getBuy/{idBuy}")
    public Buys retrieveById(@PathVariable Long idBuy){
        return iBuys.retrieveById(idBuy);
    }
    @PutMapping("/updateBuy")
    public Buys updateBuy(@RequestBody Buys buy){
        return iBuys.updateBuys(buy);
    }
    @DeleteMapping("/deleteBuy/{idBuy}")
    public void deleteBuy(@PathVariable Long idBuy){
        iBuys.deleteBuys(idBuy);
    }
    @GetMapping("/myBuys/{id}")
    public List<Buys> getBuysByUser(@PathVariable Long id){
        User user=iUser.retrieveById(id);
        return iBuys.findAllByUser(user);
    }
    @GetMapping("/vente/{id}")
    public int getnb(@PathVariable Long id){
        return iBuys.nbrAchat(id);
    }
}
