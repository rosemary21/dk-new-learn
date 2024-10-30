package com.example.dklearn.admin.auth;


import com.example.dklearn.logout.service.LogoutService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    private static final Logger LOGGER= LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    private StaffUserDetailsService staffUserDetailsService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    AdminUserDetailsService adminUserDetailsService;

    @Autowired
    LogoutService logoutService;

//    @Autowired
//    CustomerLoginServiceImpl customerLoginService;

    @Autowired
    private ModelMapper mm;

    private String secretKey;

    public Authentication getAuthentication(String token){
        UserDetails userDetails=this.staffUserDetailsService.loadUserByUsername(getUsername(token));
        return  new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }


    public Authentication getAuthenticationAdmin(String token){
        UserDetails userDetails=this.adminUserDetailsService.loadUserByUsername(getUsername(token));
        return  new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    public Authentication getAuthenticationCustomer(String token){
        log.info("getting the username <><><>???<><>{}",getUsername(token));
        CustomerUserDetails userDetails=this.customUserDetailsService.loadUserByUsername(getUsername(token));
        return  new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }



    public UserDetails getAuthenticate(String token){
        log.info("getting the token {}",token);
        UserDetails userDetails=this.staffUserDetailsService.loadUserByUsername(getUsername(token));

        return  userDetails;
    }



    private String getUsername(String token){
        secretKey= Base64.getEncoder().encodeToString("key".getBytes());
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public JwtResponse validateToken(String token){
        try{
            log.info("getting the token {}",token);
            secretKey= Base64.getEncoder().encodeToString("key".getBytes());
            Jws<Claims> claimsJws=Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            JwtResponse jwtResponse=new JwtResponse();
            jwtResponse.setRole(claimsJws.getBody().getIssuer());
            jwtResponse.setResult(true);
            jwtResponse.setBvn(claimsJws.getBody().getId());
            log.info("getting the claims {}",claimsJws.getBody().getExpiration());
            boolean tokenResult=logoutService.validateLoginToken(token);
            if(tokenResult){
                jwtResponse.setResult(false);
            }
            return jwtResponse;
        }
        catch(JwtException | IllegalArgumentException exception){
            exception.printStackTrace();
            JwtResponse jwtResponse=new JwtResponse();
            jwtResponse.setResult(false);
            return  jwtResponse;
        }
    }





    public JwtResponse expireToken(String token){
        try{
            secretKey= Base64.getEncoder().encodeToString("key".getBytes());
            Jws<Claims> claimsJws=Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            JwtResponse jwtResponse=new JwtResponse();
            jwtResponse.setRole(claimsJws.getBody().getIssuer());
            claimsJws.getBody().setExpiration(new Date()).getSubject();
            //jwtResponse.setResult(true);
            log.info("getting the claims {}",claimsJws.getBody().getExpiration());
            return jwtResponse;
        }
        catch(JwtException  | IllegalArgumentException exception){
            JwtResponse jwtResponse=new JwtResponse();
            jwtResponse.setResult(false);
            return  jwtResponse;
        }
    }





}
