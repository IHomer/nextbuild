package nl.ihomer.nextbuild.backend.view.persistence;

import nl.ihomer.nextbuild.backend.api.model.ShoppingCartItem;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by bvangameren on 09/01/15.
 */

@Entity
public class ShoppingCartItemEntity extends ShoppingCartItem {

    private UUID id;


    public ShoppingCartItemEntity() {
    }

    public ShoppingCartItemEntity(String name) {
        super();
        super.setName(name);
    }

    public ShoppingCartItemEntity(String name, LocalDateTime timestamp) {
        super();
        super.setName(name);
        super.setTimestamp(timestamp);
    }

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getId(){
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return super.getName();
    }


    @Override
    public LocalDateTime getTimestamp() {
        return super.getTimestamp();
    }
}
