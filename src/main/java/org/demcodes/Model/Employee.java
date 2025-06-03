package org.demcodes.Model;

public class Employee {


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", isActif=" + isActif +
                ", email='" + email + '\'' +
                ", isSuscribe=" + isSuscribe +
                '}';
    }
    public  Employee(){};

    public Employee(String employeeId ,String name, String firstName, String password, String email) {
        this.employeeId = employeeId;
        this.name = name;
        this.firstName = firstName;
        this.password = password;
        this.isActif = true;
        this.email = email;

    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActif() {
        return isActif;
    }

    public void setActif(boolean actif) {
        isActif = actif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSuscribe() {
        return isSuscribe;
    }

    public void setSuscribe(boolean suscribe) {
        isSuscribe = suscribe;
    }
String employeeId;
    private String name;
    private String firstName;
    private String password;
    private boolean isActif;
    private  String email;
    private boolean isSuscribe;
}
