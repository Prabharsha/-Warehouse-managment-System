package model;

public class Employee {
    private String employeeNIC;
    private String employeeName;
    private String employeeAddress;
    private String employeeContact;
    private String employeeJobType;

    public Employee() {
    }

    public Employee(String employeeNIC, String employeeName, String employeeAddress, String employeeContact, String employeeJobType) {
        this.setEmployeeNIC(employeeNIC);
        this.setEmployeeName(employeeName);
        this.setEmployeeAddress(employeeAddress);
        this.setEmployeeContact(employeeContact);
        this.setEmployeeJobType(employeeJobType);
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
}
