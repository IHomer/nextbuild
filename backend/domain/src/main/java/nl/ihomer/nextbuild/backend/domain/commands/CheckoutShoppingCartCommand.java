package nl.ihomer.nextbuild.backend.domain.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public class CheckoutShoppingCartCommand {

    @TargetAggregateIdentifier
    private final UUID id;

    public CheckoutShoppingCartCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CheckOutShoppingCartCommand{" +
                "id=" + id +
                '}';
    }
}
