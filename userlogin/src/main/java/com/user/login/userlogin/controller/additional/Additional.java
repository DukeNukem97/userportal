package com.user.login.userlogin.controller.additional;
import com.user.login.userlogin.bean.User;
import com.user.login.userlogin.bean.UserRoles;
import com.user.login.userlogin.bean.additional.UserCapture;
import com.user.login.userlogin.bean.additional.UserPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class Additional {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public String errorCheck(UserCapture userCapture){
        String error=null;
        if(userCapture.getUsername()==null)
            error="Username required";
        else if(userCapture.getPassword()==null)
            error="Password Required";
        else if(userCapture.getName()==null)
            error="Name Required";
        return error;
    }
    public User addToDB(UserCapture userCapture)
    {
        UserRoles roles=new UserRoles();
        roles.setRole("USER");
        List<UserRoles> rolesList=new ArrayList<>();
        rolesList.add(roles);
        User user=new User();
        user.setUsername(userCapture.getUsername().toLowerCase());
        user.setPassword(bCryptPasswordEncoder.encode(userCapture.getPassword()));
        user.setName(userCapture.getName());
        user.setUserRoles(rolesList);
        return user;
    }
    public List<String> getRoles(User user)
    {
        return (
                user.getUserRoles()
                        .stream()
                        .map(role->role.getRole())
                        .collect(Collectors.toList()));
    }
    public UserPreview userPreview(User user, List<String> roles)
    {
        return (new UserPreview(
                user.getId(),user.getUsername(),user.getName(),roles));
    }
}
