package io.cosmosoftware.kite.peerconnection.tests;

import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.checks.ParticipantLeftCheck;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.peerconnection.steps.*;
import io.cosmosoftware.kite.peerconnection.checks.LocalVideoDisplayCheck;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.webrtc.kite.tests.KiteBaseTest;
import org.webrtc.kite.tests.TestRunner;

import javax.json.JsonArray;
import java.util.ArrayList;
import java.util.List;

public class PeerConnectionTest extends KiteBaseTest {

    protected Coordinator coordinator;
    protected List<String> layers = new ArrayList<>();

    @Override
    protected void payloadHandling() {
        super.payloadHandling();
        this.coordinator = new Coordinator(this.tupleSize);
        layers.add("Layer S2 T2");
        if (payload.get("layers") != null) {
          JsonArray layersArray = payload.getJsonArray("layers");
          if (!layersArray.isEmpty()) {
            layers.clear();
            for (int i = 0; i < layersArray.size(); i++) {
              this.layers.add(layersArray.getString(i));
            }
          }
        }
    }

    @Override
    protected void populateTestSteps(TestRunner testRunner) {
       if(isApp(testRunner.getWebDriver())) {
         testRunner.addStep(new JoinRoomStep(testRunner, this.coordinator));
         for (String layer : this.layers) {
           testRunner.addStep(new WaitForLayerToChangeStep(testRunner, layer, this.coordinator));
           testRunner.addStep(new LocalVideoDisplayCheck(testRunner, layer, this.coordinator));
         }
         testRunner.addStep(new QuitApplicationStep(testRunner, this.coordinator));
       } else {
         testRunner.addStep(new JoinRoomSVCStep(testRunner, this.coordinator, this.url));
         for (String layer : this.layers) {
           testRunner.addStep(new ChangeLayerStep(testRunner, layer, this.coordinator));
           testRunner.addStep(new WaitForScreenshotStep(testRunner, layer, this.coordinator));
         }
       }
    }

  private boolean isApp(WebDriver webDriver) {
    return ((RemoteWebDriver) webDriver).getCapabilities().getBrowserName().equals("app");
  }
}
