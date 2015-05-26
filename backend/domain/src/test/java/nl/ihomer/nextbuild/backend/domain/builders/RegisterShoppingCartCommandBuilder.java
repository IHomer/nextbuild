package nl.ihomer.nextbuild.backend.domain.builders;

import nl.ihomer.nextbuild.backend.domain.commands.RegisterShoppingCartCommand;

public class RegisterShoppingCartCommandBuilder {
    private String name;

    public RegisterShoppingCartCommandBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RegisterShoppingCartCommand createRegisterShoppingCartCommand() {
        return new RegisterShoppingCartCommand(name);
    }
}
