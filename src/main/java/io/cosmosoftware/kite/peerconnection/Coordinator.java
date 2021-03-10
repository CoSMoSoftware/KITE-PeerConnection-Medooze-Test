package io.cosmosoftware.kite.peerconnection;

import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.report.AllureTestReport;
import io.cosmosoftware.kite.report.Reporter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Coordinator {

    private final int participantCount;
    private String roomId = "none";
    private HashMap<Runner, String> participantMap = new HashMap<>();
    private List<String> participants = new ArrayList<>();
    private List<String> layers = new ArrayList<>();
    private List<String> screenshots = new ArrayList<>();

    public Coordinator(int participantCount) {
        this.participantCount = participantCount;
    }

    public synchronized String getRoomId() {
        return roomId;
    }

    public synchronized void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public synchronized boolean allParticipantsConnected() {
        return this.participants.size() == this.participantCount;
    }

    public synchronized int getParticipantSize() { return this.participants.size(); }

    public synchronized void addParticipant(Runner runner, String id) {
        this.participantMap.put(runner, id);
        this.participants.add(id);
    }
    public synchronized  void removeParticipant(Runner runner, String id) {
        this.participantMap.remove(runner, id);
        this.participants.remove(id);
    }

    public synchronized void addLayer(String layer) {
        this.layers.add(layer);
    }

    public synchronized void addScreenshot(String layer) {
        this.screenshots.add(layer);
    }

    public boolean hasScreenshotTaken(String layer) {
        return this.screenshots.contains(layer);
    }

    public boolean hasChangedToLayer(String layer) {
        return this.layers.contains(layer);
    }
}
