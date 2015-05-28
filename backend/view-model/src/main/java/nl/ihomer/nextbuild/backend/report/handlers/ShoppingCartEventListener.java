package nl.ihomer.nextbuild.backend.report.handlers;

import nl.ihomer.nextbuild.backend.domain.events.ShoppingCartItemRemovedEvent;
import nl.ihomer.nextbuild.backend.report.persistence.ReportItemEntity;
import nl.ihomer.nextbuild.backend.report.persistence.ReportItemEntityRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.axonframework.eventhandling.replay.ReplayAware;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by bvangameren on 09/01/15.
 */

@Component("ReportShoppingCartEventListener")
public class ShoppingCartEventListener implements ReplayAware {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartEventListener.class);

    @Autowired
    private ReportItemEntityRepository reportItemEntityRepository;

    private boolean isReplaying;

    @Override
    public void beforeReplay() {
        isReplaying = true;
        reportItemEntityRepository.deleteAll();
    }

    @Override
    public void afterReplay() {
        isReplaying = false;
    }

    @Override
    public void onReplayFailed(Throwable cause) {
        isReplaying =false;
        reportItemEntityRepository.deleteAll();
    }

    @EventHandler
    public void handle(ShoppingCartItemRemovedEvent event, @Timestamp DateTime timeStamp){
        LOG.debug("Handle Shopping cart item removed event [{}]", event);
        if(isReplaying){
            LocalDateTime time = JodaDateTimeConverter.convertToLocalDateTime(timeStamp);

            ReportItemEntity item = new ReportItemEntity(event.getItem(), time);
            reportItemEntityRepository.save(item);
        }
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
