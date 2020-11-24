package com.user.login.userlogin.database;

import com.user.login.userlogin.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
