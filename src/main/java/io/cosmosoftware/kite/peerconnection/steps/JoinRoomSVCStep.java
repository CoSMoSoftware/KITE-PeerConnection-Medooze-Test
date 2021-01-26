package io.cosmosoftware.kite.peerconnection.steps;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.peerconnection.pages.SVCPage;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestStep;

import static io.cosmosoftware.kite.entities.Timeouts.*;
import static io.cosmosoftware.kite.util.TestUtils.waitAround;

public class JoinRoomSVCStep extends TestStep {

    private final Coordinator coordinator;
    private final SVCPage SVCPage;

    /**
     * Instantiates a new Test step.
     *
     * @param runner the runner
     */
    public JoinRoomSVCStep(Runner runner, Coordinator coordinator) {
        super(runner);
        SVCPage = new SVCPage(runner);
        this.coordinator = coordinator;
    }

    @Override
    protected void step() throws KiteTestException {
        for (int elapsed = 0; elapsed < DEFAULT_TIMEOUT; elapsed += ONE_SECOND_INTERVAL) {
            if (!coordinator.getRoomId().equals("none")) {
                SVCPage.joinRoom(this.coordinator.getRoomId());
                return;
            }
            waitAround(ONE_SECOND_INTERVAL);
        }
        throw new KiteTestException("Couldn't get the id before timeout", Status.FAILED);
    }

    @Override
    public String stepDescription() {
        return "Join SVC room";
    }
}
