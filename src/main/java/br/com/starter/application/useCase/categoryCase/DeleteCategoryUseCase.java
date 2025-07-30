package br.com.starter.application.useCase.categoryCase;

import br.com.starter.application.api.category.dto.UpdateCategoryDTO;
import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class DeleteCategoryUseCase {
    private final CategoryService categoryService;

    public boolean execute(UUID userId) {
        if (categoryService.deleteCategoryById(userId)) {
            return true;
        }
        throw new FrontDisplayableException(HttpStatus.NOT_MODIFIED, "Não foi possível deletá-lo.");
    }
}
