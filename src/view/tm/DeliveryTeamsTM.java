package view.tm;

import javafx.scene.control.Button;

public class DeliveryTeamsTM {
    private String teamID;
    private String teamName;
    private String teamContact;
    private String teamAddress;
    private Button button1;
    private Button button2;

    public DeliveryTeamsTM() {
    }

    public DeliveryTeamsTM(String teamID, String teamName, String teamContact, String teamAddress, Button button1, Button button2) {
        this.setTeamID(teamID);
        this.setTeamName(teamName);
        this.setTeamContact(teamContact);
        this.setTeamAddress(teamAddress);
        this.setButton1(button1);
        this.setButton2(button2);
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamContact() {
        return teamContact;
    }

    public void setTeamContact(String teamContact) {
        this.teamContact = teamContact;
    }

    public String getTeamAddress() {
        return teamAddress;
    }

    public void setTeamAddress(String teamAddress) {
        this.teamAddress = teamAddress;
    }

    public Button getButton1() {
        return button1;
    }

    public void setButton1(Button button1) {
        this.button1 = button1;
    }

    public Button getButton2() {
        return button2;
    }

    public void setButton2(Button button2) {
        this.button2 = button2;
    }
}
