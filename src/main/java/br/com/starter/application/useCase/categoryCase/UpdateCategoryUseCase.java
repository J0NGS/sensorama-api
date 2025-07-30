package br.com.starter.application.useCase.categoryCase;

import br.com.starter.application.api.category.dto.UpdateCategoryDTO;
import br.com.starter.application.api.user.dto.UpdateUserDTO;
import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import br.com.starter.domain.user.User;

import java.util.UUID;

public class UpdateCategoryUseCase {
    private final CategoryService categoryService;

    public Category execute(UUID categoryId, UpdateCategoryDTO updateCategoryDTO) {
        return categoryService.updateCategory(categoryId, updateCategoryDTO);
    }
}
