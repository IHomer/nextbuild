package nl.ihomer.nextbuild.backend.domain.events;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public class ShoppingCartRejectedEvent {

    private final UUID id;

    public ShoppingCartRejectedEvent(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ShoppingCartRejectedEvent{" +
                "id=" + id +
                '}';
    }
}
