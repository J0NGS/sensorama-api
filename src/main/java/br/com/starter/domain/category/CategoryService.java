package br.com.starter.domain.category;

import br.com.starter.application.api.category.dto.UpdateCategoryDTO;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        if (!categoryRepository.existsById(category.getId())) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(UUID categoryId, UpdateCategoryDTO updateCategoryDTO) {
        Category category = findCategoryById(categoryId);

        if (updateCategoryDTO.getName() != null) {
            category.setName(updateCategoryDTO.getName());
        }
        if (updateCategoryDTO.getDescription() != null) {
            category.setDescription(updateCategoryDTO.getDescription());
        }
        if (updateCategoryDTO.getIconUrl() != null) {
            category.setIconUrl(updateCategoryDTO.getIconUrl());
        }

        return categoryRepository.save(category);
    }

    public Category findCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }

    public List<Category> findCategoriesByNameContainingIgnoreCase(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    public Page<Category> getAllCategorys(Pageable pageable) {
        Page<Category> categorys = categoryRepository.findAll(pageable);
        return categorys;
    }

    public boolean deleteCategoryById(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
        } try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new FrontDisplayableException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar categoria: " + e.getMessage());
        }
    }
}
