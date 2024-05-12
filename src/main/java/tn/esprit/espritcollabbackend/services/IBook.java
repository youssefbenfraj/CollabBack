package tn.esprit.espritcollabbackend.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.espritcollabbackend.entities.Book;

import java.util.List;

public interface IBook
{
    public Book addBook(Book book, MultipartFile imageFile, Long userId) ;

        public Book retrieveBookbyId (Long idb);
    public List<Book> retrieveAllBook();
    public void deleteBookById (Long idb) ;
    public Book updateBK (Book BK, long idb) ;
    public Book updatePhoneNumber(Long bookId, String phoneNumber);
    public Book dislikeBook(Long userId, Long bookId) ;
    public Book likeBook(Long userId, Long bookId);

}
