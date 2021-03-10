package io.cosmosoftware.kite.peerconnection.tests;

import static io.cosmosoftware.kite.util.TestUtils.readJsonFile;
import io.cosmosoftware.kite.exception.KiteTestException;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.webrtc.kite.WebDriverFactory;
import org.webrtc.kite.config.client.Client;
import org.webrtc.kite.config.paas.Paas;


public class DevTest {

    public static void main(String[] args) throws MalformedURLException, KiteTestException {
        Client client = new Client(readJsonFile("\\GitHub\\KITE-PeerConnection-Medooze-Test\\configs\\config.json")
                .getJsonArray("clients").getJsonObject(1));
        client.setPaas(new Paas("http://192.168.3.25:4444/wd/hub"));
        WebDriver driver = WebDriverFactory.createWebDriver(client, "", "");
        System.out.println("Created");
        driver.quit();
    }
}
