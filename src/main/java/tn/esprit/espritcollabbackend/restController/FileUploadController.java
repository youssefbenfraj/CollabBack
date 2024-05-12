package tn.esprit.espritcollabbackend.restController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tn.esprit.espritcollabbackend.entities.Document;
import tn.esprit.espritcollabbackend.services.IDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials="true")// ajoutineha bch el front najmt tchouf el back 5ater ma3ndhech nafs el port w zidna configuration fel security fel crosconfiguration
@AllArgsConstructor
@Controller
@RequestMapping("/files")
public class FileUploadController {
    IDocument iDocument;
    private static final String UPLOAD_DIR = "uploads/";
    private static final String UPLOAD_DIR2 = "uploads" + File.separator;

    @PostMapping("/upload/{idDoc}")
    public ResponseEntity<?> uploadMultipleFiles(@PathVariable("idDoc") Long idDoc, @RequestParam("files") MultipartFile[] files) {
        Document document = iDocument.retrieveById(idDoc);
        StringBuilder message = new StringBuilder();

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; // skip empty files
            }

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_DIR + document.getTitleDoc() + document.getIdDoc().toString() + "_" + file.getOriginalFilename());
                Files.createDirectories(path.getParent());
                Files.write(path, bytes);

                message.append("You successfully uploaded '").append(file.getOriginalFilename()).append("'; ");
            } catch (IOException e) {
                return ResponseEntity.internalServerError().body("Failed to upload '" + file.getOriginalFilename() + "': " + e.getMessage());
            }
        }

        return ResponseEntity.ok(Map.of("message", "Uploaded files successfully: " + message.toString()));
    }

    @GetMapping("/listfiles/{idDoc}")
    public ResponseEntity<?> listFilesForDocument(@PathVariable("idDoc") Long idDoc) {
        Document document = iDocument.retrieveById(idDoc);
        if (document == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found with ID: " + idDoc);
        }

        String prefix = document.getTitleDoc() + document.getIdDoc().toString();  // This forms the prefix like "zyrt13"
        Path dirPath = Paths.get(UPLOAD_DIR);  // Now looking at the root upload directory

        System.out.println("Looking in directory: " + dirPath.toAbsolutePath().toString()); // Debugging the path

        if (!Files.exists(dirPath) || !Files.isDirectory(dirPath)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Directory does not exist: " + dirPath.toAbsolutePath().toString());
        }

        try (Stream<Path> stream = Files.list(dirPath)) {
            List<String> fileNames = stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().startsWith(prefix)) // Filter files that start with the prefix
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());

            if (fileNames.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No files found with prefix: " + prefix);
            }

            return ResponseEntity.ok(fileNames);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to list files: " + e.getMessage());
        }
    }
}
