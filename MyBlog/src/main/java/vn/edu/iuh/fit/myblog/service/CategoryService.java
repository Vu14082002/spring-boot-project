package vn.edu.iuh.fit.myblog.service;

import vn.edu.iuh.fit.myblog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(Long categoryId,CategoryDto categoryDto);

    void deleteCategory(Long categoryId);
}
