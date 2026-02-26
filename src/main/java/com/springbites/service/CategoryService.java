package com.springbites.service;

import com.springbites.dto.CategoryRequest;
import com.springbites.dto.CategoryResponse;
import com.springbites.entity.Category;
import com.springbites.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest request) {

        Category category = new Category();
        category.setName(request.getName());

        Category saved = categoryRepository.save(category);

        return mapToResponse(saved);
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CategoryResponse mapToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }
}