package com.example.dklearn.admin;


import com.example.dklearn.admin.staff.model.Staff;
import com.example.dklearn.admin.staff.repository.StaffRepository;
import com.example.dklearn.admin.staff.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Commandline implements CommandLineRunner {

    @Autowired
    StaffService service;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Entry getting the staff");
        Staff dto=new Staff();
        if(staffRepository.findByEmail("chukeluchioma408@yahoo.com")==null){
            log.info("Entry Staff Details");
            dto.setFullName("Chioma");
            dto.setUserName("chukeluchioma408@yahoo.com");
            dto.setEmail("chukeluchioma408@yahoo.com");
            dto.setPasswordhash(passwordEncoder.encode("rosechi"));
            staffRepository.save(dto);
        }
    }


}
