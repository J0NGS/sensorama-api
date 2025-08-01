package br.com.starter.application.api.userBadge;

import br.com.starter.application.api.common.ResponseDTO;
import br.com.starter.application.useCase.userBadge.GetAllUserBadgeUseCase;
import br.com.starter.application.useCase.userBadge.GetUserBadgeByProfileIdAndBadgeIdUseCase;
import br.com.starter.application.useCase.userBadge.GetUserBadgeByProfileIdUseCase;
import br.com.starter.application.useCase.userBadge.GetUserBadgebyBadgeIdUseCase;
import br.com.starter.domain.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/sensorama/api/userBadge")
@RequiredArgsConstructor
public class UserBadgeController {
    private final GetAllUserBadgeUseCase getAllUserBadgeUseCase;
    private final GetUserBadgebyBadgeIdUseCase getUserBadgebyBadgeIdUseCase;
    private final GetUserBadgeByProfileIdUseCase getUserBadgeByProfileIdUseCase;
    private final GetUserBadgeByProfileIdAndBadgeIdUseCase getUserBadgeByProfileIdAndBadgeIdUseCase;


    //index
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        ResponseDTO<?> response = new ResponseDTO<>(getAllUserBadgeUseCase.execute(pageable));
        return ResponseEntity.ok(response);
    }

    //findBy badgeId
    @GetMapping("/badge/{badgeId}")
    public ResponseEntity<?> findByBadgeId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                      @PathVariable("badgeId") UUID badgeId) {
        ResponseDTO<?> response = new ResponseDTO<>(getUserBadgebyBadgeIdUseCase.execute(badgeId));
        return ResponseEntity.ok(response);
    }


    //findBy profileId
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<?> findByProfileId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                           @PathVariable("profileId") UUID profileId) {
        ResponseDTO<?> response = new ResponseDTO<>(getUserBadgeByProfileIdUseCase.execute(profileId));
        return ResponseEntity.ok(response);
    }

    //findBy profileId and badgeId
    @GetMapping("/profile/{profileId}/badge/{badgeId}")
    public ResponseEntity<?> findByProfileIdAndBadgeId(@AuthenticationPrincipal CustomUserDetails userAuthentication,
                                             @PathVariable("profileId") UUID profileId,
                                             @PathVariable("badgeId") UUID badgeId) {
        ResponseDTO<?> response = new ResponseDTO<>(getUserBadgeByProfileIdAndBadgeIdUseCase.execute(profileId, badgeId));
        return ResponseEntity.ok(response);
    }
}
