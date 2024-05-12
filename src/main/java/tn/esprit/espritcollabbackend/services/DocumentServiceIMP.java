package tn.esprit.espritcollabbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.espritcollabbackend.entities.Document;
import tn.esprit.espritcollabbackend.repositories.DocumentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DocumentServiceIMP implements IDocument{
    private DocumentRepository documentRepository;
    @Override
    public Document addDoc(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public List<Document> getAllDoc() {
        return documentRepository.findAll();
    }

    @Override
    public Document retrieveById(Long idDoc) {
        return documentRepository.findById(idDoc).orElse(null);
    }

    @Override
    public Document updateDoc(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public void deleteDocument(Long idDoc) {
    documentRepository.deleteById(idDoc);
    }

    @Override
    public List<Document> getMyDocs(Long idUser) {
        List<Document> res=new ArrayList<>();
        List<Document> allDoc=documentRepository.findAll();
        for (Document d:allDoc){
            if (d.getUser().getIdUser()==idUser)
                res.add(d);
        }
        return res;
    }
}
