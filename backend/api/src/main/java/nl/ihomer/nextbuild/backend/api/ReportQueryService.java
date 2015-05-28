package nl.ihomer.nextbuild.backend.api;

import nl.ihomer.nextbuild.backend.api.model.ReportItem;

import java.util.List;

/**
 * Created by bvangameren on 26/05/15.
 */
public interface ReportQueryService {
    List<ReportItem> findAll();
}
