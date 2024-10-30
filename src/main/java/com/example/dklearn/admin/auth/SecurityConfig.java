package com.example.dklearn.admin.auth;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{


        httpSecurity.headers().cacheControl();

        httpSecurity.csrf().disable()
                .authorizeRequests();
        httpSecurity.cors();

        httpSecurity.httpBasic().disable()
               // .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/dl/api/v1/staff/add").permitAll()
                .antMatchers("/dl/api/v1/admin/add").permitAll()
                .antMatchers("/dl/api/v1/login/*").permitAll()
                .antMatchers("/dl/api/v1/login/*/*").permitAll()
                .antMatchers("/dl/api/v1/user/add").permitAll()
                .antMatchers("/dl/api/v1/otp/request").permitAll()
                .antMatchers("/dl/api/v1/otp/authenticate").permitAll()
                .antMatchers("/dl/api/v1/user/add").permitAll()
                .antMatchers("/dl/api/v1/otp/request").permitAll()
                .antMatchers("/dl/api/v1/user/resetpassword").permitAll()
                .antMatchers("/dl/api/v1/admin/resetpassword").permitAll()
                .antMatchers("/dl/api/v1/staff/resetpassword").permitAll()
                .antMatchers("/dl/api/v1/bvn/verify").permitAll()
                .antMatchers("/dl/api/v1/course/all").permitAll()
                .antMatchers("/dl/api/v1/bvn").permitAll()
                .antMatchers("/dl/api/v1/user/add").permitAll()
                .antMatchers("/dl/api/v1/customer/update").permitAll()
                .antMatchers("/dl/api/v1/password/mobile/forgot-password").permitAll()
                .antMatchers("/dl/api/v1/productdescription/category/location").permitAll()
                .antMatchers("/dl/api/v1/productdescription/id").permitAll()
                .antMatchers("/dl/api/v1/productdescription/search").permitAll()
                .antMatchers("/dl/api/v1/course/view/singlecourse").permitAll()
                .antMatchers("/dl/api/v1/course/search").permitAll()
                .antMatchers("/dl/api/v1/course/all/view/student/courses").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }






}
