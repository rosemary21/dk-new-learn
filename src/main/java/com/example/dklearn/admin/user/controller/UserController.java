package com.example.dklearn.admin.user.controller;


import com.example.dklearn.admin.administration.resp.AdminResponse;
import com.example.dklearn.admin.user.dto.ChangePasswordDto;
import com.example.dklearn.admin.user.dto.DekeralutiveUserDto;
import com.example.dklearn.admin.user.resp.UserResponse;
import com.example.dklearn.admin.user.service.UserService;
import com.example.dklearn.customertransaction.dto.TransactionDto;
import com.example.dklearn.customertransaction.resp.TransactionResponse;
import com.example.dklearn.customertransaction.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/dl/api/v1/user", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PaymentMethodService paymentMethodService;

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public ResponseEntity<UserResponse> addUser(@RequestBody DekeralutiveUserDto dto) {
        UserResponse responseDto= userService.addUser(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    public ResponseEntity<UserResponse> updateUser(@RequestBody DekeralutiveUserDto dto) {
        UserResponse responseDto= userService.updateUser(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }



    @RequestMapping(value = "/allcourses" ,method = RequestMethod.GET)
    public ResponseEntity<UserResponse> allCourses() {
        UserResponse responseDto= userService.getUserCourses();
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    public ResponseEntity<UserResponse> deleteUser(@RequestBody DekeralutiveUserDto dto) {
        UserResponse responseDto= userService.deleteUser(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/all" ,method = RequestMethod.GET)
    public ResponseEntity<UserResponse> allUser() {
        UserResponse responseDto= userService.getAllUser();
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/resetpassword" ,method = RequestMethod.POST)
    public ResponseEntity<UserResponse> resetPassword(@RequestBody ChangePasswordDto dto) {
        UserResponse responseDto= userService.resetPassword(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/changepassword" ,method = RequestMethod.POST)
    public ResponseEntity<UserResponse> changePassword(@RequestBody ChangePasswordDto dto) {
        UserResponse responseDto= userService.changePassword(dto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping(value = "/all/page" ,method = RequestMethod.POST)
//    public ResponseEntity<UserResponse> getAllUsersByPage(@RequestBody PageDescription pageDescription) {
//        UserResponse responseDto= userService.getAllUsersByPage(pageDescription);
//        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
//            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<UserResponse>(responseDto, HttpStatus.BAD_REQUEST);
//    }

    
}
