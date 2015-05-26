package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartRegisteredEvent;

import java.util.UUID;

public class ShoppingCartRegisteredEventBuilder {
    private UUID id;
    private String name;

    public ShoppingCartRegisteredEventBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public ShoppingCartRegisteredEventBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ShoppingCartRegisteredEvent createShoppingCartRegisteredEvent() {
        return new ShoppingCartRegisteredEvent(id, name);
    }
}