package nl.ihomer.nextbuild.backend.view.handlers;

import nl.ihomer.nextbuild.backend.api.model.ShoppingCartItem;
import nl.ihomer.nextbuild.backend.api.model.ShoppingCartState;
import nl.ihomer.nextbuild.backend.domain.events.*;
import nl.ihomer.nextbuild.backend.view.persistence.ShoppingCartEntity;
import nl.ihomer.nextbuild.backend.view.persistence.ShoppingCartEntityRepository;
import nl.ihomer.nextbuild.backend.view.persistence.ShoppingCartItemEntity;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by bvangameren on 09/01/15.
 */

@Component
public class ShoppingCartEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartEventListener.class);

    @Autowired
    private ShoppingCartEntityRepository shoppingCartEntityRepository;

    /**
     * Event handler which handles a person registered event by saving the user;
     *
     * @param event containing the information of a person
     */
    @EventHandler
    public void handle(ShoppingCartRegisteredEvent event, @Timestamp DateTime timeStamp){
        LOG.debug("Handle Shopping cart registered event [{}]", event);
        LocalDateTime registrationDate = JodaDateTimeConverter.convertToLocalDateTime(timeStamp);

        ShoppingCartEntity shoppingCart = new ShoppingCartEntity(
                event.getId(),
                ShoppingCartState.EMPTY,
                event.getName(),
                registrationDate
        );

        shoppingCartEntityRepository.save(shoppingCart);

    }

    @EventHandler
    public void handle(ShoppingCartItemAddedEvent event, @Timestamp DateTime timeStamp){
        LOG.debug("Handle Shopping cart item added event [{}]", event);
        LocalDateTime time = JodaDateTimeConverter.convertToLocalDateTime(timeStamp);

        ShoppingCartEntity shoppingCart = shoppingCartEntityRepository.findOne(event.getId());
        Set<ShoppingCartItem> items = shoppingCart.getItems();
        ShoppingCartItem item = new ShoppingCartItemEntity(event.getItem(), time);
        items.add(item);
        shoppingCart.setItems(items);
        shoppingCart.setState(ShoppingCartState.PENDING);
        shoppingCartEntityRepository.save(shoppingCart);
    }

    @EventHandler
    public void handle(ShoppingCartItemRemovedEvent event){
        LOG.debug("Handle Shopping cart item removed event [{}]", event);
        ShoppingCartEntity shoppingCart = shoppingCartEntityRepository.findOne(event.getId());
        ShoppingCartItem item = new ShoppingCartItemEntity(event.getItem());
        Set<ShoppingCartItem> items = shoppingCart.getItems();
        items.remove(item);
        shoppingCart.setItems(items);
        ShoppingCartState state = items.isEmpty() ? ShoppingCartState.EMPTY : ShoppingCartState.PENDING;
        shoppingCart.setState(state);
        shoppingCartEntityRepository.save(shoppingCart);
    }

    @EventHandler
    public void handle(ShoppingCartCheckedOutEvent event){
        LOG.debug("Handle Shopping cart checked out event [{}]", event);
        ShoppingCartEntity shoppingCart = shoppingCartEntityRepository.findOne(event.getId());
        shoppingCart.setState(ShoppingCartState.CHECKOUT);
        shoppingCartEntityRepository.save(shoppingCart);
    }

    @EventHandler
    public void handle(ShoppingCartCheckoutCancelledEvent event){
        LOG.debug("Handle Shopping cart checked out cancelled event [{}]", event);
        ShoppingCartEntity shoppingCart = shoppingCartEntityRepository.findOne(event.getId());
        shoppingCart.setState(ShoppingCartState.PENDING);
        shoppingCartEntityRepository.save(shoppingCart);
    }

    @EventHandler
    public void handle(ShoppingCartAcceptedEvent event){
        LOG.debug("Handle Shopping cart accepted event [{}]", event);
        ShoppingCartEntity shoppingCart = shoppingCartEntityRepository.findOne(event.getId());
        shoppingCart.setState(ShoppingCartState.ACCEPTED);
        shoppingCartEntityRepository.save(shoppingCart);
    }

    @EventHandler
    public void handle(ShoppingCartRejectedEvent event){
        LOG.debug("Handle Shopping cart rejected event [{}]", event);
        ShoppingCartEntity shoppingCart = shoppingCartEntityRepository.findOne(event.getId());
        shoppingCart.setState(ShoppingCartState.REJECTED);
        shoppingCartEntityRepository.save(shoppingCart);
    }

    private static class JodaDateTimeConverter {
        public static LocalDateTime convertToLocalDateTime(DateTime timeStamp) {
            if (timeStamp != null) {
                return LocalDateTime.of(
                        timeStamp.getYear(),
                        timeStamp.getMonthOfYear(),
                        timeStamp.getDayOfMonth(),
                        timeStamp.getHourOfDay(),
                        timeStamp.getMinuteOfHour(),
                        timeStamp.getSecondOfMinute());
            }
            return null;
        }
    }
}
