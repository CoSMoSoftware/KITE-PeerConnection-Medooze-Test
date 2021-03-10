package io.cosmosoftware.kite.peerconnection.checks;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.steps.TestStep;
import io.cosmosoftware.kite.util.ReportUtils;
import static io.cosmosoftware.kite.entities.Timeouts.ONE_SECOND_INTERVAL;
import static io.cosmosoftware.kite.util.TestUtils.videoCheckByBytes;
import static io.cosmosoftware.kite.util.TestUtils.waitAround;

public class LocalVideoDisplayCheck extends TestStep {
    private final Runner runner;
    private final MainPage mainPage;
    private final Coordinator coordinator;

    private final String layer;
    public LocalVideoDisplayCheck(Runner runner, String layer, Coordinator coordinator) {
        super(runner);
        this.runner = runner;
        this.mainPage = new MainPage(runner);
        this.coordinator = coordinator;
        this.layer = layer;
    }

    @Override
    protected void step() throws KiteTestException {
        waitAround(ONE_SECOND_INTERVAL);
        String videoCheck = videoCheckByBytes(webDriver);
        this.reporter.screenshotAttachment(this.report, this.getClassName(), ReportUtils
                .saveScreenshotPNG(this.webDriver));
        this.reporter.textAttachment(this.report, "Video Check", videoCheck, "plain");
        this.coordinator.addScreenshot(layer);
    }

    @Override
    public String stepDescription() {
        return "Video checking";
    }
}

