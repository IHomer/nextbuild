package nl.ihomer.nextbuild.backend.domain.events;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
public class ShoppingCartRegisteredEvent {

    private final UUID id;
    private final String name;

    public ShoppingCartRegisteredEvent(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ShoppingCartRegisteredEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
