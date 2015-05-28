package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.commands.AddShoppingCartItemCommand;

import java.util.UUID;

public class AddShoppingCartItemCommandBuilder {
    private UUID cartId;
    private String item;

    public AddShoppingCartItemCommandBuilder setCartId(UUID cartId) {
        this.cartId = cartId;
        return this;
    }

    public AddShoppingCartItemCommandBuilder setItem(String item) {
        this.item = item;
        return this;
    }

    public AddShoppingCartItemCommand createAddShoppingCartItemCommand() {
        return new AddShoppingCartItemCommand(cartId, item);
    }
}