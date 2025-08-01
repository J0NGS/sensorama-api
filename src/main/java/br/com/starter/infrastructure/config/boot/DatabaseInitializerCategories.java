package br.com.starter.infrastructure.config.boot;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryRepository;
import br.com.starter.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DatabaseInitializerCategories {
    
    @Bean
    @Order(2)
    public CommandLineRunner initializeCategories(CategoryRepository categoryRepository, CategoryService categoryService) {
        return args -> {
            List<CategoryData> defaultCategories = List.of(
                new CategoryData("Qual o objeto?", "Ouça o som e identifique qual objeto o produziu.", "🔊"),
                new CategoryData("Pixelado", "Descubra a imagem com poucos pixels visíveis.", "🔍"),
                new CategoryData("Mapa Mental", "Identifique o ambiente apenas pelo som ambiente.", "🧠"),
                new CategoryData("Zoom Enigma", "Veja uma imagem com zoom ampliado e adivinhe o que é.", "🔎"),
                new CategoryData("Eco Lógico", "Ouça 3 sons em sequência e deduza a ideia representada.", "🎵")
            );

            for (CategoryData categoryData : defaultCategories) {
                if (!categoryRepository.existsByName(categoryData.name())) {
                    Category category = new Category();
                    category.setName(categoryData.name());
                    category.setDescription(categoryData.description());
                    category.setIconUrl(categoryData.iconUrl());
                    
                    categoryService.createCategory(category);
                }
            }
        };
    }
    
    private record CategoryData(String name, String description, String iconUrl) {}
}
