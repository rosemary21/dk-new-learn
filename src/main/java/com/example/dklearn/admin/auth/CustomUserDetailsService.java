package com.example.dklearn.admin.auth;


import com.example.dklearn.admin.user.model.DekeralutiveUser;
import com.example.dklearn.admin.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Martins on 04/16/2022.
 */


@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ObjectMapper om;

    @Override
    public CustomerUserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        log.info("getting the email address <><> {}",phoneNumber);
        String [] values= phoneNumber.split("_");
        String part1="";
        if(values.length>1){
            part1= values[0];
            String part2=values[1];
        }else{
            part1=phoneNumber;
        }
        Objects.requireNonNull(phoneNumber, "Phone Number cannot be null");
//        Customer user = userRepository.findFirstByPhoneNumberOrEmailAndStatus(phoneNumber,phoneNumber, "A");
        DekeralutiveUser customer=userRepository.findByUserName(part1);

       // log.info("getting the customer user details {}",customer);
        if(customer == null){
            throw new UsernameNotFoundException("No user found with email address: "+ phoneNumber + ". If user exists, contact administrator as account might have been disabled");
        }
        return new CustomerUserDetails(customer.getUserName(), customer.getPasswordhash(), new ArrayList<>());
    }
    public DekeralutiveUser getCurrentuser() {
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("getting the response for client response data {}",om.writeValueAsString(principal));
            log.info("getting the principal {}",principal);
            if (principal instanceof CustomerUserDetails) {
                CustomerUserDetails username = ((CustomerUserDetails) principal);
                log.info("getting the response for username response data {}",om.writeValueAsString(username));
                String userName="";
                if(username.getUsername().contains("_")){
                    String value []=username.getUsername().split("_");
                    userName= value[0];
                }else{

                    userName=username.getUsername();
                    log.info("Entry getting the username <><> {}",userName);
                }
                DekeralutiveUser customer = userRepository.findByUserName(userName);
                return customer;
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }


}
