package br.com.starter.application.api.badge;

import br.com.starter.application.api.badge.dto.BadgeRegistrationRequest;
import br.com.starter.application.api.badge.dto.UpdateBadgeDTO;
import br.com.starter.application.api.category.dto.CategoryRegistrationRequest;
import br.com.starter.application.api.category.dto.UpdateCategoryDTO;
import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.badge.*;
import br.com.starter.application.useCase.category.*;
import br.com.starter.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sensorama/api/badge")
@RequiredArgsConstructor

public class BadgeController {
    private final GetBadgeByIdUseCase getBadgeByIdUseCase;
    private final GetAllBadgeUseCase getAllBadgeUseCase;
    private final GetBadgeByCategoryIdUseCase getBadgeByCategoryIdUseCase;
    private final CreateBadgeUseCase createBadgeUseCase;
    private final UpdateBadgeUseCase updateBadgeUseCase;
    private final DeleteBadgeUseCase deleteBadgeUseCase;

    private final CountAllBadgesUseCase countAllBadgesUseCase;
    private final GetBadgesByDescriptionUseCase getBadgesByDescriptionUseCase;
    private final GetBadgesByNameUseCase getBadgesByNameUseCase;

    //index
    @GetMapping
    public ResponseEntity<?> getAllBadges(Pageable pageable) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllBadgeUseCase.execute(pageable));
        return ResponseEntity.ok(response);
    }

    //findByID
    @GetMapping("/{badgeId}")
    public ResponseEntity<?> findBadgeById(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                              @PathVariable("badgeId") UUID badgeId) {
        ResponseDTO<?> response = new ResponseDTO<>(getBadgeByIdUseCase.execute(badgeId));
        return ResponseEntity.ok(response);
    }

    //find by categoryId
    @GetMapping("/Category/{categoryId}")
    public ResponseEntity<?> getBadgeByCategoryId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                                  @PathVariable("categoryId") UUID categoryId) {
        ResponseDTO<?> response = new ResponseDTO<>(getBadgeByCategoryIdUseCase.execute(categoryId));
        return ResponseEntity.ok(response);
    }

    //create
    @PostMapping
    public ResponseEntity<?> register(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                      @RequestBody BadgeRegistrationRequest badge) {
        ResponseDTO<?> response = new ResponseDTO<>(createBadgeUseCase.execute(badge));
        return ResponseEntity.ok(response);
    }

    //update
    @PutMapping("/{badgeId}")
    public ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                    @PathVariable("badgeId") UUID badgeId, @RequestBody UpdateBadgeDTO request) {
        ResponseDTO<?> response = new ResponseDTO<>(updateBadgeUseCase.execute(badgeId, request));
        return ResponseEntity.ok(response);
    }

    //delete
    @DeleteMapping("/{badgeId}")
    public ResponseEntity<?> deleteBadge(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                             @PathVariable UUID badgeId) {
        ResponseDTO<?> response = new ResponseDTO<>(deleteBadgeUseCase.execute(badgeId));
        return ResponseEntity.ok(response);
    }

    //count
    @GetMapping("/count")
    public ResponseEntity<?> count(@AuthenticationPrincipal CustomUserDetails userAuthentication
                                          ) {
        ResponseDTO<?> response = new ResponseDTO<>(countAllBadgesUseCase.execute());
        return ResponseEntity.ok(response);
    }

    //findBy description
    @GetMapping("/Description")
    public ResponseEntity<?> findBadgeByDescription(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                           @RequestBody String description) {
        ResponseDTO<?> response = new ResponseDTO<>(getBadgesByDescriptionUseCase.execute(description));
        return ResponseEntity.ok(response);
    }

    //findByID
    @GetMapping("/Name")
    public ResponseEntity<?> findBadgeByName(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                           @RequestBody String name) {
        ResponseDTO<?> response = new ResponseDTO<>(getBadgesByNameUseCase.execute(name));
        return ResponseEntity.ok(response);
    }
}
