package br.com.starter.application.useCase.category;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class GetCategoryByIdUseCase {
    private final CategoryService categoryService;

    public Category execute(UUID categoryId) {
        return categoryService.findCategoryById(categoryId);
    }
}
