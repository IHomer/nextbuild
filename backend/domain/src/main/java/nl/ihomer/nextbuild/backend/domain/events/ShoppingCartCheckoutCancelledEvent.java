package nl.ihomer.nextbuild.backend.domain.events;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public class ShoppingCartCheckoutCancelledEvent {

    private final UUID id;

    public ShoppingCartCheckoutCancelledEvent(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ShoppingCartCheckoutCancelledEvent{" +
                "id=" + id +
                '}';
    }
}
