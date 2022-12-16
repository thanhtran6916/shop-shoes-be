package com.adidas.shopshoes.service;

import com.adidas.shopshoes.model.ImageProduct;
import com.adidas.shopshoes.repository.ImageProductRepository;
import com.adidas.shopshoes.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ImageProductRepository imageProductRepository;

    @Override
    public String copyFile(MultipartFile file, String folderName) {
        try {
            byte[] bytes;
            String filename = UUID.randomUUID() + file.getOriginalFilename();
            String pathFile = Constant.file.ROOT_FOLDER_NAME + folderName + "\\" + filename;
            bytes = file.getBytes();
            try (FileOutputStream fileOutputStream = new FileOutputStream(pathFile)) {
                fileOutputStream.write(bytes);
            }
            return filename;
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    @Override
    public byte[] readFileImage(String folder, String id) {
        Optional<ImageProduct> imageProductOptional = imageProductRepository.findById(id);
        if (imageProductOptional.isEmpty()) return null;
        String pathFile = Constant.file.ROOT_FOLDER_NAME + folder + "\\" + imageProductOptional.get().getUrl();
        try {
            byte[] bytes;
            try (FileInputStream fileInputStream = new FileInputStream(pathFile)) {
                bytes = fileInputStream.readAllBytes();
            }
            return bytes;
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }


}
