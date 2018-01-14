//package main.java.Application;
package Application;


import javafx.beans.property.*;

public class Person {
    private final SimpleStringProperty contactId;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty email;

    public Person(String contactId, String firstName, String secondName, String phone, String email) {
        this.contactId = new SimpleStringProperty(contactId);
        this.firstName = new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
    }

    public String getContactId() {
        return contactId.get();
    }

    public void setContactId(String contactId) {
        this.contactId.set(contactId);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
