package com.example.dklearn.courses.resp;

import com.example.dklearn.admin.user.dto.DekeralutiveUserDto;
import com.example.dklearn.courses.dto.CoursesDto;
import com.example.dklearn.courses.model.CourseCategory;
import com.example.dklearn.courses.model.CourseGroup;
import com.example.dklearn.courses.model.Courses;
import com.example.dklearn.response.ResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse {

    private ResponseDto responseDto;
    private List<Courses> coursesList;
    private List<CourseGroup> courseGroups;
    private List<CourseCategory> courseCategories;
    private List<CoursesDto> coursesDtos;
    private List<DekeralutiveUserDto> users;
    private CoursesDto coursesDto;
    private BigDecimal totalEarnings;
    private int totalCourses;
    private int totalPages;
    private int totalData;

}
