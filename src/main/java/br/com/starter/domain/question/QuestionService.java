package br.com.starter.domain.question;

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
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question findById(UUID id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Questão não encontrada"));
    }

    public List<Question> findByTitleContainingIgnoreCase(String title) {
        return questionRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Question> findByCategoryId(UUID categoryId) {
        return questionRepository.findByCategoryId(categoryId);
    }

    public List<Question> findByMediaType(MediaType mediaType) {
        return questionRepository.findByMediaType(mediaType);
    }

    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionRepository.findAllPageable(pageable);
    }

    public List<Question> getAllQuestionsList() {
        return questionRepository.findAllList();
    }
}
