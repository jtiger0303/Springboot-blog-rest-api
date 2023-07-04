package com.example.springbootblogrestapi.service.impl;

import com.example.springbootblogrestapi.entity.Category;
import com.example.springbootblogrestapi.exception.ResourceNotFoundException;
import com.example.springbootblogrestapi.payload.CategoryDto;
import com.example.springbootblogrestapi.repository.CategoryRepository;
import com.example.springbootblogrestapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper){
        this.categoryRepository=categoryRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category= modelMapper.map(categoryDto, Category.class); //model mapper to convert category data->category
        Category savedCategory=categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));

        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        //converted a list of category JP entity into a list
        List<Category> categories=categoryRepository.findAll();
        return categories.stream().map((category)->modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setId(categoryId);

        Category updatedCategory=categoryRepository.save(category);

        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
       Category category= categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
       categoryRepository.delete(category);
    }
}
