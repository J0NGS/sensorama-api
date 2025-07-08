package br.com.starter.domain.question;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.common.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    private String title;
    private String description;
    private String mediaUrl;
    private MediaType mediaType;
    @Version
    private Long version;
}
