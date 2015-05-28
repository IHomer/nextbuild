package nl.ihomer.nextbuild.backend.view.persistence;

import nl.ihomer.nextbuild.backend.api.model.ShoppingCart;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by bvangameren on 09/01/15.
 */

@Entity
public class ShoppingCartEntity extends ShoppingCart {


    public ShoppingCartEntity() {
    }

    public ShoppingCartEntity(UUID id, String name, LocalDateTime registrationTimestamp) {
        super();
        super.setId(id);
        super.setName(name);
        super.setRegistrationTimestamp(registrationTimestamp);
    }

    @Id
    @Type(type = "uuid-char")
    public UUID getId() {
        return super.getId();
    }

    public String getName() {
        return super.getName();
    }

    public LocalDateTime getRegistrationTimestamp() {
        return super.getRegistrationTimestamp();
    }

}
