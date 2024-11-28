package com.avfinal.model;

public class SessionObject {

    private String id;
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SessionObject(String id, User user) {
        this.id = id;
        this.user = user;
    }
}
