package com.example.dklearn.admin.staff.service;


import com.example.dklearn.admin.staff.dto.StaffDto;
import com.example.dklearn.admin.staff.resp.StaffResponse;
import com.example.dklearn.admin.user.dto.ChangePasswordDto;

public interface StaffService {

    StaffResponse addStaff(StaffDto staffDto);
    StaffResponse getStaff();
    StaffResponse updateStaff(StaffDto staffDto);
    StaffResponse deleteStaff(StaffDto staffDto);
    StaffResponse allStaff();
    StaffResponse resetPassword(ChangePasswordDto resetPasswordParam);

    StaffResponse changePassword(ChangePasswordDto resetPasswordParam);

}
