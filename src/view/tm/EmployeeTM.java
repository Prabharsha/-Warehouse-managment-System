package view.tm;

import javafx.scene.control.Button;

public class EmployeeTM {
    private String employeeNIC;
    private String employeeName;
    private String employeeAddress;
    private String employeeContact;
    private String employeeJobType;
    private Button button1;
    private  Button button2;

    public EmployeeTM() {
    }

    public EmployeeTM(String employeeNIC, String employeeName, String employeeAddress, String employeeContact, String employeeJobType,  Button button1, Button button2) {
        this.setEmployeeNIC(employeeNIC);
        this.setEmployeeName(employeeName);
        this.setEmployeeAddress(employeeAddress);
        this.setEmployeeContact(employeeContact);
        this.setEmployeeJobType(employeeJobType);
        this.setButton1(button1);
        this.setButton2(button2);
    }

    public String getEmployeeNIC() {
        return employeeNIC;
    }

    public void setEmployeeNIC(String employeeNIC) {
        this.employeeNIC = employeeNIC;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(String employeeContact) {
        this.employeeContact = employeeContact;
    }

    public String getEmployeeJobType() {
        return employeeJobType;
    }

    public void setEmployeeJobType(String employeeJobType) {
        this.employeeJobType = employeeJobType;
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
