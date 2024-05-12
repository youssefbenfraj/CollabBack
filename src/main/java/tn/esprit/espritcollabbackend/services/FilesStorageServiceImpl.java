package tn.esprit.espritcollabbackend.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    private final Path root = Paths.get("./uploads");

    @Override
    public void init() {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!", e);
        }
    }

    @Override
    public String saveImage(MultipartFile file) {
        try {
            Path filePath = this.root.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath);
            return filePath.toString();
        } catch (Exception e) {
            throw new RuntimeException("Could not save the image: " + e.getMessage());
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public String saveImage1(MultipartFile file) {
        try {
            String mimeType = file.getContentType();
            List<String> allowedMimeTypes = Arrays.asList("image/jpeg","image/jpg", "image/png", "application/pdf", "text/plain");
            if (!allowedMimeTypes.contains(mimeType)) {
                throw new RuntimeException("File type not supported. Allowed types: JPEG, PNG, PDF, TXT.");
            }

            Path filePath = this.root.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (Exception e) {
            throw new RuntimeException("Could not save the image: " + e.getMessage());
        }
    }
    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(String filename) {
        try {
            Path file = root.resolve(filename);
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
