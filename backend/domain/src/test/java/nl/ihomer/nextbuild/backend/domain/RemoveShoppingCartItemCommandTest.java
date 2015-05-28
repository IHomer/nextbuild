package nl.ihomer.nextbuild.backend.domain;

import nl.ihomer.nextbuild.backend.domain.builders.RemoveShoppingCartItemCommandBuilder;
import nl.ihomer.nextbuild.backend.domain.builders.ShoppingCartItemAddedEventBuilder;
import nl.ihomer.nextbuild.backend.domain.builders.ShoppingCartItemRemovedEventBuilder;
import nl.ihomer.nextbuild.backend.domain.builders.ShoppingCartRegisteredEventBuilder;
import nl.ihomer.nextbuild.backend.domain.commands.RemoveShoppingCartItemCommand;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartItemAddedEvent;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartItemRemovedEvent;
import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartRegisteredEvent;
import org.axonframework.repository.AggregateNotFoundException;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by jojo on 08/12/14.
 */
public class RemoveShoppingCartItemCommandTest {

    private FixtureConfiguration<ShoppingCart> fixture;

    private final String name = "Shopping cart 1";
    private final UUID id = UUID.randomUUID();
    private final String item = "Item 1";
    private final String nonExistingItem = "Not a Item";

    private final ShoppingCartRegisteredEvent shoppingCartRegisteredEvent = new ShoppingCartRegisteredEventBuilder()
            .setId(id)
            .setName(name)
            .createShoppingCartRegisteredEvent();

    private final ShoppingCartItemAddedEvent shoppingCartItemAddedEvent = new ShoppingCartItemAddedEventBuilder()
            .setId(id)
            .setItem(item)
            .createShoppingCartItemAddedEvent();

    private final RemoveShoppingCartItemCommand removeShoppingCartItemCommand = new RemoveShoppingCartItemCommandBuilder()
            .setCartId(id)
            .setItem(item)
            .createRemoveShoppingCartItemCommand();

    private final RemoveShoppingCartItemCommand removeNonExistingShoppingCartItemCommand = new RemoveShoppingCartItemCommandBuilder()
            .setCartId(id)
            .setItem(nonExistingItem)
            .createRemoveShoppingCartItemCommand();

    private final ShoppingCartItemRemovedEvent shoppingCartItemRemovedEvent = new ShoppingCartItemRemovedEventBuilder()
            .setId(id)
            .setItem(item)
            .createShoppingCartItemRemovedEvent();

    @Test
    public void removeShoppingCartItemCommandShouldDispatchShoppingCartItemRemovedEvent() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
            .given(shoppingCartRegisteredEvent, shoppingCartItemAddedEvent)
            .when(removeShoppingCartItemCommand)
            .expectEvents(shoppingCartItemRemovedEvent);

    }

    @Test
    public void removeShoppingCartItemCommandThrowIllegalStateException() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
                .given(shoppingCartRegisteredEvent)
                .when(removeShoppingCartItemCommand)
                .expectException(IllegalStateException.class);

    }

    @Test
    public void removeShoppingCartItemCommandThrowIllegalArgumentException() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
                .given(shoppingCartRegisteredEvent, shoppingCartItemAddedEvent)
                .when(removeNonExistingShoppingCartItemCommand)
                .expectException(IllegalArgumentException.class);

    }

    @Test
    public void addShoppingCartItemCommandShouldThrowNotFoundException() {
        fixture = Fixtures.newGivenWhenThenFixture(ShoppingCart.class);
        fixture
                .given()
                .when(removeShoppingCartItemCommand)
                .expectException(AggregateNotFoundException.class);

    }


}
