package nl.ihomer.nextbuild.backend.domain;

import nl.ihomer.nextbuild.backend.domain.builders.AddShoppingCartItemCommandBuilder;
import nl.ihomer.nextbuild.backend.domain.builders.ShoppingCartItemAddedEventBuilder;
import nl.ihomer.nextbuild.backend.domain.builders.ShoppingCartRegisteredEventBuilder;
import nl.ihomer.nextbuild.backend.domain.commands.AddShoppingCartItemCommand;
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
public class AddShoppingCartItemCommandTest {

    private FixtureConfiguration<ShoppingCart> fixture;

    private final String name = "Shopping cart 1";
    private final UUID id = UUID.randomUUID();
    private final String item = "Item 1";

    private final ShoppingCartRegisteredEvent shoppingCartRegisteredEvent = new ShoppingCartRegisteredEventBuilder()
            .setId(id)
            .setName(name)
            .createShoppingCartRegisteredEvent();

    private final AddShoppingCartItemCommand addShoppingCartItemCommand = new AddShoppingCartItemCommandBuilder()
            .setCartId(id)
            .setItem(item)
            .createAddShoppingCartItemCommand();

    private final ShoppingCartItemAddedEvent shoppingCartItemAddedEvent = new ShoppingCartItemAddedEventBuilder()
            .setId(id)
            .setItem(item)
            .createShoppingCartItemAddedEvent();
    @Test
    public void addShoppingCartItemCommandShouldDispatchShoppingCartItemAddedEvent() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
            .given(shoppingCartRegisteredEvent)
            .when(addShoppingCartItemCommand)
            .expectEvents(shoppingCartItemAddedEvent);

    }

    @Test
    public void addShoppingCartItemCommandShouldThrowIllegalArgumentException() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
                .given(shoppingCartRegisteredEvent, shoppingCartItemAddedEvent)
                .when(addShoppingCartItemCommand)
                .expectException(IllegalArgumentException.class);

    }

    @Test
    public void addShoppingCartItemCommandShouldThrowNotFoundException() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
                .given()
                .when(addShoppingCartItemCommand)
                .expectException(AggregateNotFoundException.class);

    }
}
