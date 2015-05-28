package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartAcceptedEvent;

import java.util.UUID;

public class ShoppingCartAcceptedEventBuilder {
    private UUID id;

    public ShoppingCartAcceptedEventBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public ShoppingCartAcceptedEvent createShoppingCartAcceptedEvent() {
        return new ShoppingCartAcceptedEvent(id);
    }
}