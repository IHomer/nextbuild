package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.commands.AcceptShoppingCartCommand;

import java.util.UUID;

public class AcceptShoppingCartCommandBuilder {

    private UUID id;

    public AcceptShoppingCartCommandBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public AcceptShoppingCartCommand createAcceptShoppingCartCommand() {
        return new AcceptShoppingCartCommand(id);
    }
}