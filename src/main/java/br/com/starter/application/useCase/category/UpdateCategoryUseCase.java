package br.com.starter.application.useCase.category;

import br.com.starter.application.api.category.dto.UpdateCategoryDTO;
import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateCategoryUseCase {
    private final CategoryService categoryService;

    public Category execute(UUID categoryId, UpdateCategoryDTO updateCategoryDTO) {
        return categoryService.updateCategory(categoryId, updateCategoryDTO);
    }
}
