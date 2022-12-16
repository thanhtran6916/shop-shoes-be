package com.adidas.shopshoes.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String copyFile(MultipartFile file, String folderName);

    byte[] readFileImage(String folder, String id);
}
