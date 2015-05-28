package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartItemAddedEvent;

import java.util.UUID;

public class ShoppingCartItemAddedEventBuilder {
    private UUID id;
    private String item;

    public ShoppingCartItemAddedEventBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public ShoppingCartItemAddedEventBuilder setItem(String item) {
        this.item = item;
        return this;
    }

    public ShoppingCartItemAddedEvent createShoppingCartItemAddedEvent() {
        return new ShoppingCartItemAddedEvent(id, item);
    }
}