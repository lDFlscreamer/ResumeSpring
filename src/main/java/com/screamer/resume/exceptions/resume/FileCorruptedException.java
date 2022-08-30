package com.screamer.resume.exceptions.resume;

import org.springframework.web.multipart.MultipartFile;

public class FileCorruptedException extends Exception {
    private final MultipartFile corruptedFile;

    public FileCorruptedException(MultipartFile file, Throwable cause) {
        super("File corrupted", cause);
        this.corruptedFile = file;
    }

    public MultipartFile getCorruptedFile() {
        return corruptedFile;
    }
}
