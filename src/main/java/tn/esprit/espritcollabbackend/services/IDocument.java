package tn.esprit.espritcollabbackend.services;


import tn.esprit.espritcollabbackend.entities.Document;

import java.util.List;

public interface IDocument {
    public Document addDoc(Document document);
    public List<Document> getAllDoc();
    public Document retrieveById(Long idDoc);
    public Document updateDoc(Document document);
    public void deleteDocument(Long idDoc);
    public List<Document> getMyDocs(Long idUser);
}
