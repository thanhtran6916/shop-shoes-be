package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.ImageProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageProductService extends GeneralService<ImageProductDTO> {

    List<ImageProductDTO> insertAndCopyListFileImageProduct(List<MultipartFile> files);
}
