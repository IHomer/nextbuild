package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.commands.RemoveShoppingCartItemCommand;

import java.util.UUID;

public class RemoveShoppingCartItemCommandBuilder {
    private UUID cartId;
    private String item;

    public RemoveShoppingCartItemCommandBuilder setCartId(UUID cartId) {
        this.cartId = cartId;
        return this;
    }

    public RemoveShoppingCartItemCommandBuilder setItem(String item) {
        this.item = item;
        return this;
    }

    public RemoveShoppingCartItemCommand createRemoveShoppingCartItemCommand() {
        return new RemoveShoppingCartItemCommand(cartId, item);
    }
}