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
import java.lang.reflect.Field;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class LocalStorageServiceTest {

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private LocalStorageService storageService;

    private final String uploadAccessUrl = "http://localhost:8080/upload/profile_images/";

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        try {
            Field uploadPathField = LocalStorageService.class.getDeclaredField("uploadPath");
            uploadPathField.setAccessible(true);
            uploadPathField.set(storageService, tempDir.toString());

            Field uploadAccessUrlField = LocalStorageService.class.getDeclaredField("uploadAccessUrl");
            uploadAccessUrlField.setAccessible(true);
            uploadAccessUrlField.set(storageService, uploadAccessUrl);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set up FileStorageService", e);
        }
    }

    @Test
    @DisplayName("프로필 이미지 업로드 성공")
    void uploadFile_Success() throws IOException {
        when(multipartFile.getOriginalFilename()).thenReturn("profile.png");
        when(multipartFile.getContentType()).thenReturn("image/png");
        doNothing().when(multipartFile).transferTo(any(File.class));

        String imageUrl = storageService.uploadFile(multipartFile);

        assertNotNull(imageUrl);
        assertTrue(imageUrl.startsWith(uploadAccessUrl));
        verify(multipartFile, times(1)).transferTo(any(File.class));
    }

    @Test
    @DisplayName("프로필 이미지 업로드 실패 (IOException)")
    void uploadFile_Failure_IOException() throws IOException {
        when(multipartFile.getOriginalFilename()).thenReturn("profile.png");
        when(multipartFile.getContentType()).thenReturn("image/png");
        doThrow(new IOException("IO Error")).when(multipartFile).transferTo(any(File.class));

        FileUploadException exception = assertThrows(FileUploadException.class, () ->
                storageService.uploadFile(multipartFile)
        );

        assertTrue(exception.getMessage().contains("파일 업로드 실패"));
        verify(multipartFile, times(1)).transferTo(any(File.class));
    }

    @Test
    @DisplayName("프로필 이미지 업로드 성공 (파일 존재 확인)")
    void uploadFile_Success_FileExists() throws IOException {
        when(multipartFile.getOriginalFilename()).thenReturn("profile.png");
        when(multipartFile.getContentType()).thenReturn("image/png");
        doAnswer(invocation -> {
            File file = invocation.getArgument(0);
            file.createNewFile();
            return null;
        }).when(multipartFile).transferTo(any(File.class));

        String imageUrl = storageService.uploadFile(multipartFile);
        Path uploadedFilePath = tempDir.resolve(imageUrl.substring(uploadAccessUrl.length()));

        assertNotNull(imageUrl);
        assertTrue(uploadedFilePath.toFile().exists());
        verify(multipartFile, times(1)).transferTo(any(File.class));
    }

    @Test
    @DisplayName("프로필 이미지 업로드 실패 (파일 비어있음)")
    void uploadFile_Failure_FileEmpty() throws IOException {
        when(multipartFile.isEmpty()).thenReturn(true);

        String imageUrl = storageService.uploadFile(multipartFile);

        assertNull(imageUrl);
        verify(multipartFile, never()).transferTo(any(File.class));
    }

    @Test
    @DisplayName("프로필 이미지 업로드 실패 (파일이 null)")
    void uploadFile_Failure_FileNull() {
        String imageUrl = storageService.uploadFile(null);

        assertNull(imageUrl);
    }

    @Test
    @DisplayName("프로필 이미지 업로드 후 파일 삭제 확인")
    void uploadFile_FileExistsAfterUpload() throws IOException {
        when(multipartFile.getOriginalFilename()).thenReturn("profile.png");
        when(multipartFile.getContentType()).thenReturn("image/png");
        doAnswer(invocation -> {
            File file = invocation.getArgument(0);
            file.createNewFile(); // Simulate file creation
            return null;
        }).when(multipartFile).transferTo(any(File.class));


        String imageUrl = storageService.uploadFile(multipartFile);
        Path uploadedFilePath = tempDir.resolve(imageUrl.substring(uploadAccessUrl.length()));


        assertNotNull(imageUrl);
        assertTrue(uploadedFilePath.toFile().exists());

        uploadedFilePath.toFile().delete();
        assertFalse(uploadedFilePath.toFile().exists());
    }
}
