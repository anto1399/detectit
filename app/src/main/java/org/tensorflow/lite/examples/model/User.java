package org.tensorflow.lite.examples.model;

public class User {

    private int id;
    private String name;
    private String contact;
    private String password;

    public User() {
    }

    public User(String name, String contact, String password) {
        this.name = name;
        this.contact = contact;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
