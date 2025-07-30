package br.com.starter.application.useCase.category;

import br.com.starter.domain.category.CategoryService;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class DeleteCategoryUseCase {
    private final CategoryService categoryService;

    public boolean execute(UUID categoryId) {
        if (categoryService.deleteCategoryById(categoryId)) {
            return true;
        }
        throw new FrontDisplayableException(HttpStatus.NOT_MODIFIED, "Não foi possível deletá-lo.");
    }
}
