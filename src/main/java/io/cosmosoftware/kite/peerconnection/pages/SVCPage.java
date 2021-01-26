package io.cosmosoftware.kite.peerconnection.pages;

import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SVCPage extends BasePage {

  @FindBy(id="connect")
  private WebElement connect_btn;

  @FindBy(id="peerId")
  private WebElement id_input;

  public SVCPage(Runner runner) {
    super(runner);
  }


  public void joinRoom(String id) {
    id_input.sendKeys(id);
    connect_btn.click();
  }
}
