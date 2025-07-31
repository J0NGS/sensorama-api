package br.com.starter.application.api.options;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.options.GetAllOptionsUseCase;
import br.com.starter.application.useCase.options.GetOptionByIdUseCase;
import br.com.starter.application.useCase.options.GetOptionContainTextUseCase;
import br.com.starter.application.useCase.options.GetOptionCorrectByQuestionIdUseCase;
import br.com.starter.domain.game.Mode;
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
    private final GetAllOptionsUseCase getAllOptionsUseCase;
    private final GetOptionByIdUseCase getOptionByIdUseCase;
    private final GetOptionCorrectByQuestionIdUseCase getOptionCorrectByQuestionIdUseCase;
    private final GetOptionContainTextUseCase getOptionContainTextUseCase;


    //index
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllOptionsUseCase.execute(pageable));
        return ResponseEntity.ok(response);
    }

    //findBy
    @GetMapping("/{optionId}")
    public ResponseEntity<?> findById(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("optionId") UUID optionId) {
        ResponseDTO<?> response = new ResponseDTO<>(getOptionByIdUseCase.execute(optionId));
        return ResponseEntity.ok(response);
    }

    //findBy categoryId
    @GetMapping("/Category/{categoryId}")
    public ResponseEntity<?> findOptionByCategoryId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("categoryId") UUID categoryId) {
        ResponseDTO<?> response = new ResponseDTO<>(getOptionCorrectByQuestionIdUseCase.execute(categoryId));
        return ResponseEntity.ok(response);
    }

    //find By Text
    @GetMapping("/Text")
    public ResponseEntity<?> findOptionByText(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                            @RequestBody String text) {
        ResponseDTO<?> response = new ResponseDTO<>(getOptionContainTextUseCase.execute(text));
        return ResponseEntity.ok(response);
    }
}
