package com.ssafy.home.auth.service.signup;

import com.ssafy.home.auth.exception.FileUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class S3StorageService implements StorageService {

    private static final Set<String> ALLOWED_EXTENSIONS = Stream.of("jpg", "jpeg", "png")
            .collect(Collectors.toSet());

    private static final Set<String> ALLOWED_MIME_TYPES = Set.of("image/jpeg", "image/png", "image/webp");

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${upload.access.url}") // AWS S3 접근 URL (예: https://[서버주소]/)
    private String uploadAccessUrl;

    @Override
    public String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        validateFile(file);

        String fileName = "upload/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .acl("public-read") // 퍼블릭 읽기 권한 설정 (필요에 따라 조정)
                .build();

        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        } catch (IOException e) {
            throw new FileUploadException("파일 업로드 실패: " + file.getOriginalFilename(), e);
        }

        return uploadAccessUrl + fileName; // 클라이언트가 접속할 수 있는 URL
    }

    @Override
    public List<String> uploadFiles(MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return List.of();
        }

        List<String> fileUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileUrl = uploadFile(file);  // 업로드
            fileUrls.add(fileUrl);
        }

        return fileUrls;
    }

    // 공통된 파일 유효성 검사 로직 (하나의 메서드로 통합)
    private void validateFile(MultipartFile file) {
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
