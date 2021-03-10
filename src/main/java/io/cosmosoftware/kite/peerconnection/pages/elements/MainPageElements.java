package io.cosmosoftware.kite.peerconnection.pages.elements;


import io.cosmosoftware.kite.peerconnection.pages.Page;

import java.util.HashMap;

public class MainPageElements extends PageElements {

    // WINDOWS============================================================
    private String connectBtn = "/Window/Button";
    private String idLabel = "/Window/List/ListItem[2]";
    private String closeBtn = "/Window/TitleBar/Button[3]";

    public MainPageElements() {

    }

    public HashMap<String, String> populateElement(String os) {
        HashMap<String, String> elements = new HashMap<>();

        if (os.equals("WINDOWS")) {
            getWindowsElements(elements);
        } else if (os.equals("MAC")) {
            getMacElements(elements);
        } else {
            getLinuxElements(elements);
        }
        return elements;
    }

    protected void getWindowsElements(HashMap<String, String> elements) {
        elements.put(PageElements.MAIN_PAGE_ID_LABEL, this.idLabel);
        elements.put(PageElements.MAIN_PAGE_CLOSE_BTN, this.closeBtn);
        elements.put(PageElements.MAIN_PAGE_CONNECT_BTN, this.connectBtn);
    }

    protected void getMacElements(HashMap<String, String> elements) {
    }

    protected void getLinuxElements(HashMap<String, String> elements) {
    }

}
