package io.cosmosoftware.kite.peerconnection.steps;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.steps.TestStep;

import static io.cosmosoftware.kite.entities.Timeouts.ONE_SECOND_INTERVAL;
import static io.cosmosoftware.kite.entities.Timeouts.THREE_SECOND_INTERVAL;
import static io.cosmosoftware.kite.util.TestUtils.waitAround;

public class JoinRoomStep extends TestStep {

    private final Coordinator coordinator;
    private final MainPage mainPage;

    /**
     * Instantiates a new Test step.
     *
     * @param runner the runner
     */
    public JoinRoomStep(Runner runner, Coordinator coordinator) {
        super(runner);
        mainPage = new MainPage(runner);
        this.coordinator = coordinator;
    }

    @Override
    protected void step() throws KiteTestException {
        waitAround(THREE_SECOND_INTERVAL);
        this.coordinator.setRoomId(mainPage.getRoomId());
        waitAround(ONE_SECOND_INTERVAL);
        mainPage.joinRoom();
    }

    @Override
    public String stepDescription() {
        return "Join room";
    }
}
