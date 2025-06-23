package com.fenix.flash.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileUploadService {

    @Value("${file.upload.dir}")
    private String uploadDir;
    private Path location;

    @PostConstruct
    public void init() {
        location = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(location);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String store(MultipartFile file, String subDirectory) {
        if (file == null || file.isEmpty()) return null;

        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (filename.contains("..")) throw new RuntimeException("Sequência de caminho inválida");

            String fileExtension = "";
            int dotIndex = filename.lastIndexOf(".");
            if (dotIndex > 0) {
                fileExtension = filename.substring(dotIndex);
            }
            String uniqueFileName = UUID.randomUUID() + fileExtension;

            Path targetLocation = location;
            if (subDirectory != null && !subDirectory.isEmpty()) {
                targetLocation = targetLocation.resolve(subDirectory);
                Files.createDirectories(targetLocation);
            }

            targetLocation = targetLocation.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return subDirectory != null ? subDirectory + '/' + uniqueFileName : uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path getLocation() {
        return location;
    }
}
