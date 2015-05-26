package nl.ihomer.nextbuild.backend.view.persistence;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by bvangameren on 09/01/15.
 */

@Entity
public class ShoppingCartEntity {

    private UUID id;
    private String name;
    private LocalDateTime registrationTimestamp;


    public ShoppingCartEntity() {
    }

    public ShoppingCartEntity(UUID id, String name, LocalDateTime registrationTimestamp) {
        this.id = id;
        this.name = name;
        this.registrationTimestamp = registrationTimestamp;
    }

    @Id
    @Type(type = "uuid-char")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp(LocalDateTime registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }
}
