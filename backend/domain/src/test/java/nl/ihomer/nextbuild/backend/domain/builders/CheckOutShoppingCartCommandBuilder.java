package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.commands.CheckoutShoppingCartCommand;

import java.util.UUID;

public class CheckOutShoppingCartCommandBuilder {

    private UUID id;

    public CheckOutShoppingCartCommandBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public CheckoutShoppingCartCommand createCheckOutShoppingCartCommand() {
        return new CheckoutShoppingCartCommand(id);
    }
}