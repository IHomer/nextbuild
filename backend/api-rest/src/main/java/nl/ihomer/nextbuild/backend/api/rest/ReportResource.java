package nl.ihomer.nextbuild.backend.api.rest;

import nl.ihomer.nextbuild.backend.api.ReportCommandService;
import nl.ihomer.nextbuild.backend.api.ReportQueryService;
import nl.ihomer.nextbuild.backend.api.ShoppingCartCommandService;
import nl.ihomer.nextbuild.backend.api.ShoppingCartQueryService;
import nl.ihomer.nextbuild.backend.api.model.ReportItem;
import nl.ihomer.nextbuild.backend.api.model.ShoppingCart;
import nl.ihomer.nextbuild.backend.api.rest.messages.RegisterShoppingCartRequest;
import nl.ihomer.nextbuild.backend.api.rest.messages.ShoppingCartItemRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/report")
public class ReportResource {

    private static final Logger LOG = LoggerFactory.getLogger(ReportResource.class);

    @Autowired
    private ReportCommandService reportCommandService;

    @Autowired
    private ReportQueryService reportQueryService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> generateReport() {
        LOG.debug("Generate report");
        reportCommandService.generateReport();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getReport() {
        LOG.debug("Get report");
        List<ReportItem> items = reportQueryService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
