package com.user.login.userlogin.controller;
import com.user.login.userlogin.bean.User;
import com.user.login.userlogin.bean.UserRoles;
import com.user.login.userlogin.bean.additional.UserCapture;
import com.user.login.userlogin.bean.additional.UserPreview;
import com.user.login.userlogin.database.MyRepository;
import com.user.login.userlogin.exception.UserAlreadyExists;
import com.user.login.userlogin.controller.additional.Additional;
import com.user.login.userlogin.jwt.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class MyController {


    @Autowired
    private Additional additional;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MyRepository myRepository;

//    @PostConstruct
//    public void init() {
//        User user=new User();
//        Collection<UserRoles> li=new ArrayList<>();
//        UserRoles role1=new UserRoles();
//        role1.setRole("USER");
//        li.add(role1);
//        user.setName("sivasanker");
//        user.setPassword(bCryptPasswordEncoder.encode("siva123456"));
//        user.setUsername("dukenukem97");
//        user.setUserRoles(li);
//        myRepository.save(user);
//    }

    @GetMapping("/api/users")
    public List<UserPreview> allUsers() throws Exception {
        List<User> users=myRepository.findAll();
        List<UserPreview> allTheUsers=new ArrayList<>();
        for(User u:users) {
            List<String> roles=additional.getRoles(u);
            UserPreview preview=additional.userPreview(u,roles);
            allTheUsers.add(preview);
        }
        return allTheUsers;
    }
    @GetMapping("/api/users/{name}")
    public UserPreview theUser(@PathVariable("name") String name) throws Exception {
        User user = myRepository.findByUsername(name.toLowerCase());
        if(user==null)
            return null;
        List<String> roles=additional.getRoles(user);
        UserPreview preview=additional.userPreview(user,roles);
        return preview;
    }
    @PostMapping("/api/users")
    public Object addUser(@RequestBody UserCapture userCapture) throws AuthenticationException{
        String error=additional.errorCheck(userCapture);
        if(error!=null)
            return error;
        User exists=myRepository.findByUsername(userCapture.getUsername());
        if(exists !=null) throw new UserAlreadyExists() ;
        User user=additional.addToDB(userCapture);
        Collection<UserRoles> roles=user.getUserRoles();
        List<String> theRoles=roles.stream().map(role->role.getRole()).collect(Collectors.toList());
        myRepository.save(user);
        return additional.userPreview(user,theRoles);
    }

}
