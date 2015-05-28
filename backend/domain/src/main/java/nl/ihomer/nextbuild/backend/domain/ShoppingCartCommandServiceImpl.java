package nl.ihomer.nextbuild.backend.domain;

import nl.ihomer.nextbuild.backend.api.ShoppingCartCommandService;
import nl.ihomer.nextbuild.backend.domain.commands.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.replay.ReplayingCluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
@Service
public class ShoppingCartCommandServiceImpl implements ShoppingCartCommandService {

    @Autowired
    private CommandGateway commandGateway;

    @Override
    public void registerShoppingCart(String name) {
        RegisterShoppingCartCommand command = new RegisterShoppingCartCommand(name);
        this.commandGateway.sendAndWait(command);
    }

    @Override
    public void addShoppingCartItem(UUID id, String item) {
        AddShoppingCartItemCommand command = new AddShoppingCartItemCommand(id, item);
        this.commandGateway.sendAndWait(command);
    }

    @Override
    public void removeShoppingCartItem(UUID id, String item) {
        RemoveShoppingCartItemCommand command = new RemoveShoppingCartItemCommand(id, item);
        this.commandGateway.sendAndWait(command);
    }

    @Override
    public void checkoutShoppingCart(UUID id) {
        CheckoutShoppingCartCommand command = new CheckoutShoppingCartCommand(id);
        this.commandGateway.sendAndWait(command);
    }

    @Override
    public void cancelShoppingCartCheckout(UUID id) {
        CancelShoppingCartCheckoutCommand command = new CancelShoppingCartCheckoutCommand(id);
        this.commandGateway.sendAndWait(command);
    }

    @Override
    public void acceptShoppingCart(UUID id) {
        AcceptShoppingCartCommand command = new AcceptShoppingCartCommand(id);
        this.commandGateway.sendAndWait(command);
    }

    @Override
    public void rejectShoppingCart(UUID id) {
        RejectShoppingCartCommand command = new RejectShoppingCartCommand(id);
        this.commandGateway.sendAndWait(command);
    }
}
