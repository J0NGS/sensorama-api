package br.com.starter.domain.badge;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, UUID> {
    @Query("SELECT b FROM Badge b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Badge> findByNameContainingIgnoreCase(String name);

    @Query("SELECT b FROM Badge b")
    Page<Badge> findAllPageable(Pageable pageable);

    @Query("SELECT b FROM Badge b")
    List<Badge> findAllList();

    @Query("SELECT b FROM Badge b WHERE LOWER(b.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Badge> findByDescriptionContainingIgnoreCase(String description);

    @Query("SELECT b FROM Badge b WHERE b.category.id = :categoryId")
    List<Badge> findByCategoryId(UUID categoryId);

    @Query("SELECT b FROM Badge b WHERE b.category.id = :categoryId ORDER BY b.name ASC")
    List<Badge> findByCategoryIdOrderByName(UUID categoryId);

    @Query("SELECT COUNT(b) FROM Badge b WHERE b.category.id = :categoryId")
    Long countByCategoryId(UUID categoryId);

    @Query("SELECT b FROM Badge b ORDER BY b.name ASC")
    List<Badge> findAllOrderByNameAsc();
}
