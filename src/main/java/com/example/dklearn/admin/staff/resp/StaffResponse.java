package com.example.dklearn.admin.staff.resp;


import com.example.dklearn.admin.staff.dto.StaffDto;
import com.example.dklearn.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StaffResponse {
    private ResponseDto responseDto;
    private List<StaffDto> staffDtoList;
    private StaffDto staffDto;
}
