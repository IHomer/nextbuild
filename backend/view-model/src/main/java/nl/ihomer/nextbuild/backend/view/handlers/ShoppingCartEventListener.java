package nl.ihomer.nextbuild.backend.view.handlers;

import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartRegisteredEvent;
import nl.ihomer.nextbuild.backend.view.persistence.ShoppingCartEntity;
import nl.ihomer.nextbuild.backend.view.persistence.ShoppingCartEntityRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
                event.getName(),
                registrationDate
        );

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
