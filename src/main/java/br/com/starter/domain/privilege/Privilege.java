package br.com.starter.domain.privilege;

import br.com.starter.domain.common.Auditable;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "privileges")
@Audited
@Setter
@Getter
public class Privilege extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    private String name = null;
    private Boolean isSignatureRevoked = false;
    @Version
    private Long version;
}
