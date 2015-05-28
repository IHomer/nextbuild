package nl.ihomer.nextbuild.backend.domain;

import nl.ihomer.nextbuild.backend.domain.builders.CheckOutShoppingCartCommandBuilder;
import nl.ihomer.nextbuild.backend.domain.builders.ShoppingCartCheckedOutEventBuilder;
import nl.ihomer.nextbuild.backend.domain.builders.ShoppingCartItemAddedEventBuilder;
import nl.ihomer.nextbuild.backend.domain.builders.ShoppingCartRegisteredEventBuilder;
import nl.ihomer.nextbuild.backend.domain.commands.CheckoutShoppingCartCommand;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartCheckedOutEvent;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartItemAddedEvent;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartRegisteredEvent;
import org.axonframework.repository.AggregateNotFoundException;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by jojo on 08/12/14.
 */
public class CheckoutShoppingCartCommandTest {

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

    private final CheckoutShoppingCartCommand checkoutShoppingCartCommand = new CheckOutShoppingCartCommandBuilder()
            .setId(id)
            .createCheckOutShoppingCartCommand();

    private final ShoppingCartCheckedOutEvent shoppingCartCheckedOutEvent = new ShoppingCartCheckedOutEventBuilder()
            .setId(id)
            .createShoppingCartCheckedOutEvent();

    @Test
    public void checkOutShoppingCartCommandShouldDispatchShoppingCartCheckedOutEvent() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
            .given(shoppingCartRegisteredEvent, shoppingCartItemAddedEvent)
            .when(checkoutShoppingCartCommand)
            .expectEvents(shoppingCartCheckedOutEvent);

    }

    @Test
    public void checkOutShoppingCartCommandShouldThrowNotFoundException() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
                .given()
                .when(checkoutShoppingCartCommand)
                .expectException(AggregateNotFoundException.class);

    }

    @Test
    public void checkOutShoppingCartCommandThrowIllegalStateException() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
                .given(shoppingCartRegisteredEvent)
                .when(checkoutShoppingCartCommand)
                .expectException(IllegalStateException.class);

    }
}
