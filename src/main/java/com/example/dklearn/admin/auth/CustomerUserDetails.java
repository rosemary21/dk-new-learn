package com.example.dklearn.admin.auth;


import com.example.dklearn.admin.user.model.DekeralutiveUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerUserDetails implements UserDetails {

    //private String emailAddress;
    private String password;
    private List<String> permissions;
    private String phoneNumber;
    private String bvn;
    private DekeralutiveUser customer;

    public CustomerUserDetails(String phoneNumber, String password, List<String> permissions) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuth = new ArrayList<>();
        for(String p : permissions){
            grantedAuth.add(new SimpleGrantedAuthority(p));
        }
        return grantedAuth;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.phoneNumber;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
