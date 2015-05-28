package nl.ihomer.nextbuild.backend.domain.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public class RemoveShoppingCartItemCommand {

    @TargetAggregateIdentifier
    private final UUID cartId;
    private final String item;

    public RemoveShoppingCartItemCommand(UUID cartId, String item) {
        this.cartId = cartId;
        this.item = item;
    }

    public UUID getCartId() {
        return cartId;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "RemoveShoppingCartItemCommand{" +
                "cartId=" + cartId +
                ", item='" + item + '\'' +
                '}';
    }
}
