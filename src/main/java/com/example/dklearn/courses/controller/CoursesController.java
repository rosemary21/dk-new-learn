package com.example.dklearn.courses.controller;

import com.example.dklearn.courses.dto.CoursesDto;
import com.example.dklearn.courses.dto.PageRequestDto;
import com.example.dklearn.courses.resp.CourseResponse;
import com.example.dklearn.courses.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dl/api/v1/course", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CoursesController {

    @Autowired
    CoursesService coursesService;
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)

    public ResponseEntity<CourseResponse>  addCourses(@RequestBody  CoursesDto coursesDto){
        CourseResponse courseResponse=coursesService.addCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/tutoraddcourses" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  addStaffCourses(@RequestBody  CoursesDto coursesDto){
        CourseResponse courseResponse=coursesService.addStaffCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/updateadmin" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  updateAmdminCourses(@RequestBody  CoursesDto coursesDto){
        CourseResponse courseResponse=coursesService.updateAdminCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  updateCourses(@RequestBody  CoursesDto coursesDto){
        CourseResponse courseResponse=coursesService.updateCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }



    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)

    public ResponseEntity<CourseResponse>  deleteCourses(@RequestBody  CoursesDto coursesDto){
        CourseResponse courseResponse=coursesService.deleteCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }



    @RequestMapping(value = "/process" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  processCourses(@RequestBody  CoursesDto coursesDto){
        CourseResponse courseResponse=coursesService.processCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/allcoursegroups" ,method = RequestMethod.GET)
    public ResponseEntity<CourseResponse>  allCoursesGroups(){
        CourseResponse courseResponse=coursesService.getAllCourseGroups();
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/allcourseCategory/{group}" ,method = RequestMethod.GET)
    public ResponseEntity<CourseResponse>  allCoursesCategory(@PathVariable String  group){
        CourseResponse courseResponse=coursesService.getAllCourseCategories(group);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/all" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  allCourses(@RequestBody PageRequestDto coursesDto){
        CourseResponse courseResponse=coursesService.allCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/alladmincourses" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  allAdminCourses(@RequestBody PageRequestDto coursesDto){
        CourseResponse courseResponse=coursesService.allCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/allcategory" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  allCoursesByCategory(@RequestBody PageRequestDto coursesDto){
        CourseResponse courseResponse=coursesService.allCoursesByCategory(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/totalcourse" ,method = RequestMethod.GET)
    public ResponseEntity<CourseResponse>  getTotalCourse(){
        CourseResponse courseResponse=coursesService.getTotalCourses();
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/all/student/courses" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  allStudentCourses(@RequestBody PageRequestDto coursesDto){
        CourseResponse courseResponse=coursesService.allStudentCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/search" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  searchCourses(@RequestBody PageRequestDto coursesDto){
        CourseResponse courseResponse=coursesService.searchCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }




    @RequestMapping(value = "/all/view/student/courses" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  allStudentExposedCourses(@RequestBody PageRequestDto coursesDto){
        CourseResponse courseResponse=coursesService.allViewStudentCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }



    @RequestMapping(value = "/singlecourse/{id}" ,method = RequestMethod.GET)
    public ResponseEntity<CourseResponse>  getSingleCourse(@PathVariable Long id){
        CourseResponse courseResponse=coursesService.getSingleCourse(id);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/singletutorcourse/{id}" ,method = RequestMethod.GET)
    public ResponseEntity<CourseResponse>  getSingleTutorCourse(@PathVariable Long id){
        CourseResponse courseResponse=coursesService.getSingleForTutorCourse(id);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }




    @RequestMapping(value = "/view/singlecourse" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  getViewSingleCourse(@RequestBody PageRequestDto coursesDto){
        CourseResponse courseResponse=coursesService.getViewSingleCourse(coursesDto.getCourseId());
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/alltutor" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  allTutorCourses(@RequestBody PageRequestDto coursesDto){
        CourseResponse courseResponse=coursesService.allTutorCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/updatetutorname" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  updateTutorName(@RequestBody CoursesDto coursesDto){
        CourseResponse courseResponse=coursesService.updateTutorsName(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/allcustomer" ,method = RequestMethod.POST)

    public ResponseEntity<CourseResponse>  allCustomerCourses(@RequestBody PageRequestDto coursesDto){
        CourseResponse courseResponse=coursesService.allTutorCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/boughtcourse" ,method = RequestMethod.POST)
    public ResponseEntity<CourseResponse>  boughtCourses(@RequestBody PageRequestDto coursesDto){
        CourseResponse courseResponse=coursesService.boughtCourses(coursesDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<CourseResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }

}
