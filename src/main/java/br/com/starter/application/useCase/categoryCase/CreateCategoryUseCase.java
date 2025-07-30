package br.com.starter.application.useCase.categoryCase;


import br.com.starter.application.api.category.dto.CategoryRegistrationRequest;
import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCategoryUseCase {
    private final CategoryService categoryService;

    public Category execute(CategoryRegistrationRequest request) {


            Category category = new Category();
            category.setName(request.name());
            category.setDescription(request.description());
            category.setIconUrl(request.iconUrl());

            return categoryService.createCategory(category);

    }
}
