package com.ssafy.home.auth.service.signup;

import com.ssafy.home.auth.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload.dir}")
    private String uploadPath;

    @Value("${upload.access.url}")
    private String uploadAccessUrl;

    public String uploadProfileImage(MultipartFile profileImage) {
        String uploadDirPath = new File(uploadPath).getAbsolutePath();
        String fileName = UUID.randomUUID() + "_" + profileImage.getOriginalFilename(); // 고유한 파일 이름 생성

        File directory = new File(uploadDirPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File saveFile = new File(uploadDirPath + File.separator + fileName); // 파일이 저장되는 곳
        try {
            profileImage.transferTo(saveFile);
        } catch (IOException e) {
            throw new FileUploadException("파일 업로드 실패: " + profileImage.getOriginalFilename(), e);
        }

        return uploadAccessUrl + fileName; // 클라이언트가 접속할 수 있는 URL
    }

    public String getImageUrl(MultipartFile profileImage) {
        if (profileImage == null || !profileImage.isEmpty()) {
            return null;
        }
        return uploadProfileImage(profileImage);
    }
}
