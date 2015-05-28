package nl.ihomer.nextbuild.backend.report.persistence;

import nl.ihomer.nextbuild.backend.api.model.ReportItem;
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
public class ReportItemEntity extends ReportItem {

    private UUID id;

    public ReportItemEntity() {
    }

    public ReportItemEntity(String name) {
        super();
        super.setName(name);
    }

    public ReportItemEntity(String name, LocalDateTime timestamp) {
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
