package io.cosmosoftware.kite.peerconnection.pages.elements;


import java.util.HashMap;

public class MainPageElements extends PageElements {

    // WINDOWS============================================================
    private String idLabel = "/Window/List/ListItem[2]";
    private String closeBtn = "/Window/TitleBar/Button[3]";

    // MAC================================================================
    private String addressInputMac = "/Window/Window[5]/Group/Button";
    private String portInputMac = "/Window/Window[5]/Group/Button";
    private String connectInputMac = "/Window/Window[5]/Group/Button";

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
    }

    protected void getMacElements(HashMap<String, String> elements) {
    }

    protected void getLinuxElements(HashMap<String, String> elements) {
    }

}
