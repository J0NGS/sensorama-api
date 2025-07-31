package br.com.starter.application.api.options;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.api.options.dto.CreateOptionsDTO;
import br.com.starter.application.useCase.options.*;
import br.com.starter.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sensorama/api/options")
@RequiredArgsConstructor
public class OptionsController {
    private final CreateOptionsUseCase createOptionsUseCase;
    private final GetAllOptionsUseCase getAllOptionsUseCase;
    private final GetOptionsByIdUseCase getOptionsByIdUseCase;
    private final GetOptionsByQuestionIdUseCase getOptionsByQuestionIdUseCase;
    private final GetCorrectOptionsByQuestionIdUseCase getCorrectOptionsByQuestionIdUseCase;
    private final GetOptionsByTextUseCase getOptionsByTextUseCase;
    private final GetOptionsByIsCorrectUseCase getOptionsByIsCorrectUseCase;

    //create
    @PostMapping
    public ResponseEntity<?> createOptions(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                         @RequestBody CreateOptionsDTO createOptionsDTO) {
        ResponseDTO<?> response = new ResponseDTO<>(createOptionsUseCase.execute(createOptionsDTO));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{optionId}")
    public ResponseEntity<?> findOptionById(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                          @PathVariable("optionId") UUID optionId) {
        ResponseDTO<?> response = new ResponseDTO<>(getOptionsByIdUseCase.execute(optionId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/Question/{questionId}")
    public ResponseEntity<?> findOptionsByQuestionId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                                   @PathVariable("questionId") UUID questionId) {
        ResponseDTO<?> response = new ResponseDTO<>(getOptionsByQuestionIdUseCase.execute(questionId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/Question/{questionId}/correct")
    public ResponseEntity<?> findCorrectOptionsByQuestionId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                                          @PathVariable("questionId") UUID questionId) {
        ResponseDTO<?> response = new ResponseDTO<>(getCorrectOptionsByQuestionIdUseCase.execute(questionId));
        return ResponseEntity.ok(response);
    }
}
