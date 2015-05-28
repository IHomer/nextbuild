package nl.ihomer.nextbuild.backend.domain.events;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public class ShoppingCartItemRemovedEvent {

    private final UUID id;
    private final String item;

    public ShoppingCartItemRemovedEvent(UUID id, String item) {
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
        return "ShoppingCartItemRemovedEvent{" +
                "id=" + id +
                ", item='" + item + '\'' +
                '}';
    }
}
