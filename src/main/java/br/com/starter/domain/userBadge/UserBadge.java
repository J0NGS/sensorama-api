package br.com.starter.domain.userBadge;

import br.com.starter.domain.badge.Badge;
import br.com.starter.domain.common.Auditable;
import br.com.starter.domain.profile.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_badges")
public class UserBadge extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "badge_id", referencedColumnName = "id")
    private Profile profile;
    @ManyToOne
    @JoinColumn(name = "badge_id", referencedColumnName = "id")
    private Badge badge;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime earnedAt;
    @Version
    private Long version;
}
