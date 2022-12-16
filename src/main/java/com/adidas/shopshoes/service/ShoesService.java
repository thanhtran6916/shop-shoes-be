package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.ShoesDTO;
import com.adidas.shopshoes.dto.request.ShoesRequest;

public interface ShoesService extends GeneralService<ShoesDTO> {

    ShoesDTO saveAttachedPhoto(ShoesRequest shoesRequest);
}
