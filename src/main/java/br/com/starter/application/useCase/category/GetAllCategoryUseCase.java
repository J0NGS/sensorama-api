package br.com.starter.application.useCase.category;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor

public class GetAllCategoryUseCase {
    private final CategoryService categoryService;
    public Page<Category> execute(Pageable pageable) {
        return categoryService.getAllCategorys(pageable);
    }
}
