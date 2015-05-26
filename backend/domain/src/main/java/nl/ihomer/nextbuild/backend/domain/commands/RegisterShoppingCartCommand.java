package nl.ihomer.nextbuild.backend.domain.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public class RegisterShoppingCartCommand {

    @TargetAggregateIdentifier
    private final UUID id;
    private final String name;

    public RegisterShoppingCartCommand(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RegisterShoppingCartCommand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
