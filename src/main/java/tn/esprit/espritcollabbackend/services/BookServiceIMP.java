package tn.esprit.espritcollabbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.espritcollabbackend.entities.Book;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.BookRepository;
import tn.esprit.espritcollabbackend.repositories.UserRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
@AllArgsConstructor
public class BookServiceIMP implements IBook {
    private BookRepository bookRepository;
    private final UserRepository userRepository;
    private final FilesStorageService filesStorageService;

    @Override
    public Book addBook(Book book, MultipartFile imageFile, Long userId) {
        try {
            // Check if the image file is provided and save it
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = filesStorageService.saveImage1(imageFile);
                book.setCoverPicture(imageUrl); // Assuming there is a setter for coverPicture
            }

            // Retrieve user by userId
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Link the book to the user
            book.setUser(user);

            // Optionally add the book to the user's collection if you manage the relationship on both sides
            user.getBooks().add(book);

            // Save the user to ensure the association is persisted
            userRepository.save(user);

            // Save the book
            Book savedBook = bookRepository.save(book);

            return savedBook;
        } catch (Exception e) {
            throw new RuntimeException("Failed to add book. Error: " + e.getMessage());
        }
    }

    @Override
    public Book retrieveBookbyId(Long idb) {
        return bookRepository.findById(idb).orElse(null);
    }

    @Override
    public List<Book> retrieveAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBookById(Long idb) {
        bookRepository.deleteById(idb);

    }

    @Override
    public Book updateBK(Book BK, long idb) {
        Book b = bookRepository.findById(idb).get();
        if (BK.getTitleBook() != null) {
            b.setTitleBook(BK.getTitleBook());
        }
        if (BK.getDescription() != null) {
            b.setDescription(BK.getDescription());
        }
        if (BK.getLanguage() != null) {
            b.setLanguage(BK.getLanguage());
        }

        if (BK.getCoverPicture() != null) {
            b.setCoverPicture(BK.getCoverPicture());
        }

        if (BK.getIsAvailable() != null) {
            b.setIsAvailable(BK.getIsAvailable());
        }


        return bookRepository.save(b);
    }

    ;

    public Book updatePhoneNumber(Long bookId, String phoneNumber) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setPhoneNumber(phoneNumber);
        return bookRepository.save(book);
    }

    public Book likeBook(Long userId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + bookId));

        // Check if the user has already liked the book
        if (!book.getLikedBy().contains(userId)) {
            book.getLikedBy().add(userId);
            bookRepository.save(book);
        }

        return book;
    }

    @Override
    public Book dislikeBook(Long userId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + bookId));

        // Check if the user has already disliked the book
        if (!book.getDislikedBy().contains(userId)) {
            book.getDislikedBy().add(userId);
            bookRepository.save(book);
        }

        return book;
    }
}




