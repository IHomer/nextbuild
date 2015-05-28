package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartRejectedEvent;

import java.util.UUID;

public class ShoppingCartRejectedEventBuilder {
    private UUID id;

    public ShoppingCartRejectedEventBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public ShoppingCartRejectedEvent createShoppingCartRejectedEvent() {
        return new ShoppingCartRejectedEvent(id);
    }
}