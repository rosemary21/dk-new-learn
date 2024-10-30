package com.example.dklearn.admin.administration.service;


import com.example.dklearn.admin.administration.dto.AdminDto;
import com.example.dklearn.admin.administration.resp.AdminResponse;
import com.example.dklearn.admin.staff.dto.StaffDto;
import com.example.dklearn.admin.user.dto.ChangePasswordDto;

public interface AdminService {

    AdminResponse addAdmin(AdminDto staffDto);
    AdminResponse getAdmin();
    AdminResponse updateAdmin(AdminDto staffDto);
    AdminResponse deleteAdmin(AdminDto staffDto);
    AdminResponse allAdmin();
    AdminResponse resetPassword(ChangePasswordDto resetPasswordParam);

    AdminResponse changePassword(ChangePasswordDto resetPasswordParam);

}
