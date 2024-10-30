package com.example.dklearn.admin.staff.controller;


import com.example.dklearn.admin.staff.dto.StaffDto;
import com.example.dklearn.admin.staff.resp.StaffResponse;
import com.example.dklearn.admin.staff.service.StaffService;
import com.example.dklearn.admin.user.dto.ChangePasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dl/api/v1/staff", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StaffController {


    @Autowired
    StaffService userService;

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public ResponseEntity<StaffResponse> addUser(@RequestBody StaffDto dto) {
        StaffResponse responseDto= userService.addStaff(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/staffid" ,method = RequestMethod.GET)
    public ResponseEntity<StaffResponse> getStaff() {
        StaffResponse responseDto= userService.getStaff();
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    public ResponseEntity<StaffResponse> updateUser(@RequestBody StaffDto dto) {
        StaffResponse responseDto= userService.updateStaff(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/resetpassword" ,method = RequestMethod.POST)
    public ResponseEntity<StaffResponse> resetPassword(@RequestBody ChangePasswordDto dto) {
        StaffResponse responseDto= userService.resetPassword(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/changepassword" ,method = RequestMethod.POST)
    public ResponseEntity<StaffResponse> changePassword(@RequestBody ChangePasswordDto dto) {
        StaffResponse responseDto= userService.changePassword(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    public ResponseEntity<StaffResponse> deleteUser(@RequestBody StaffDto dto) {
        StaffResponse responseDto= userService.deleteStaff(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/all" ,method = RequestMethod.GET)
    public ResponseEntity<StaffResponse> allUser() {
        StaffResponse responseDto= userService.allStaff();
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<StaffResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }





}
