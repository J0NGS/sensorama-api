package br.com.starter.application.useCase.categoryCase;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class GetCategoryByNameUseCase {
    private final CategoryService categoryService;

    public List<Category> execute(String categoryName) {
        return categoryService.findCategoriesByNameContainingIgnoreCase(categoryName);
    }
}
