package com.example.dklearn.admin.administration.resp;


import com.example.dklearn.admin.administration.dto.AdminDto;
import com.example.dklearn.admin.staff.dto.StaffDto;
import com.example.dklearn.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AdminResponse {
    private ResponseDto responseDto;
    private List<AdminDto> staffDtoList;
    private AdminDto staffDto;
}
