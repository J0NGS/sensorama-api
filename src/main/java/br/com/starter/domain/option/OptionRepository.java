package br.com.starter.domain.option;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OptionRepository extends JpaRepository<Option, UUID> {

    @Query("SELECT o FROM Option o")
    List<Option> findAllList();

    @Query("SELECT o FROM Option o")
    Page<Option> findAllPageable(Pageable pageable);

    @Query("SELECT o FROM Option o WHERE o.question.id = :questionId")
    List<Option> findByQuestionId(UUID questionId);

    @Query("SELECT o FROM Option o WHERE o.isCorrect = :isCorrect")
    List<Option> findByIsCorrect(boolean isCorrect);

    @Query("SELECT o FROM Option o WHERE o.question.id = :questionId AND o.isCorrect = true")
    Option findCorrectOptionsByQuestionId(UUID questionId);

    @Query("SELECT o FROM Option o WHERE LOWER(o.text) LIKE LOWER(CONCAT('%', :text, '%'))")
    List<Option> findByTextContainingIgnoreCase(String text);
}
