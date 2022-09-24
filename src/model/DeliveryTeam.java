package model;

public class DeliveryTeam {
    private String teamID;
    private String teamName;
    private String teamContact;
    private String teamAddress;

    public DeliveryTeam() {
    }

    public DeliveryTeam(String teamID, String teamName, String teamContact, String teamAddress) {
        this.setTeamID(teamID);
        this.setTeamName(teamName);
        this.setTeamContact(teamContact);
        this.setTeamAddress(teamAddress);
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
}
