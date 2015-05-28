package nl.ihomer.nextbuild.backend.domain;

import nl.ihomer.nextbuild.backend.api.ReportCommandService;
import nl.ihomer.nextbuild.backend.api.ShoppingCartCommandService;
import nl.ihomer.nextbuild.backend.domain.commands.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.replay.ReplayingCluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by bvangameren on 26/05/15.
 */
@Service
public class ReportCommandServiceImpl implements ReportCommandService {

    @Autowired
    private ReplayingCluster replayingCluster;

    @Override
    public void generateReport() {
        replayingCluster.startReplay();
    }
}
