package tn.esprit.espritcollabbackend.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.espritcollabbackend.entities.Book;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.BookRepository;
import tn.esprit.espritcollabbackend.repositories.UserRepository;
import tn.esprit.espritcollabbackend.services.FilesStorageService;
import tn.esprit.espritcollabbackend.services.IBook;
import tn.esprit.espritcollabbackend.services.IUser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "https://086a-197-27-101-27.ngrok-free.app"}, maxAge = 3600, allowCredentials="true")

public class BookRestController {
    private IBook iBook;
    private BookRepository bookRepository;
    @Autowired
    FilesStorageService storageService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private IUser iUser;


    // Assuming iBook and storageService are already autowired
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@Valid @ModelAttribute Book book,
                                        @RequestParam("imageFile") MultipartFile imageFile,
                                        @RequestParam("userId") Long idUser
    ) {



        User user = iUser.retrieveById(idUser);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // User not found
        }

        // Set the cover image URL if file is provided
        if (imageFile != null && !imageFile.isEmpty()) {
            String savedImageUrl = storageService.saveImage(imageFile);
            String imageUrl = "http://localhost:8087/uploads/" + imageFile.getOriginalFilename();
            book.setCoverPicture(imageUrl);
        }

        // Set the user to the book
        book.setUser(user);
        user.getBooks().add(book);  // Optionally, maintain the relationship
        userRepository.save(user);  // Save user to update the relationship

        // Save the book
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }


    @GetMapping("/getBookById/{idb}")
    public Book retrieveBookbyId(@PathVariable Long idb){
        return iBook.retrieveBookbyId(idb);
    }
    @GetMapping("/getAllBK")
    public List<Book> retrieveAllB(){
        return iBook.retrieveAllBook();
    }
    @DeleteMapping("/deleteBK/{idb}")
    public void deleteById(@PathVariable Long idb){
        iBook.deleteBookById(idb);
    }
    @PutMapping("/updateBK/{idb}")

    public Book updateBK(@RequestBody  Book BK, @PathVariable  long idb){
        return iBook.updateBK(BK,idb);
    }
    @PutMapping("/updateBook/{bookId}")
    public Book updatePhoneNumber(@PathVariable Long bookId, @RequestBody String phoneNumber) {
        Book updatedBook = iBook.updatePhoneNumber(bookId, phoneNumber);
        return  updatedBook;
    }
    @PostMapping("/like/{bookId}/{userId}")
    public Book likeBook(@PathVariable Long bookId, @PathVariable Long userId) {
        return iBook.likeBook(userId, bookId);
    }

    @PostMapping("/dislike/{bookId}/{userId}")
    public Book dislikeBook(@PathVariable Long bookId, @PathVariable Long userId) {
        return iBook.dislikeBook(userId, bookId);
    }


}
