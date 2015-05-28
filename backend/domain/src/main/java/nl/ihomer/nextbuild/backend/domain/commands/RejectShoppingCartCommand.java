package nl.ihomer.nextbuild.backend.domain.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public class RejectShoppingCartCommand {

    @TargetAggregateIdentifier
    private final UUID id;

    public RejectShoppingCartCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AcceptShoppingCartCommand{" +
                "id=" + id +
                '}';
    }
}
