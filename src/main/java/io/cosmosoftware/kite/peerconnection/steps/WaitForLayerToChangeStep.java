package io.cosmosoftware.kite.peerconnection.steps;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.peerconnection.pages.SVCPage;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestStep;
import io.cosmosoftware.kite.util.ReportUtils;

import static io.cosmosoftware.kite.entities.Timeouts.*;
import static io.cosmosoftware.kite.entities.Timeouts.ONE_SECOND_INTERVAL;
import static io.cosmosoftware.kite.util.TestUtils.waitAround;

public class WaitForLayerToChangeStep extends TestStep {

  private final Coordinator coordinator;
  private final String layer;

  public WaitForLayerToChangeStep(Runner runner, String layer, Coordinator coordinator) {
    super(runner);
    this.layer = layer;
    this.coordinator = coordinator;
  }

  @Override
  protected void step() throws KiteTestException {
    for (int wait = 0; wait < DEFAULT_TIMEOUT; wait += ONE_SECOND_INTERVAL) {
      waitAround(ONE_SECOND_INTERVAL / 3);
      if (coordinator.hasChangedToLayer(layer)) {
        return;
      }
    }
    throw new KiteTestException("Timeout before changing layer", Status.FAILED);

  }

  @Override
  public String stepDescription() {
    return "Waited to change to layer " + this.layer;
  }

}
