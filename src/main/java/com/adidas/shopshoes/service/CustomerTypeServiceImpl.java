package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.CustomerTypeDTO;
import com.adidas.shopshoes.mapper.CustomerTypeMapper;
import com.adidas.shopshoes.model.CustomerType;
import com.adidas.shopshoes.repository.CustomerTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerTypeServiceImpl implements CustomerTypeService {

    private final CustomerTypeRepository customerTypeRepository;
    private final CustomerTypeMapper customerTypeMapper;

    @Override
    public CustomerTypeDTO save(CustomerTypeDTO customerTypeDTO) {
        CustomerType customerType = customerTypeMapper.toCustomerType(customerTypeDTO);
        customerType = customerTypeRepository.save(customerType);
        return customerTypeMapper.toCustomerTypeDTO(customerType);
    }

    @Override
    public CustomerTypeDTO update(CustomerTypeDTO customerTypeDTO) {
        if (Objects.isNull(customerTypeDTO.getId())) return null;
        Optional<CustomerType> customerTypeOptional = customerTypeRepository.findById(customerTypeDTO.getId());
        if (customerTypeOptional.isEmpty()) return null;
        CustomerType customerType = customerTypeRepository.save(customerTypeMapper.toCustomerType(customerTypeDTO));
        return customerTypeMapper.toCustomerTypeDTO(customerType);
    }

    @Override
    public CustomerTypeDTO findById(String id) {
        if (Objects.isNull(id)) return null;
        Optional<CustomerType> customerTypeOptional = customerTypeRepository.findById(id);
        return customerTypeOptional.map(customerTypeMapper::toCustomerTypeDTO).orElse(null);
    }

    @Override
    public List<CustomerTypeDTO> findAll() {
        List<CustomerType> customerTypeList = customerTypeRepository.findAll();
        return customerTypeMapper.toCustomerTypeDTOs(customerTypeList);
    }
}
