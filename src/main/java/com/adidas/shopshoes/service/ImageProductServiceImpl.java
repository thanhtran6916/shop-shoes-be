package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.BaseResponse;
import com.adidas.shopshoes.dto.ImageProductDTO;
import com.adidas.shopshoes.exception.CustomCodeException;
import com.adidas.shopshoes.mapper.ImageProductMapper;
import com.adidas.shopshoes.model.ImageProduct;
import com.adidas.shopshoes.repository.ImageProductRepository;
import com.adidas.shopshoes.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageProductServiceImpl implements ImageProductService {

    private final ImageProductRepository imageProductRepository;
    private final FileService fileService;

    private final ImageProductMapper imageProductMapper;

    @Override
    public ImageProductDTO save(ImageProductDTO imageProductDTO) {
        ImageProduct imageProduct = imageProductMapper.toImageProduct(imageProductDTO);
        imageProduct = imageProductRepository.save(imageProduct);
        return imageProductMapper.toImageProductDTO(imageProduct);
    }

    @Override
    public ImageProductDTO update(ImageProductDTO imageProductDTO) {
        if (Objects.isNull(imageProductDTO.getId())) return null;
        Optional<ImageProduct> imageProductOptional = imageProductRepository.findById(imageProductDTO.getId());
        if (imageProductOptional.isEmpty()) return null;
        ImageProduct imageProduct = imageProductMapper.toImageProduct(imageProductDTO);
        imageProduct = imageProductRepository.save(imageProduct);
        return imageProductMapper.toImageProductDTO(imageProduct);
    }

    @Override
    public ImageProductDTO findById(String id) {
        if (Objects.isNull(id)) return null;
        Optional<ImageProduct> imageProductOptional = imageProductRepository.findById(id);
        return imageProductOptional.map(imageProductMapper::toImageProductDTO).orElse(null);
    }

    @Override
    public List<ImageProductDTO> findAll() {
        List<ImageProduct> imageProducts = imageProductRepository.findAll();
        return imageProductMapper.toImageProductDTOList(imageProducts);
    }

    @Override
    public List<ImageProductDTO> insertAndCopyListFileImageProduct(List<MultipartFile> files) {
        try {
            String folderName = Constant.file.IMAGE_PRODUCT_FOLDER_NAME;
            List<ImageProduct> imageProductList = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileName = fileService.copyFile(file, folderName);
                ImageProduct imageProductDTO = ImageProduct.builder()
                        .url(fileName)
                        .build();
                imageProductList.add(imageProductDTO);

            }
            imageProductList = imageProductRepository.saveAll(imageProductList);
            return imageProductMapper.toImageProductDTOList(imageProductList);
        } catch (Exception e) {
            log.info("SAVE AND COPY LIST FILE IMAGE PRODUCT ERROR ===>>> " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
