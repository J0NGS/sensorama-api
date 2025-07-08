package br.com.starter.domain.role;

import br.com.starter.domain.common.Auditable;
import br.com.starter.domain.privilege.Privilege;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Audited
@Table(name = "roles")
@Setter
@Getter
public class Role extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "privileges_on_roles",
        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private List<Privilege> privileges;

    @Version
    private Long version;
}
