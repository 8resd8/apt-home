package com.ssafy.home.auth.service.signup;

import com.ssafy.home.auth.exception.FileUploadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class FileStorageServiceTest {

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private FileStorageService fileStorageService;

    private final String uploadAccessUrl = "http://localhost:8080/upload/profile_images/";

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        try {
            java.lang.reflect.Field uploadPathField = FileStorageService.class.getDeclaredField("uploadPath");
            uploadPathField.setAccessible(true);
            uploadPathField.set(fileStorageService, tempDir.toString());

            java.lang.reflect.Field uploadAccessUrlField = FileStorageService.class.getDeclaredField("uploadAccessUrl");
            uploadAccessUrlField.setAccessible(true);
            uploadAccessUrlField.set(fileStorageService, uploadAccessUrl);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set up FileStorageService", e);
        }
    }

    @Test
    @DisplayName("프로필 이미지 업로드 성공 (메모 포함)")
    void uploadProfileImage_Success_WithMemo() throws IOException {
        // Arrange
        when(multipartFile.getOriginalFilename()).thenReturn("profile.png");
        doNothing().when(multipartFile).transferTo(any(File.class));

        // Act
        String imageUrl = fileStorageService.uploadProfileImage(multipartFile);

        // Assert
        assertNotNull(imageUrl);
        assertTrue(imageUrl.startsWith(uploadAccessUrl));
        verify(multipartFile, times(1)).transferTo(any(File.class));
    }

    @Test
    @DisplayName("프로필 이미지 업로드 실패 (IOException)")
    void uploadProfileImage_Failure_IOException() throws IOException {
        // Arrange
        when(multipartFile.getOriginalFilename()).thenReturn("profile.png");
        doThrow(new IOException("IO Error")).when(multipartFile).transferTo(any(File.class));

        // Act & Assert
        FileUploadException exception = assertThrows(FileUploadException.class, () ->
                fileStorageService.uploadProfileImage(multipartFile)
        );

        assertTrue(exception.getMessage().contains("파일 업로드 실패"));
        verify(multipartFile, times(1)).transferTo(any(File.class));
    }

    @Test
    @DisplayName("getImageUrl 메서드 성공 (메모 포함)")
    void getImageUrl_Success_WithMemo() throws IOException {
        // Arrange
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("profile.png");
        doNothing().when(multipartFile).transferTo(any(File.class));

        // Act
        String imageUrl = fileStorageService.getImageUrl(multipartFile);

        // Assert
        assertNotNull(imageUrl);
        assertTrue(imageUrl.startsWith(uploadAccessUrl));
        verify(multipartFile, times(1)).transferTo(any(File.class));
    }

    @Test
    @DisplayName("getImageUrl 메서드 실패 (파일 비어있음)")
    void getImageUrl_Failure_FileEmpty() throws IOException {
        // Arrange
        when(multipartFile.isEmpty()).thenReturn(true);

        // Act
        String imageUrl = fileStorageService.getImageUrl(multipartFile);

        // Assert
        assertNull(imageUrl);
        verify(multipartFile, never()).transferTo(any(File.class));
    }

    @Test
    @DisplayName("getImageUrl 메서드 실패 (파일이 null)")
    void getImageUrl_Failure_FileNull() {
        // Act
        String imageUrl = fileStorageService.getImageUrl(null);

        // Assert
        assertNull(imageUrl);
    }

    @Test
    @DisplayName("업로드된 파일 삭제 확인")
    void uploadProfileImage_FileDeletionAfterTest() throws IOException {
        // Arrange
        when(multipartFile.getOriginalFilename()).thenReturn("profile.png");
        doAnswer(invocation -> {
            File file = invocation.getArgument(0);
            file.createNewFile(); // Simulate file creation
            return null;
        }).when(multipartFile).transferTo(any(File.class));

        // Act
        String imageUrl = fileStorageService.uploadProfileImage(multipartFile);
        Path uploadedFilePath = tempDir.resolve(imageUrl.substring(uploadAccessUrl.length()));

        // Assert
        assertTrue(uploadedFilePath.toFile().exists());

    }
}
