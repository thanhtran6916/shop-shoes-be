package com.adidas.shopshoes.service;

import com.adidas.shopshoes.dto.CategoryDTO;
import com.adidas.shopshoes.dto.CustomerTypeDTO;
import com.adidas.shopshoes.mapper.CategoryMapper;
import com.adidas.shopshoes.model.Category;
import com.adidas.shopshoes.model.CustomerType;
import com.adidas.shopshoes.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toCategory(categoryDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toCategoryDTO(category);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        if (Objects.isNull(categoryDTO.getId())) return null;
        Optional<Category> customerTypeOptional = categoryRepository.findById(categoryDTO.getId());
        if (customerTypeOptional.isEmpty()) return null;
        Category category = categoryRepository.save(categoryMapper.toCategory(categoryDTO));
        return categoryMapper.toCategoryDTO(category);
    }

    @Override
    public CategoryDTO findById(String id) {
        if (Objects.isNull(id)) return null;
        Optional<Category> customerTypeOptional = categoryRepository.findById(id);
        return customerTypeOptional.map(categoryMapper::toCategoryDTO).orElse(null);
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> customerTypeList = categoryRepository.findAll();
        return categoryMapper.toCategoryDTOs(customerTypeList);
    }
}
