package com.user.login.userlogin.bean.additional;

import java.util.List;

public class UserPreview {
    private int id;
    private String username;
    private List<String> role;
    private String name;
    public UserPreview(int id, String username,String name,List<String> role) {
        this.id = id;
        this.username = username;
        this.name=name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
