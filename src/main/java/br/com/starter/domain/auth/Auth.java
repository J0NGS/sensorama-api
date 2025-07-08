package br.com.starter.domain.auth;
import br.com.starter.domain.common.Auditable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Audited
@Table(name = "auths")
@Getter
@Setter
public class Auth extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    @Column(unique = true)
    private String username = null;
    @JsonIgnore
    private String password = null;
    @Version
    private Long version;
}

