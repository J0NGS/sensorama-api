package br.com.starter.domain.badge;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.common.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "badges")
public class Badge extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private String description;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Category category;
    @Version
    private Long version;
}
