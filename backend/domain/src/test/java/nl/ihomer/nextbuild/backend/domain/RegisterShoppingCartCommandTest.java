package nl.ihomer.nextbuild.backend.domain;

import nl.ihomer.nextbuild.backend.domain.builders.RegisterShoppingCartCommandBuilder;
import nl.ihomer.nextbuild.backend.domain.builders.ShoppingCartRegisteredEventBuilder;
import nl.ihomer.nextbuild.backend.domain.commands.RegisterShoppingCartCommand;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartRegisteredEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by jojo on 08/12/14.
 */
public class RegisterShoppingCartCommandTest {

    private FixtureConfiguration<ShoppingCart> fixture;

    private final String name = "Shopping cart 1";
    private final RegisterShoppingCartCommand registerShoppingCartCommand = new RegisterShoppingCartCommandBuilder()
            .setName(name)
            .createRegisterShoppingCartCommand();

    private final UUID id = registerShoppingCartCommand.getId();

    private final ShoppingCartRegisteredEvent shoppingCartRegisteredEvent = new ShoppingCartRegisteredEventBuilder()
            .setId(id)
            .setName(name)
            .createShoppingCartRegisteredEvent();

    @Test
    public void registerShoppingCartCommandShouldDispatchShoppingCartRegisteredEvent() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
            .given()
            .when(registerShoppingCartCommand)
            .expectEvents(shoppingCartRegisteredEvent);

    }
}
