package com.example.dklearn.courses.services;

import com.example.dklearn.courses.dto.CoursesDto;
import com.example.dklearn.courses.dto.PageRequestDto;
import com.example.dklearn.courses.model.Courses;
import com.example.dklearn.courses.resp.CourseResponse;

import java.util.List;

public interface CoursesService {

    CourseResponse addCourses(CoursesDto coursesDto);
    CourseResponse addStaffCourses(CoursesDto coursesDto);
    CourseResponse deleteCourses(CoursesDto coursesDto);
    CourseResponse updateAdminCourses(CoursesDto coursesDto);
    CourseResponse updateCourses(CoursesDto coursesDto);
    CourseResponse processCourses(CoursesDto coursesDto);
    List<CoursesDto> getCourses(List<Courses> courses);
    CourseResponse boughtCourses(PageRequestDto pageRequestDto);
    CourseResponse allViewStudentCourses(PageRequestDto pageRequestDto);
    CourseResponse allStudentCourses(PageRequestDto pageRequestDto);
    CourseResponse allCourses(PageRequestDto pageRequestDto);
    CourseResponse allCoursesByCategory(PageRequestDto pageRequestDto);
    CourseResponse allAdminCourses(PageRequestDto pageRequestDto);
    CourseResponse getAllCourseCategories( String group);
    CourseResponse getAllCourseGroups();

    CourseResponse allTutorCourses(PageRequestDto pageRequestDto);
    CourseResponse allCustomerCourses(PageRequestDto pageRequestDto);
    CourseResponse getCourses(PageRequestDto pageRequestDto);
    CourseResponse getSingleCourse(Long id);
    CourseResponse searchCourses(PageRequestDto pageRequestDto);
    CourseResponse getSingleForTutorCourse(Long id);
    CourseResponse getViewSingleCourse(Long id);
    CourseResponse getTotalCourses();
    CourseResponse updateTutorsName(CoursesDto pageRequestDto);




}
