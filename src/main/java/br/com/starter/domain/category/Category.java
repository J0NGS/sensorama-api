package br.com.starter.domain.category;


import br.com.starter.domain.common.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Entity
@Getter
@Setter
@Audited
@Table(name = "categories")
public class Category extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private String description;
    private String iconUrl;
    @Version
    private Long version;
}
