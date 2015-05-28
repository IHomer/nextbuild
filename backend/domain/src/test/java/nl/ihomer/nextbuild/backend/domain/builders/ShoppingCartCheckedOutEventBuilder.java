package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartCheckedOutEvent;

import java.util.UUID;

public class ShoppingCartCheckedOutEventBuilder {
    private UUID id;

    public ShoppingCartCheckedOutEventBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public ShoppingCartCheckedOutEvent createShoppingCartCheckedOutEvent() {
        return new ShoppingCartCheckedOutEvent(id);
    }
}