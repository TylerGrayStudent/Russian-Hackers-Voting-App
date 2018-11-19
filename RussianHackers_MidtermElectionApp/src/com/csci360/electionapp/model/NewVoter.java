package com.csci360.electionapp.model;

public class NewVoter {
    public String userName;
    public String firstName;
    public String lastName;
    public String password;
    public String address;
    public String zipCode;
    public String ssn;
    public String dlNumber;

    public NewVoter(String userName, String firstName, String lastName, String password, String address, String zipCode, String ssn, String dlNumber) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.zipCode = zipCode;
        this.ssn = ssn;
        this.dlNumber = dlNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getSsn() {
        return ssn;
    }

    public String getDlNumber() {
        return dlNumber;
    }
}
