package com.adidas.shopshoes.mapper;

import com.adidas.shopshoes.dto.CustomerTypeDTO;
import com.adidas.shopshoes.model.CustomerType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerTypeMapper {

    CustomerTypeMapper INSTANCE = Mappers.getMapper(CustomerTypeMapper.class);

    CustomerType toCustomerType(CustomerTypeDTO customerTypeDTO);

    CustomerTypeDTO toCustomerTypeDTO(CustomerType customerType);

    List<CustomerType> toCustomerTypes(List<CustomerTypeDTO> customerTypeDTOS);

    List<CustomerTypeDTO> toCustomerTypeDTOs(List<CustomerType> customerTypeList);
}
