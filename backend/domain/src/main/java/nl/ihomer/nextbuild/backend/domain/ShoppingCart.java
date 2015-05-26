package nl.ihomer.nextbuild.backend.domain;

import nl.ihomer.nextbuild.backend.domain.commands.RegisterShoppingCartCommand;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartRegisteredEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public class ShoppingCart extends AbstractAnnotatedAggregateRoot<ShoppingCart> {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCart.class);

    @AggregateIdentifier
    private UUID id;

    protected ShoppingCart() {
        /* needed by the axon framework */
    }

    @CommandHandler
    public ShoppingCart(RegisterShoppingCartCommand command){
        LOG.debug("Handle register shopping cart command [{}]", command);
        apply(new ShoppingCartRegisteredEvent(
                command.getId(),
                command.getName()
        ));
    }

    @EventSourcingHandler
    public void handle(ShoppingCartRegisteredEvent event){
        LOG.debug("Handle shopping cart registered event [{}]", event);
        this.id = event.getId();
    }
}
