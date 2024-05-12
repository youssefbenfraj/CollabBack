package tn.esprit.espritcollabbackend.restController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.espritcollabbackend.entities.Document;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.services.IDocument;
import tn.esprit.espritcollabbackend.services.IUser;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")// ajoutineha bch el front najmt tchouf el back 5ater ma3ndhech nafs el port w zidna configuration fel security fel crosconfiguration
@RestController
@AllArgsConstructor
public class DocumentRestController {
    private IDocument iDocument;
    private IUser iUser;
    @PostMapping("/addDoc/{id}")
    public Document addDoc(@RequestBody Document document,@PathVariable Long id){
        User u=iUser.retrieveById(id);
        document.setUser(u);
        System.out.println("Received Document: " + document);
        return iDocument.addDoc(document);
    }
    @GetMapping("/getAllDoc")
    public List<Document> getAllDoc(){
        return iDocument.getAllDoc();
    }
    @GetMapping("/getDoc/{idDoc}")
    public Document retrieveById(@PathVariable Long idDoc){
        return iDocument.retrieveById(idDoc);
    }
    @PutMapping("/updateDoc/{id}")
    public Document updateDoc(@RequestBody Document document,@PathVariable Long id){
        User u=iUser.retrieveById(id);
        document.setUser(u);
        System.out.println("Received Document: " + document);
        return iDocument.updateDoc(document);
    }
    @DeleteMapping("/deleteDoc/{idDoc}")
    public void deleteDocument(@PathVariable Long idDoc){
        iDocument.deleteDocument(idDoc);
    }
    @GetMapping("/getmy/{id}")
    public List<Document> getMyDocs(@PathVariable Long id){
        return iDocument.getMyDocs(id);
    }
}