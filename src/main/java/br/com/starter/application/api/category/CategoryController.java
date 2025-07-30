package br.com.starter.application.api.category;

import br.com.starter.application.api.category.dto.CategoryRegistrationRequest;
import br.com.starter.application.api.category.dto.UpdateCategoryDTO;
import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.category.*;
import br.com.starter.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sensorama/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final GetAllCategoryUseCase getAllCategorysUseCase;
    private final GetCategoryByNameUseCase getCategoryByNameUseCase;
    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    //index
    @GetMapping
    public ResponseEntity<?> getAllCategorys(Pageable pageable) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllCategorysUseCase.execute(pageable));
        return ResponseEntity.ok(response);
    }

    //findBy
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> findCategoryById(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                             @PathVariable("categoryId") UUID categoryId) {
        ResponseDTO<?> response = new ResponseDTO<>(getCategoryByIdUseCase.execute(categoryId));
        return ResponseEntity.ok(response);
    }

    //findByName
    @GetMapping("/find_by_name")
    public ResponseEntity<?> getCategoryByName(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                                @RequestBody String category) {
        ResponseDTO<?> response = new ResponseDTO<>(getCategoryByNameUseCase.execute(category));
        return ResponseEntity.ok(response);
    }

    //create
    @PostMapping
    public ResponseEntity<?> register(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                      @RequestBody CategoryRegistrationRequest category) {
        ResponseDTO<?> response = new ResponseDTO<>(createCategoryUseCase.execute(category));
        return ResponseEntity.ok(response);
    }

    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                    @PathVariable("categoryId") UUID categoryId, @RequestBody UpdateCategoryDTO request) {
        ResponseDTO<?> response = new ResponseDTO<>(updateCategoryUseCase.execute(categoryId, request));
        return ResponseEntity.ok(response);
    }

    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                             @PathVariable UUID categoryId) {
        ResponseDTO<?> response = new ResponseDTO<>(deleteCategoryUseCase.execute(categoryId));
        return ResponseEntity.ok(response);
    }
}
