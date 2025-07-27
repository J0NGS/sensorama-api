package br.com.starter.domain.Options;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OptionsRepository extends JpaRepository<Options, UUID> {

    @Query("SELECT o FROM Options o")
    List<Options> findAllList();

    @Query("SELECT o FROM Options o")
    Page<Options> findAllPageable(Pageable pageable);

    @Query("SELECT o FROM Options o WHERE o.question.id = :questionId")
    List<Options> findByQuestionId(UUID questionId);

    @Query("SELECT o FROM Options o WHERE o.isCorrect = :isCorrect")
    List<Options> findByIsCorrect(boolean isCorrect);

    @Query("SELECT o FROM Options o WHERE o.question.id = :questionId AND o.isCorrect = true")
    List<Options> findCorrectOptionsByQuestionId(UUID questionId);

    @Query("SELECT o FROM Options o WHERE LOWER(o.text) LIKE LOWER(CONCAT('%', :text, '%'))")
    List<Options> findByTextContainingIgnoreCase(String text);
}
