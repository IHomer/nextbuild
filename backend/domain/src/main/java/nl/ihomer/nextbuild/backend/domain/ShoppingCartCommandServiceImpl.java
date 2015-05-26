package nl.ihomer.nextbuild.backend.domain;

import nl.ihomer.nextbuild.backend.api.ShoppingCartCommandService;
import nl.ihomer.nextbuild.backend.domain.commands.RegisterShoppingCartCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
