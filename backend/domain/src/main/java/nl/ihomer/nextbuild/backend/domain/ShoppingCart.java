package nl.ihomer.nextbuild.backend.domain;

import nl.ihomer.nextbuild.backend.api.model.ShoppingCartState;
import nl.ihomer.nextbuild.backend.domain.commands.*;
import nl.ihomer.nextbuild.backend.domain.events.*;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * Created by bvangameren on 26/05/15.
 */
public class ShoppingCart extends AbstractAnnotatedAggregateRoot<ShoppingCart> {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCart.class);

    @AggregateIdentifier
    private UUID id;
    private ShoppingCartState state;
    private Set<String> items;

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

    @CommandHandler
    public void handle(AddShoppingCartItemCommand command){
        LOG.debug("Handle add shopping cart item command [{}]", command);
        checkState(state.equals(ShoppingCartState.EMPTY) || state.equals(ShoppingCartState.PENDING));
        checkArgument(!items.contains(command.getItem()));
        apply(new ShoppingCartItemAddedEvent(
                command.getCartId(),
                command.getItem()
        ));
    }

    @CommandHandler
    public void handle(RemoveShoppingCartItemCommand command){
        LOG.debug("Handle remove shopping cart item command [{}]", command);
        checkState(state.equals(ShoppingCartState.PENDING));
        checkArgument(items.contains(command.getItem()));
        apply(new ShoppingCartItemRemovedEvent(
                command.getCartId(),
                command.getItem()
        ));

    }

    @CommandHandler
    public void handle(CheckoutShoppingCartCommand command){
        LOG.debug("Handle checkout shopping cart command [{}]", command);
        checkState(state.equals(ShoppingCartState.PENDING));
        apply(new ShoppingCartCheckedOutEvent(
                command.getId()
        ));

    }

    @CommandHandler
    public void handle(CancelShoppingCartCheckoutCommand command){
        LOG.debug("Handle cancel shopping cart checkout command [{}]", command);
        checkState(state.equals(ShoppingCartState.CHECKOUT));
        apply(new ShoppingCartCheckoutCancelledEvent(
                command.getId()
        ));

    }

    @CommandHandler
    public void handle(AcceptShoppingCartCommand command){
        LOG.debug("Handle accept shopping cart command [{}]", command);
        checkState(state.equals(ShoppingCartState.CHECKOUT));
        apply(new ShoppingCartAcceptedEvent(
                command.getId()
        ));

    }

    @CommandHandler
    public void handle(RejectShoppingCartCommand command){
        LOG.debug("Handle reject shopping cart command [{}]", command);
        checkState(state.equals(ShoppingCartState.CHECKOUT));
        apply(new ShoppingCartRejectedEvent(
                command.getId()
        ));

    }

    @EventSourcingHandler
    public void handle(ShoppingCartRegisteredEvent event){
        LOG.debug("Handle shopping cart registered event [{}]", event);
        this.id = event.getId();
        this.state = ShoppingCartState.EMPTY;
        this.items = new HashSet<>();
    }

    @EventSourcingHandler
    public void handle(ShoppingCartItemAddedEvent event){
        LOG.debug("Handle shopping cart item added event [{}]", event);
        this.state = ShoppingCartState.PENDING;
        this.items.add(event.getItem());
    }

    @EventSourcingHandler
    public void handle(ShoppingCartItemRemovedEvent event){
        LOG.debug("Handle shopping cart item removed event [{}]", event);
        this.items.remove(event.getItem());
        this.state = items.isEmpty() ? ShoppingCartState.EMPTY : ShoppingCartState.PENDING;
    }

    @EventSourcingHandler
    public void handle(ShoppingCartCheckedOutEvent event){
        LOG.debug("Handle shopping cart checkout event [{}]", event);
        this.state = ShoppingCartState.CHECKOUT;
    }

    @EventSourcingHandler
    public void handle(ShoppingCartCheckoutCancelledEvent event){
        LOG.debug("Handle shopping cart checkout cancelled event [{}]", event);
        this.state = ShoppingCartState.PENDING;
    }

    @EventSourcingHandler
    public void handle(ShoppingCartAcceptedEvent event){
        LOG.debug("Handle shopping cart accepted event [{}]", event);
        this.state = ShoppingCartState.ACCEPTED;
    }

    @EventSourcingHandler
    public void handle(ShoppingCartRejectedEvent event){
        LOG.debug("Handle shopping cart rejected event [{}]", event);
        this.state = ShoppingCartState.REJECTED;
    }
}
