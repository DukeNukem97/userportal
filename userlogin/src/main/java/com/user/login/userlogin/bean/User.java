package com.user.login.userlogin.bean;

import org.springframework.beans.factory.annotation.Autowired;
import com.sun.istack.NotNull;
import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "User_Credentials")
public class User {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Size(min = 6)
    @Column(name = "Username",nullable = false,length = 255,unique = true)
    private String username;
    @NotNull
    @Size(min = 8)
    @Column(name = "Password",nullable = false,length = 255)
    private String password;
    @NotNull
    @Size(min = 4)
    @Column(name = "Name",nullable = false,length = 255)
    private String name;
    @Autowired
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Collection<UserRoles> userRoles=new ArrayList<>();
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }
}
