package br.com.starter.domain.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.UUID;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Audited(targetAuditMode = NOT_AUDITED)
@Table(name = "addresses")
@Getter
@Setter
public class Address {
    @Id
    private UUID id = UUID.randomUUID();
    private String country;
    private String city;
    private String state;
}