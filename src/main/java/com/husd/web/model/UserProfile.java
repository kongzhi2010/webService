package com.husd.web.model;

import java.io.Serializable;

public class UserProfile implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4917983021137202885L;

    private String username;

    private String password;

    private String email;

    public UserProfile() {}

    public UserProfile(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("user detail is :username:%s,password:%s,email:%s", username, password,
                email);
    }

}
