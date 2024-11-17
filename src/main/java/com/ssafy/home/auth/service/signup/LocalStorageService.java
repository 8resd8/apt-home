package com.ssafy.home.auth.service.signup;

import com.ssafy.home.auth.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LocalStorageService implements StorageService {

    // 허용할 파일 확장자
    private static final Set<String> ALLOWED_EXTENSIONS = Stream.of("jpg", "jpeg", "png")
            .collect(Collectors.toSet());

    // 허용할 MIME 타입
    private static final Set<String> ALLOWED_MIME_TYPES = Set.of("image/jpeg", "image/png", "image/webp");

    @Value("${file.upload.dir}")
    private String uploadPath;

    @Value("${upload.access.url}")
    private String uploadAccessUrl;


    @Override
    public String uploadFile(MultipartFile profileImage) {
        if (profileImage == null || profileImage.isEmpty()) { // 파일이 빈 것은 예외상황이 아님
            return null;
        }
        validateFile(profileImage);

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

    void validateFile(MultipartFile file) {
        int extension = file.getOriginalFilename().lastIndexOf('.');
        if (extension == -1) {
            throw new FileUploadException("파일 확장자가 없습니다.");
        }

        String mimeType = file.getContentType();
        if (mimeType == null || !ALLOWED_MIME_TYPES.contains(mimeType.toLowerCase())) {
            throw new FileUploadException("ContentType 확인하세요");
        }

        String fileExtension = file.getOriginalFilename().substring(extension + 1).toLowerCase(); // 확장자만 확인
        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            throw new FileUploadException("허용되지 않는 파일 형식입니다. (허용: " + ALLOWED_EXTENSIONS + ")");
        }
    }
}
