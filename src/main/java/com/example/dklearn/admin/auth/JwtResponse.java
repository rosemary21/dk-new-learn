package com.example.dklearn.admin.auth;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {
    private boolean result;
    private String role;
    private String bvn;
}
