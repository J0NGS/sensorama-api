package br.com.starter.application.api.question;


import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.question.*;
import br.com.starter.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import br.com.starter.domain.question.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sensorama/api/question")
@RequiredArgsConstructor
public class QuestionController {
    private final GetQuestionByCategoryIdUseCase getByCategoryIdUseCase;
    private final GetAllQuestionUseCase getAllQuestionUseCase;
    private final GetQuestionByMediaType getByMediaTypeUseCase;
    private final GetQuestionByTitleUseCase getQuestionByTitleUseCase;
    private final GetQuestionByIdUseCase getQuestionByIdUseCase;

    //index
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllQuestionUseCase.execute(pageable));
        return ResponseEntity.ok(response);
    }

    //findBy
    @GetMapping("/{questionId}")
    public ResponseEntity<?> findCategoryById(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("questionId") UUID questionId) {
        ResponseDTO<?> response = new ResponseDTO<>(getQuestionByIdUseCase.execute(questionId));
        return ResponseEntity.ok(response);
    }

    //find by media
    @GetMapping("/Media")
    public ResponseEntity<?> findQuestionById(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @RequestBody MediaType media) {
        ResponseDTO<?> response = new ResponseDTO<>(getByMediaTypeUseCase.execute(media));
        return ResponseEntity.ok(response);
    }

    //find by CategoryId
    @GetMapping("/Category/{categoryId}")
    public ResponseEntity<?> findQuestionByCategoryId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("categoryId") UUID categoryId) {
        ResponseDTO<?> response = new ResponseDTO<>(getByCategoryIdUseCase.execute(categoryId));
        return ResponseEntity.ok(response);
    }

    //find By title
    @GetMapping("/Title")
    public ResponseEntity<?> findQuestionByTitle(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @RequestBody String title) {
        ResponseDTO<?> response = new ResponseDTO<>(getQuestionByTitleUseCase.execute(title));
        return ResponseEntity.ok(response);
    }
}
