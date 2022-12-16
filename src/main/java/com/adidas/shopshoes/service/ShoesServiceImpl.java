package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.BaseResponse;
import com.adidas.shopshoes.dto.ImageProductDTO;
import com.adidas.shopshoes.dto.ShoesDTO;
import com.adidas.shopshoes.dto.request.ShoesRequest;
import com.adidas.shopshoes.exception.CustomCodeException;
import com.adidas.shopshoes.mapper.ShoesMapper;
 import com.adidas.shopshoes.model.Shoes;
import com.adidas.shopshoes.repository.ShoesRepository;
import com.adidas.shopshoes.util.Constant;
import com.adidas.shopshoes.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoesServiceImpl implements ShoesService {

    private final ShoesRepository shoesRepository;
    private final ImageProductService imageProductService;

    private final ShoesMapper shoesMapper;

    @Override
    public ShoesDTO save(ShoesDTO shoesDTO) {
        Shoes shoes = shoesMapper.toShoes(shoesDTO);
        shoes = shoesRepository.save(shoes);
        return shoesMapper.toShoesDTO(shoes);
    }

    @Override
    public ShoesDTO update(ShoesDTO shoesDTO) {
        if (Objects.isNull(shoesDTO.getId())) return null;
        Optional<Shoes> customerTypeOptional = shoesRepository.findById(shoesDTO.getId());
        if (customerTypeOptional.isEmpty()) return null;
        Shoes shoes = shoesRepository.save(shoesMapper.toShoes(shoesDTO));
        return shoesMapper.toShoesDTO(shoes);
    }

    @Override
    public ShoesDTO findById(String id) {
        if (Objects.isNull(id)) return null;
        Optional<Shoes> shoesOptional = shoesRepository.findById(id);
        return shoesOptional.map(shoesMapper::toShoesDTO).orElse(null);
    }

    @Override
    public List<ShoesDTO> findAll() {
        List<Shoes> shoesList = shoesRepository.findAll();
        return shoesMapper.toShoesDTOs(shoesList);
    }

    @Override
    public ShoesDTO saveAttachedPhoto(ShoesRequest shoesRequest) {
        validateSaveShoesRequest(shoesRequest);

        ShoesDTO shoesDTO = shoesMapper.shoesRequestToShoesDTO(shoesRequest);

        // insert and copy file to IMAGE_PRODUCT table
        List<MultipartFile> shoesImageFiles = shoesRequest.getImageProductFiles();
        List<ImageProductDTO> imageProductDTOS = imageProductService.insertAndCopyListFileImageProduct(shoesImageFiles);
        if (imageProductDTOS.isEmpty()) {
            throw new CustomCodeException(BaseResponse.builder()
                    .errorCode(Constant.ERROR_CODE)
                    .message(MessageUtil.getMessage("message.error.save.image.error"))
                    .build());
        }

        shoesDTO.setImageProduct(imageProductDTOS);

        shoesDTO = save(shoesDTO);
        return shoesDTO;
    }

    private void validateSaveShoesRequest(ShoesRequest shoesRequest) {
        if (Objects.isNull(shoesRequest)) {
            throw new CustomCodeException(BaseResponse.builder()
                    .errorCode(Constant.ERROR_CODE)
                    .message(MessageUtil.getMessage("message.error.product.not.empty"))
                    .build());
        }
        if (Objects.isNull(shoesRequest.getImageProductFiles())) {
            throw new CustomCodeException(BaseResponse.builder()
                    .errorCode(Constant.ERROR_CODE)
                    .message(MessageUtil.getMessage("message.error.shoes.image.not.empty"))
                    .build());
        }

    }
}
