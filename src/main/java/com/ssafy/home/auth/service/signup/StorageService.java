package com.ssafy.home.auth.service.signup;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {
    List<String> uploadFiles(MultipartFile[] files);

    String uploadFile(MultipartFile file);
}
