package nl.ihomer.nextbuild.backend.domain.events;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public class ShoppingCartItemAddedEvent {

    private final UUID id;
    private final String item;

    public ShoppingCartItemAddedEvent(UUID id, String item) {
        this.id = id;
        this.item = item;
    }

    public UUID getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "ShoppingCartItemAddedEvent{" +
                "id=" + id +
                ", item='" + item + '\'' +
                '}';
    }
}
