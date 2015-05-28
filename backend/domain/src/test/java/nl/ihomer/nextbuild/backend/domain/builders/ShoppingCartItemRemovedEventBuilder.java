package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartItemRemovedEvent;

import java.util.UUID;

public class ShoppingCartItemRemovedEventBuilder {
    private UUID id;
    private String item;

    public ShoppingCartItemRemovedEventBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public ShoppingCartItemRemovedEventBuilder setItem(String item) {
        this.item = item;
        return this;
    }

    public ShoppingCartItemRemovedEvent createShoppingCartItemRemovedEvent() {
        return new ShoppingCartItemRemovedEvent(id, item);
    }
}