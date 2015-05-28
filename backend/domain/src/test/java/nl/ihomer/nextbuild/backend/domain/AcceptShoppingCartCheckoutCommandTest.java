package nl.ihomer.nextbuild.backend.domain;

import nl.ihomer.nextbuild.backend.domain.builders.*;
import nl.ihomer.nextbuild.backend.domain.commands.AcceptShoppingCartCommand;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartAcceptedEvent;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartCheckedOutEvent;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartItemAddedEvent;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartRegisteredEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by jojo on 08/12/14.
 */
public class AcceptShoppingCartCheckoutCommandTest {

    private FixtureConfiguration<ShoppingCart> fixture;

    private final String name = "Shopping cart 1";
    private final UUID id = UUID.randomUUID();
    private final String item = "Item 1";

    private final ShoppingCartRegisteredEvent shoppingCartRegisteredEvent = new ShoppingCartRegisteredEventBuilder()
            .setId(id)
            .setName(name)
            .createShoppingCartRegisteredEvent();

    private final ShoppingCartItemAddedEvent shoppingCartItemAddedEvent = new ShoppingCartItemAddedEventBuilder()
            .setId(id)
            .setItem(item)
            .createShoppingCartItemAddedEvent();

    private final ShoppingCartCheckedOutEvent shoppingCartCheckedOutEvent = new ShoppingCartCheckedOutEventBuilder()
            .setId(id)
            .createShoppingCartCheckedOutEvent();

    private final AcceptShoppingCartCommand acceptShoppingCartCommand = new AcceptShoppingCartCommandBuilder()
            .setId(id)
            .createAcceptShoppingCartCommand();

    private final ShoppingCartAcceptedEvent shoppingCartAcceptedEvent = new ShoppingCartAcceptedEventBuilder()
            .setId(id)
            .createShoppingCartAcceptedEvent();

    @Test
    public void acceptShoppingCartCommandShouldDispatchShoppingCartAcceptedEvent() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
            .given(shoppingCartRegisteredEvent, shoppingCartItemAddedEvent, shoppingCartCheckedOutEvent)
            .when(acceptShoppingCartCommand)
            .expectEvents(shoppingCartAcceptedEvent);

    }
}
