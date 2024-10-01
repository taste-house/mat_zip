package com.matzip.matzipback.common.util;

import com.matzip.matzipback.exception.RestApiException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static com.matzip.matzipback.exception.ErrorCode.INTERNAL_SERVER_ERROR;

public class FileUploadUtils {

    public static String saveFile(String uploadDir, MultipartFile multipartFile) {

        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path uploadPath = Paths.get(uploadDir);

            // 경로 없을 시 경로 생성
            if(!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String replaceFileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());

            Path filePath = uploadPath.resolve(replaceFileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            return replaceFileName;
        } catch (IOException e) {
            throw new RestApiException(INTERNAL_SERVER_ERROR);
        }
    }
}
