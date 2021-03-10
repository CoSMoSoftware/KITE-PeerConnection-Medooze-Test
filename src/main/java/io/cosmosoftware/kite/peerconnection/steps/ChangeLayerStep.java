package io.cosmosoftware.kite.peerconnection.steps;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.peerconnection.pages.SVCPage;
import io.cosmosoftware.kite.steps.TestStep;
import io.cosmosoftware.kite.util.ReportUtils;

import static io.cosmosoftware.kite.entities.Timeouts.THREE_SECOND_INTERVAL;
import static io.cosmosoftware.kite.util.TestUtils.waitAround;

public class ChangeLayerStep extends TestStep {

  private final SVCPage svcPage;
  private final String layer;
  private final Coordinator coordinator;


  public ChangeLayerStep(Runner runner, String layer, Coordinator coordinator) {
    super(runner);
    this.svcPage = new SVCPage(runner);
    this.layer = layer;
    this.coordinator = coordinator;
  }

  @Override
  protected void step() throws KiteTestException {
    try {
      waitAround(THREE_SECOND_INTERVAL);
      svcPage.setLayer(this.layer);
      this.coordinator.addLayer(this.layer);
      this.reporter.screenshotAttachment(this.report, this.getClassName(), ReportUtils
              .saveScreenshotPNG(this.webDriver));
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public String stepDescription() {
    return "Change codec to : " + this.layer;
  }

}
