package nl.ihomer.nextbuild.backend.view.persistence;

import nl.ihomer.nextbuild.backend.api.model.ShoppingCart;
import nl.ihomer.nextbuild.backend.api.model.ShoppingCartItem;
import nl.ihomer.nextbuild.backend.api.model.ShoppingCartState;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by bvangameren on 09/01/15.
 */

@Entity
public class ShoppingCartEntity extends ShoppingCart {


    public ShoppingCartEntity() {
    }

    public ShoppingCartEntity(UUID id, ShoppingCartState state, String name, LocalDateTime registrationTimestamp) {
        super();
        super.setId(id);
        super.setState(state);
        super.setName(name);
        super.setRegistrationTimestamp(registrationTimestamp);
    }

    @Id
    @Type(type = "uuid-char")
    @Override
    public UUID getId() {
        return super.getId();
    }

    @Enumerated(EnumType.STRING)
    @Override
    public ShoppingCartState getState() {
        return super.getState();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ShoppingCartItemEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingCartId")
    @Override
    public Set<ShoppingCartItem> getItems() {
        return super.getItems();
    }

    @Override
    public LocalDateTime getRegistrationTimestamp() {
        return super.getRegistrationTimestamp();
    }

}
