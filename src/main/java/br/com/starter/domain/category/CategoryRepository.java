package br.com.starter.domain.category;

import br.com.starter.domain.badge.Badge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Category> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c FROM Category c")
    Page<Badge> findAllPageable(Pageable pageable);

    @Query("SELECT c FROM Category c")
    List<Badge> findAllList();
}
