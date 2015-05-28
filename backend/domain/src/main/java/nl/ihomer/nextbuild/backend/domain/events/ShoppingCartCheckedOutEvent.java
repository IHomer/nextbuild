package nl.ihomer.nextbuild.backend.domain.events;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public class ShoppingCartCheckedOutEvent {

    private final UUID id;

    public ShoppingCartCheckedOutEvent(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ShoppingCartCheckedOutEvent{" +
                "id=" + id +
                '}';
    }
}
