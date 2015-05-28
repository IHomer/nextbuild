package nl.ihomer.nextbuild.backend.report.services;

import nl.ihomer.nextbuild.backend.api.ReportQueryService;
import nl.ihomer.nextbuild.backend.api.model.ReportItem;
import nl.ihomer.nextbuild.backend.report.persistence.ReportItemEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bvangameren on 28/05/15.
 */
@Service
public class ReportQueryServiceImpl implements ReportQueryService {

    @Autowired
    private ReportItemEntityRepository reportItemEntityRepository;

    @Override
    public List<ReportItem> findAll() {
        List<ReportItem> reportItems = new ArrayList<>();
        reportItems.addAll(reportItemEntityRepository.findAll());
        return reportItems;
    }
}
