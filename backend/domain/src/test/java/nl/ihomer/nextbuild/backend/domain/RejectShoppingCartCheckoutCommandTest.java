package nl.ihomer.nextbuild.backend.domain;

import nl.ihomer.nextbuild.backend.domain.builders.*;
import nl.ihomer.nextbuild.backend.domain.commands.RejectShoppingCartCommand;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartCheckedOutEvent;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartItemAddedEvent;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartRegisteredEvent;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartRejectedEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by jojo on 08/12/14.
 */
public class RejectShoppingCartCheckoutCommandTest {

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

    private final RejectShoppingCartCommand rejectShoppingCartCommand = new RejectShoppingCartCommandBuilder()
            .setId(id)
            .createRejectShoppingCartCommand();

    private final ShoppingCartRejectedEvent shoppingCartRejectedEvent = new ShoppingCartRejectedEventBuilder()
            .setId(id)
            .createShoppingCartRejectedEvent();

    @Test
    public void rejectShoppingCartCommandShouldDispatchShoppingCartRejectedEvent() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
            .given(shoppingCartRegisteredEvent, shoppingCartItemAddedEvent, shoppingCartCheckedOutEvent)
            .when(rejectShoppingCartCommand)
            .expectEvents(shoppingCartRejectedEvent);

    }
}
