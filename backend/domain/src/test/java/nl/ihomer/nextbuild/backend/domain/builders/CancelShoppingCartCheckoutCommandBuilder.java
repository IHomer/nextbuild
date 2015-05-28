package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.commands.CancelShoppingCartCheckoutCommand;

import java.util.UUID;

public class CancelShoppingCartCheckoutCommandBuilder {

    private UUID id;

    public CancelShoppingCartCheckoutCommandBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public CancelShoppingCartCheckoutCommand createCancelCheckOutShoppingCartCommand() {
        return new CancelShoppingCartCheckoutCommand(id);
    }
}