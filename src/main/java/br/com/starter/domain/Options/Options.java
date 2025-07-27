package br.com.starter.domain.Options;

import br.com.starter.domain.common.Auditable;
import br.com.starter.domain.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Entity
@Audited
@Getter
@Setter
@Table(name = "options")
public class Options extends Auditable {
    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;
    private String text;
    private String imageUrl;
    private boolean isCorrect;
    @Version
    private Long version;
}
