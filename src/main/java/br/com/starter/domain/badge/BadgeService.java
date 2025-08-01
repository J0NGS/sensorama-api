package br.com.starter.domain.badge;

import br.com.starter.application.api.badge.dto.UpdateBadgeDTO;
import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
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
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final CategoryService categoryService;

    public Badge createBadge(Badge badge) {
        if (badge.getCategory() != null && badge.getCategory().getId() != null) {
            Category category = categoryService.findCategoryById(badge.getCategory().getId());
            badge.setCategory(category);
        }
        return badgeRepository.save(badge);
    }

    public Badge updateBadge(Badge badge) {
        if (!badgeRepository.existsById(badge.getId())) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Badge não encontrado");
        }
        return badgeRepository.save(badge);
    }

    public Badge updateBadge(UUID badgeId, UpdateBadgeDTO updateBadgeDTO) {
        Badge badge = findBadgeById(badgeId);

        if (updateBadgeDTO.getName() != null) {
            badge.setName(updateBadgeDTO.getName());
        }
        if (updateBadgeDTO.getDescription() != null) {
            badge.setDescription(updateBadgeDTO.getDescription());
        }
        if (updateBadgeDTO.getImageUrl() != null) {
            badge.setImageUrl(updateBadgeDTO.getImageUrl());
        }
        if (updateBadgeDTO.getCategoryId() != null) {
            Category category = categoryService.findCategoryById(updateBadgeDTO.getCategoryId());
            badge.setCategory(category);
        }

        return badgeRepository.save(badge);
    }

    public Badge findBadgeById(UUID id) {
        return badgeRepository.findById(id)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Badge não encontrado"));
    }

    public List<Badge> findBadgesByNameContainingIgnoreCase(String name) {
        return badgeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Badge> findBadgesByDescriptionContainingIgnoreCase(String description) {
        return badgeRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public List<Badge> findBadgesByCategoryId(UUID categoryId) {
        return badgeRepository.findByCategoryId(categoryId);
    }

    public List<Badge> findBadgesByCategoryIdOrderByName(UUID categoryId) {
        return badgeRepository.findByCategoryIdOrderByName(categoryId);
    }

    public Long countBadgesByCategoryId(UUID categoryId) {
        return badgeRepository.countByCategoryId(categoryId);
    }

    public Page<Badge> getAllBadges(Pageable pageable) {
        return badgeRepository.findAllPageable(pageable);
    }

    public List<Badge> getAllBadgesList() {
        return badgeRepository.findAllList();
    }

    public List<Badge> getAllBadgesOrderByName() {
        return badgeRepository.findAllOrderByNameAsc();
    }

    public void deleteBadgeById(UUID id) {
        if (!badgeRepository.existsById(id)) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Badge não encontrado");
        }
        badgeRepository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return badgeRepository.existsById(id);
    }

    public long countAllBadges() {
        return badgeRepository.count();
    }
}
