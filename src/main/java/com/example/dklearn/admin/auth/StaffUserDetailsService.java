package com.example.dklearn.admin.auth;


import com.example.dklearn.admin.staff.model.Staff;
import com.example.dklearn.admin.staff.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class StaffUserDetailsService  implements UserDetailsService {

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Objects.requireNonNull(emailAddress, "Email address cannot be null");

        Staff user = staffRepository.findByEmail(emailAddress);
        System.out.println("getting the user details {}"+user);
//        CustomerUser user = userRepository.findByEmailAddress(emailAddress);
        if(user == null){
            throw new UsernameNotFoundException("No user found with email address: "+ emailAddress + ". If user exists, contact administrator as account might have been disabled");
        }
        return new StaffUserDetails(user.getEmail(), user.getPasswordhash(), new ArrayList<>());
    }


}


