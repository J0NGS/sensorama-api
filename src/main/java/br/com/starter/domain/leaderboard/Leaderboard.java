package br.com.starter.domain.leaderboard;

import br.com.starter.domain.common.Auditable;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "leaderboards")
@Audited
public class Leaderboard extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private String description;
    private String imageUrl;
    private LeaderboardType type;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime lastUpdated;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "leaderboard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeaderboardEntry> table = new ArrayList<>();

    @Version
    private Long version;
}
