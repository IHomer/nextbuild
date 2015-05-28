package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartCheckoutCancelledEvent;

import java.util.UUID;

public class ShoppingCartCheckoutCancelledEventBuilder {
    private UUID id;

    public ShoppingCartCheckoutCancelledEventBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public ShoppingCartCheckoutCancelledEvent createShoppingCartCheckoutCancelledEvent() {
        return new ShoppingCartCheckoutCancelledEvent(id);
    }
}