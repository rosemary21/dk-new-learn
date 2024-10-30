package com.example.dklearn.admin.administration.controller;


import com.example.dklearn.admin.administration.dto.AdminDto;
import com.example.dklearn.admin.administration.resp.AdminResponse;
import com.example.dklearn.admin.administration.service.AdminService;
import com.example.dklearn.admin.staff.dto.StaffDto;
import com.example.dklearn.admin.staff.resp.StaffResponse;
import com.example.dklearn.admin.user.dto.ChangePasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dl/api/v1/admin", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AdminController {


    @Autowired
    AdminService userService;

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public ResponseEntity<AdminResponse> addUser(@RequestBody AdminDto dto) {
        AdminResponse responseDto= userService.addAdmin(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/adminid" ,method = RequestMethod.GET)
    public ResponseEntity<AdminResponse> getStaff() {
        AdminResponse responseDto= userService.getAdmin();
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    public ResponseEntity<AdminResponse> updateUser(@RequestBody AdminDto dto) {
        AdminResponse responseDto= userService.updateAdmin(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/resetpassword" ,method = RequestMethod.POST)
    public ResponseEntity<AdminResponse> resetPassword(@RequestBody ChangePasswordDto dto) {
        AdminResponse responseDto= userService.resetPassword(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/changepassword" ,method = RequestMethod.POST)
    public ResponseEntity<AdminResponse> changePassword(@RequestBody ChangePasswordDto dto) {
        AdminResponse responseDto= userService.changePassword(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    public ResponseEntity<AdminResponse> deleteUser(@RequestBody AdminDto dto) {
        AdminResponse responseDto= userService.deleteAdmin(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/all" ,method = RequestMethod.GET)
    public ResponseEntity<AdminResponse> allUser() {
        AdminResponse responseDto= userService.allAdmin();
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<AdminResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }





}
