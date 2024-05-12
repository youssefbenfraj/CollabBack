package tn.esprit.espritcollabbackend.services;

import jakarta.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootUploadImageApplication implements CommandLineRunner {

    @Resource
    FilesStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootUploadImageApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        storageService.init();
    }
}