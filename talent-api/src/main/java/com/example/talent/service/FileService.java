package com.example.talent.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-19
 * @version: 1.0
 */

public interface FileService {

    File multipartFileToFile(MultipartFile file);

    void inputStreamToFile(InputStream ins, File file);

    void deleteTempFile(File file);

    String uploadFile(File file, String objectKey, String urlPrefix);
}
