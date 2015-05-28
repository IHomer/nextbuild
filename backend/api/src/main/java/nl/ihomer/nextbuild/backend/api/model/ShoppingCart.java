package nl.ihomer.nextbuild.backend.api.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by bvangameren on 27/05/15.
 */
public abstract class ShoppingCart {
    private UUID id;
    private ShoppingCartState state;
    private String name;
    private Set<ShoppingCartItem> items;
    private LocalDateTime registrationTimestamp;

    public UUID getId() {
        return id;
    }

    public ShoppingCartState getState() {
        return state;
    }

    public void setState(ShoppingCartState state) {
        this.state = state;
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

    public Set<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(Set<ShoppingCartItem> items) {
        this.items = items;
    }

    public LocalDateTime getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp(LocalDateTime registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }
}
