package nl.ihomer.nextbuild.backend.api.model;

import java.time.LocalDateTime;

/**
 * Created by bvangameren on 27/05/15.
 */
public abstract class ReportItem {

    private String name;
    private LocalDateTime timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportItem)) return false;

        ReportItem item1 = (ReportItem) o;

        return name.equals(item1.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
