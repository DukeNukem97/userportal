package com.user.login.userlogin.bean;

import javax.persistence.*;

@Entity
@Table(name = "User_Roles")
public class UserRoles {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "role_name")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
