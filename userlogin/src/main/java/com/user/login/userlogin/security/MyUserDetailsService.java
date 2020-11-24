package com.user.login.userlogin.security;

import com.user.login.userlogin.bean.User;
import com.user.login.userlogin.database.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    protected MyRepository myRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=myRepository.findByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("User Not Found");
        return new MyUserDetails(user);
    }
}
