package vn.edu.iuh.fit.myblog.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.myblog.entity.Category;
import vn.edu.iuh.fit.myblog.exception.ResourceNotFoundException;
import vn.edu.iuh.fit.myblog.payload.CategoryDto;
import vn.edu.iuh.fit.myblog.repository.CategoryRepository;
import vn.edu.iuh.fit.myblog.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category newCategory = categoryRepository.save(category);
        return modelMapper.map(newCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map((element) -> modelMapper.map(element, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        Category newCategory = categoryRepository.save(category);
        return modelMapper.map(newCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.delete(category);
    }
}
