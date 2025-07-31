package br.com.starter.domain.badge;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "badges")
@Audited
public class Badge extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String description;
    @NotNull
    @NotEmpty
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Category category;
    @Version
    private Long version;
}
