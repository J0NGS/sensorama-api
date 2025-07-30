package br.com.starter.application.useCase.categoryCase;

import br.com.starter.application.api.user.dto.UpdateUserDTO;
import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import br.com.starter.domain.user.User;
import br.com.starter.domain.user.UserService;
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
