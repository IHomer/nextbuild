package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.commands.RejectShoppingCartCommand;

import java.util.UUID;

public class RejectShoppingCartCommandBuilder {

    private UUID id;

    public RejectShoppingCartCommandBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public RejectShoppingCartCommand createRejectShoppingCartCommand() {
        return new RejectShoppingCartCommand(id);
    }
}