package io.cosmosoftware.kite.peerconnection.tests;

import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.checks.ParticipantLeftCheck;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.peerconnection.steps.ConnectClientStep;
import io.cosmosoftware.kite.peerconnection.steps.JoinRoomSVCStep;
import io.cosmosoftware.kite.peerconnection.steps.JoinRoomStep;
import io.cosmosoftware.kite.peerconnection.checks.LocalVideoDisplayCheck;
import io.cosmosoftware.kite.peerconnection.steps.QuitApplicationStep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.webrtc.kite.tests.KiteBaseTest;
import org.webrtc.kite.tests.TestRunner;

public class PeerConnectionTest extends KiteBaseTest {

    protected Coordinator coordinator;

    @Override
    protected void payloadHandling() {
        super.payloadHandling();
        this.coordinator = new Coordinator(this.tupleSize);
    }

    @Override
    protected void populateTestSteps(TestRunner testRunner) {
       if(isApp(testRunner.getWebDriver())) {
         testRunner.addStep(new JoinRoomStep(testRunner, this.coordinator));
         testRunner.addStep(new LocalVideoDisplayCheck(testRunner));
         testRunner.addStep(new QuitApplicationStep(testRunner, this.coordinator));
//       } else {
//         testRunner.addStep(new JoinRoomSVCStep(testRunner, this.coordinator));
       }
    }

  private boolean isApp(WebDriver webDriver) {
    return ((RemoteWebDriver) webDriver).getCapabilities().getBrowserName().equals("app");
  }
}
