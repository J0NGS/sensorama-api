package br.com.starter.domain.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    @Query("SELECT q FROM Question q")
    List<Question> findAllList();

    @Query("SELECT q FROM Question q")
    Page<Question> findAllPageable(Pageable pageable);

    @Query("SELECT q FROM Question q WHERE LOWER(q.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Question> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT q FROM Question q WHERE q.category.id = :categoryId")
    List<Question> findByCategoryId(UUID categoryId);

    @Query("SELECT q FROM Question q WHERE q.mediaType = :mediaType")
    List<Question> findByMediaType(MediaType mediaType);
}
