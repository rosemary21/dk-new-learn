package com.example.dklearn.admin.user.resp;


import com.example.dklearn.admin.user.dto.DekeralutiveUserDto;
import com.example.dklearn.courses.dto.CoursesDto;
import com.example.dklearn.courses.model.Courses;
import com.example.dklearn.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserResponse {

    private ResponseDto responseDto;
    private DekeralutiveUserDto userDto;
    private List<DekeralutiveUserDto> userDtoList;
    private List<CoursesDto> courses;
}
