package nl.ihomer.nextbuild.backend.api.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by bvangameren on 27/05/15.
 */
public abstract class ShoppingCart {
    private UUID id;
    private String name;
    private LocalDateTime registrationTimestamp;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp(LocalDateTime registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }
}
