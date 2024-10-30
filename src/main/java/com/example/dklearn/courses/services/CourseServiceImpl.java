package com.example.dklearn.courses.services;

import com.example.dklearn.admin.administration.model.Admin;
import com.example.dklearn.admin.administration.repository.AdminRepository;
import com.example.dklearn.admin.auth.AdminUserDetails;
import com.example.dklearn.admin.auth.CustomerUserDetails;
import com.example.dklearn.admin.auth.StaffUserDetails;
import com.example.dklearn.admin.staff.model.Staff;
import com.example.dklearn.admin.staff.repository.StaffRepository;
import com.example.dklearn.admin.user.dto.DekeralutiveUserDto;
import com.example.dklearn.admin.user.model.DekeralutiveUser;
import com.example.dklearn.admin.user.repository.UserRepository;
import com.example.dklearn.card.dto.Customer;
import com.example.dklearn.courses.dto.CoursesDto;
import com.example.dklearn.courses.dto.PageRequestDto;
import com.example.dklearn.courses.dto.SectionDto;
import com.example.dklearn.courses.dto.Series;
import com.example.dklearn.courses.model.CourseCategory;
import com.example.dklearn.courses.model.CourseGroup;
import com.example.dklearn.courses.model.Courses;
import com.example.dklearn.courses.model.Section;
import com.example.dklearn.courses.repository.CourseCategoryRepository;
import com.example.dklearn.courses.repository.CourseGroupRepository;
import com.example.dklearn.courses.repository.CourseRepository;
import com.example.dklearn.courses.repository.SectionRepository;
import com.example.dklearn.courses.resp.CourseResponse;
import com.example.dklearn.customertransaction.model.Transaction;
import com.example.dklearn.response.ResponseDto;
import com.example.dklearn.transaction.repository.TransactionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CourseServiceImpl implements CoursesService {

    @Autowired
    ModelMapper mapper;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    CourseCategoryRepository courseCategoryRepository;
    @Autowired
    CourseGroupRepository courseGroupRepository;

    @Autowired
    StaffRepository staffRepository;
    @Autowired
    MessageSource messageSource;
    @Override
    public CourseResponse addCourses(CoursesDto coursesDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        Courses courses=mapper.map(coursesDto,Courses.class);
        List<Section> sectionDtos=addSection(coursesDto.getSectionDto());
        courses.setSection(sectionDtos);
        courses.setStatus("Pending");
        courses.setTimeCreated(LocalDateTime.now());
        String value=getAdminCurrentuser();
        Admin admin=adminRepository.findByEmail(value);
        if(admin!=null){
            courses.setAdmin(admin);
        }
        courses.setTimeCreated(LocalDateTime.now());
        courseRepository.save(courses);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.add.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    @Override
    public CourseResponse addStaffCourses(CoursesDto coursesDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        Courses courses=mapper.map(coursesDto,Courses.class);
        List<Section> sectionDtos=addSection(coursesDto.getSectionDto());
        courses.setSection(sectionDtos);
        courses.setStatus("Pending");
        courses.setTimeCreated(LocalDateTime.now());
        String value=getCurrentuser();
        Staff staff=staffRepository.findByEmail(value);
        if(staff!=null){
            courses.setStaff(staff);
        }
        courses.setTimeCreated(LocalDateTime.now());
        courseRepository.save(courses);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.add.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    @Override
    public CourseResponse processCourses(CoursesDto coursesDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        Optional<Courses> courses= courseRepository.findById(coursesDto.getId());
       if(courses.isPresent()){
           courses.get().setStatus(coursesDto.getStatus());
       }
       courseRepository.save(courses.get());
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.process.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    @Override
    public CourseResponse deleteCourses(CoursesDto coursesDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        Optional<Courses> courses= courseRepository.findById(coursesDto.getId());

        if(courses.isPresent()){
            if(courses.get().getStatus().equalsIgnoreCase("Approved")){
                responseDto.setCode(messageSource.getMessage("service.error.code",null, Locale.ENGLISH));
                responseDto.setMessage(messageSource.getMessage("courses.deleted.error",null,Locale.ENGLISH));
                courseResponse.setResponseDto(responseDto);
                return courseResponse;
            }
            courses.get().setDelFlag("Y");
            courseRepository.save(courses.get());
            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("courses.deleted.success",null,Locale.ENGLISH));
            courseResponse.setResponseDto(responseDto);
            return courseResponse;
        }
        else{
            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("courses.no.exist",null,Locale.ENGLISH));
            courseResponse.setResponseDto(responseDto);
            return courseResponse;
        }

    }

    @Override
    public CourseResponse allAdminCourses(PageRequestDto pageRequestDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        System.out.println("page request {}"+pageRequestDto.getPageNo());
        System.out.println("page request {}"+pageRequestDto.getPageSize());
        String user=getCurrentuser();
        Admin admin=adminRepository.findByUserName(user);
        Page<Courses> courses= courseRepository.findByAdmin(admin,PageRequest.of(pageRequestDto.getPageNo(), pageRequestDto.getPageSize()));
        List<CoursesDto> coursesDtos= getCourses(courses.getContent());
        List<Courses> courses1=courseRepository.findByAdmin(admin);
        courseResponse.setTotalData(courses1.size());
        courseResponse.setTotalPages(courses1.size()/10);
        courseResponse.setCoursesDtos(coursesDtos);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    @Override
    public CourseResponse updateAdminCourses(CoursesDto coursesDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        Courses courses=mapper.map(coursesDto,Courses.class);
        List<Section> sectionDtos=addSection(coursesDto.getSectionDto());
        courses.setSection(sectionDtos);
        courses.setStatus("Pending");
        courses.setTimeCreated(LocalDateTime.now());
        String adminValue= getAdminCurrentuser();
        Admin admin=adminRepository.findByEmail(adminValue);
        if(admin!=null){
            courses.setAdmin(admin);
        }
        courses.setTimeCreated(LocalDateTime.now());
        courseRepository.save(courses);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.update.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    @Override
    public CourseResponse updateCourses(CoursesDto coursesDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        Optional<Courses> oldCourses=    courseRepository.findById(coursesDto.getId());
        Courses courses=mapper.map(coursesDto,Courses.class);
        List<Section> sectionDtos=addSection(coursesDto.getSectionDto());
        courses.setSection(sectionDtos);
        courses.setStatus("Pending");
        courses.setTimeCreated(LocalDateTime.now());
        String adminValue= getCurrentuser();
        Staff admin=staffRepository.findByEmail(adminValue);
        if(admin!=null){
            courses.setStaff(admin);
        }
        //int value=oldCourses.get().getVersion()+5;
      //  log.info("getting the version {}",value);
        courses.setVersion(oldCourses.get().getVersion());
        //log.info("");
        courses.setTimeCreated(LocalDateTime.now());
        courseRepository.save(courses);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.update.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    public List<Section> addSection(List<SectionDto> sectionDtos){
        try{
            List<Section> sections=new ArrayList<>();
            for(SectionDto sectionDto:sectionDtos){
                ObjectMapper objectMapper = new ObjectMapper();
                String value=objectMapper.writeValueAsString(sectionDto.getSeriesList());
                String series=value;
                Section section= mapper.map(sectionDto,Section.class);
                section.setSeries(series);
                Section section1= sectionRepository.save(section);
                sections.add(section1);
            }
            return sections;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public CourseResponse allTutorCourses(PageRequestDto pageRequestDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        System.out.println("page request {}"+pageRequestDto.getPageNo());
        System.out.println("page request {}"+pageRequestDto.getPageSize());
        String user=getCurrentuser();
        Staff staff=staffRepository.findByUserName(user);
        Page<Courses> courses= courseRepository.findByStaff(staff,PageRequest.of(pageRequestDto.getPageNo(), pageRequestDto.getPageSize()));
        List<CoursesDto> coursesDtos= getCourses(courses.getContent());
        List<Courses> courses1=courseRepository.findByStaff(staff);
        courseResponse.setTotalData(courses1.size());
        courseResponse.setTotalPages(courses1.size()/10);
        courseResponse.setCoursesDtos(coursesDtos);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }


    @Override
    public CourseResponse allStudentCourses(PageRequestDto pageRequestDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        System.out.println("page request {}"+pageRequestDto.getPageNo());
        System.out.println("page request {}"+pageRequestDto.getPageSize());
        String user=getCustomerCurrentuser();

        List<Courses>  coursesList=courseRepository.findByStatus("Approved");
        Page<Courses> courses= courseRepository.findByStatus("Approved",PageRequest.of(pageRequestDto.getPageNo(), pageRequestDto.getPageSize()));
        List<CoursesDto> coursesDtos= getCourses(courses.getContent());
        courseResponse.setTotalData(coursesList.size());
        courseResponse.setTotalPages(coursesList.size()/10);
        courseResponse.setCoursesDtos(coursesDtos);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    @Override
    public CourseResponse allViewStudentCourses(PageRequestDto pageRequestDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        System.out.println("page request {}"+pageRequestDto.getPageNo());
        System.out.println("page request {}"+pageRequestDto.getPageSize());
        List<Courses> coursesList=  courseRepository.findByStatus("Approved");
        Page<Courses> courses= courseRepository.findByStatus("Approved",PageRequest.of(pageRequestDto.getPageNo(), pageRequestDto.getPageSize()));
        log.info("getting the courses {}",courses.getContent().size());
        List<CoursesDto> coursesDtos= getViewCourses(courses.getContent());
        courseResponse.setCoursesDtos(coursesDtos);
        courseResponse.setTotalPages(coursesList.size()/10);
        courseResponse.setTotalData(coursesList.size());
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    @Override
    public CourseResponse updateTutorsName(CoursesDto pageRequestDto) {
        ResponseDto responseDto=new ResponseDto();
        CourseResponse courseResponse=new CourseResponse();

        Optional<Courses>  courses=   courseRepository.findById(pageRequestDto.getId());
        courses.get().setAuthor(pageRequestDto.getAuthor());
        courses.get().setTimeCreated(LocalDateTime.now());
        courseRepository.save(courses.get());
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.update.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    @Override
    public CourseResponse getTotalCourses() {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        List<Courses> courses= courseRepository.findAll();
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        courseResponse.setTotalCourses(courses.size());
        return courseResponse;
    }

    @Override
    public CourseResponse allCourses(PageRequestDto pageRequestDto) {
       CourseResponse courseResponse=new CourseResponse();
       ResponseDto responseDto=new ResponseDto();
       System.out.println("page request {}"+pageRequestDto.getPageNo());
        System.out.println("page request {}"+pageRequestDto.getPageSize());
        List<Courses> coursesList=  courseRepository.findAll();
        Page<Courses> courses= courseRepository.findAll(PageRequest.of(pageRequestDto.getPageNo(), pageRequestDto.getPageSize()));
        List<CoursesDto> coursesDtos= getCourses(courses.getContent());
        courseResponse.setTotalData(coursesList.size());
        courseResponse.setTotalPages(coursesList.size()/10);
        courseResponse.setCoursesDtos(coursesDtos);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }


    @Override
    public CourseResponse allCoursesByCategory(PageRequestDto pageRequestDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        System.out.println("page request {}"+pageRequestDto.getPageNo());
        System.out.println("page request {}"+pageRequestDto.getPageSize());
        List<Courses> coursesList=  courseRepository.findByCourseCategory(pageRequestDto.getCategory());
        Page<Courses> courses= courseRepository.findByCourseCategory(pageRequestDto.getCategory(),PageRequest.of(pageRequestDto.getPageNo(), pageRequestDto.getPageSize()));
        List<CoursesDto> coursesDtos= getCourses(courses.getContent());
        courseResponse.setTotalData(coursesList.size());
        courseResponse.setTotalPages(coursesList.size()/10);
        courseResponse.setCoursesDtos(coursesDtos);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    public String getCustomerCurrentuser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomerUserDetails) {
            String username = ((CustomerUserDetails)principal).getUsername();
            if(username.contains("_")){
                String value []=username.split("_");
                return  value[0];
            }
            return  username;
//            return username;
        } else {
            //  String username = principal.toString();
            return null;
        }
    }

    @Override
    public CourseResponse allCustomerCourses(PageRequestDto pageRequestDto) {
        return null;
    }

    @Override
    public List<CoursesDto> getCourses(List<Courses> courses){
        try{
            List<CoursesDto> series=new ArrayList<>();
            CoursesDto coursesDto=new CoursesDto();
            for(Courses courses1:courses){
               String value= getCustomerCurrentuser();
                DekeralutiveUser user=userRepository.findByUserName(value);
                if(user!=null){
                    coursesDto= validateCourses(courses1,user);
                }else{
                     coursesDto=getCourse(courses1);
                }
                series.add(coursesDto);
            }

            return series;
        }catch (Exception e){
            e.printStackTrace();
        }

        return  null;

    }

    public List<CoursesDto> getViewCourses(List<Courses> courses){
        try{
            List<CoursesDto> coursesDtos=new ArrayList<>();
            CoursesDto coursesDto=new CoursesDto();
            for(Courses courses1:courses){
                List<SectionDto> sectionDtos=new ArrayList<>();

                coursesDto=getCourse(courses1);
                for(SectionDto series:coursesDto.getSectionDto()){
                    List<Series> seriesList=new ArrayList<>();
                    log.info("Entering the series {}",series);
                    if(series!=null && series.getSeriesList()!=null){
                        for(Series series1: series.getSeriesList()){
                            series1.setVideoLink("");
                            seriesList.add(series1);
                            series.setSeriesList(seriesList);


                        }
                    }

                    sectionDtos.add(series);
                }
                coursesDto.setSectionDto(sectionDtos);


                coursesDtos.add(coursesDto);
            }

            return coursesDtos;


        }catch (Exception e){
            e.printStackTrace();
        }

        return  null;

    }


    @Override
    public CourseResponse getAllCourseCategories( String group) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        List<CourseCategory> courseCategory= courseCategoryRepository.findAll();
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        courseResponse.setCourseCategories(courseCategory);
        return courseResponse;
    }

    @Override
    public CourseResponse getAllCourseGroups() {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        List<CourseGroup> courseCategory= courseGroupRepository.findAll();
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        courseResponse.setCourseGroups(courseCategory);
        return courseResponse;
    }

    public String getCurrentuser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof StaffUserDetails) {
            String username = ((StaffUserDetails)principal).getUsername();
            if(username.contains("_")){
                String value []=username.split("_");
                return  value[0];
            }
            return  username;
//            return username;
        } else {
            //  String username = principal.toString();
            return null;
        }
    }


    public String getAdminCurrentuser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AdminUserDetails) {
            String username = ((AdminUserDetails)principal).getUsername();
            if(username.contains("_")){
                String value []=username.split("_");
                return  value[0];
            }
            return  username;
//            return username;
        } else {
            //  String username = principal.toString();
            return null;
        }
    }

    @Override
    public CourseResponse boughtCourses(PageRequestDto pageRequestDto) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        List<DekeralutiveUserDto> dekeralutiveUserDtos=new ArrayList<>();
        BigDecimal totalAmount=new BigDecimal(0);

        try{

            log.info("bought courses for student");

            List<Transaction> transactions=  transactionRepository.findByCourseId(pageRequestDto.getId());
            Page<Transaction> transactions1=transactionRepository.findByCourseId(pageRequestDto.getId(),PageRequest.of(pageRequestDto.getPageNo(), pageRequestDto.getPageSize()));
            for(Transaction transaction: transactions1){
                log.info("Entry getting the transaction details {}",transactions1);
                DekeralutiveUser user=userRepository.findByUserName(transaction.getEmailAddress());
                if(user!=null){
                    DekeralutiveUserDto dto= mapper.map(user, DekeralutiveUserDto.class);
                    dekeralutiveUserDtos.add(dto);

                    if(transaction.getAmount()!=null){
                        dto.setAmount(transaction.getAmount());
                        totalAmount=totalAmount.add(transaction.getAmount());
                    }
                }
                courseResponse.setUsers(dekeralutiveUserDtos);
                courseResponse.setTotalEarnings(totalAmount);
                courseResponse.setTotalData(transactions.size());
                courseResponse.setTotalPages(transactions.size()/10);
                responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
                responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
                courseResponse.setResponseDto(responseDto);
            }
            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
            courseResponse.setResponseDto(responseDto);
            courseResponse.setUsers(dekeralutiveUserDtos);
            return courseResponse;
        }catch (Exception e){
            e.printStackTrace();
            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
            courseResponse.setResponseDto(responseDto);
            courseResponse.setUsers(dekeralutiveUserDtos);

            return courseResponse;
        }


    }

    @Override
    public CourseResponse getCourses(PageRequestDto pageRequestDto) {
        return null;
    }

    @Override
    public CourseResponse getViewSingleCourse(Long id) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        Optional<Courses> courses=courseRepository.findById(id);
        String value=  getCustomerCurrentuser();
        CoursesDto coursesDto=  getCourse(courses.get());
        courseResponse.setCoursesDto(coursesDto);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }


    @Override
    public CourseResponse getSingleForTutorCourse(Long id) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        Optional<Courses> courses=courseRepository.findById(id);
        String value=  getCustomerCurrentuser();
        Staff user=staffRepository.findByUserName(value);

        if(courses.isPresent()){
            if(user!=null){
                CoursesDto coursesDto= validateTutorCourses(courses.get(),user);
                courseResponse.setCoursesDto(coursesDto);

            }
            else
            {
                CoursesDto coursesDto=getUnvalidateCourses(courses.get());
                courseResponse.setCoursesDto(coursesDto);
            }
        }

        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }



    @Override
    public CourseResponse getSingleCourse(Long id) {
        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        Optional<Courses> courses=courseRepository.findById(id);
         String value=  getCustomerCurrentuser();
         DekeralutiveUser user=userRepository.findByUserName(value);

        if(courses.isPresent()){
            if(user!=null){
                CoursesDto coursesDto= validateCourses(courses.get(),user);
                courseResponse.setCoursesDto(coursesDto);

            }
            else
            {
                CoursesDto coursesDto=getUnvalidateCourses(courses.get());
                courseResponse.setCoursesDto(coursesDto);
            }
        }

        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    private boolean checkIfPurchased(Courses courses,DekeralutiveUser user){
        boolean checkPurchase=false;
        for(Courses courses1:user.getCoursesList()){
            if(courses1.getId().equals(courses.getId())){
                checkPurchase=true;
            }
        }
        return checkPurchase;
    }

    private CoursesDto validateTutorCourses(Courses courses,Staff user){
        boolean result =false;

        if(courses.getStaff().getUserName().equals(user.getUserName())){
            result=true;
        }
        if(!result){

            CoursesDto coursesDto=  getCourse(courses);
            List<SectionDto> sectionDtos=new ArrayList<>();
            for(SectionDto series:coursesDto.getSectionDto()){
                List<Series> seriesList=new ArrayList<>();
                for(Series series1: series.getSeriesList()){
                    series1.setVideoLink("");
                    seriesList.add(series1);

                }
                series.setSeriesList(seriesList);
                sectionDtos.add(series);
            }
            coursesDto.setPurchased(result);
            coursesDto.setSectionDto(sectionDtos);
            return coursesDto;
        }


        else {
            return getCourse(courses);
        }

    }


    private CoursesDto validateCourses(Courses courses,DekeralutiveUser user){
        boolean result =false;
        for(Courses courses1 : user.getCoursesList()){
            if(courses.getId().equals(courses1.getId())){
                result=true;
            }
        }

        if(!result){

            CoursesDto coursesDto=  getCourse(courses);
            List<SectionDto> sectionDtos=new ArrayList<>();
            for(SectionDto series:coursesDto.getSectionDto()){
                List<Series> seriesList=new ArrayList<>();
                for(Series series1: series.getSeriesList()){
                    series1.setVideoLink("");
                    seriesList.add(series1);

                }
                series.setSeriesList(seriesList);
                sectionDtos.add(series);
            }
            coursesDto.setPurchased(result);
            coursesDto.setSectionDto(sectionDtos);
            return coursesDto;
        }


        else {
           return getCourse(courses);
        }

    }

    private CoursesDto getUnvalidateCourses(Courses courses){
        CoursesDto coursesDto=  getCourse(courses);
        List<SectionDto> sectionDtos=new ArrayList<>();
        for(SectionDto series:coursesDto.getSectionDto()){
            List<Series> seriesList=new ArrayList<>();
            for(Series series1: series.getSeriesList()){
                series1.setVideoLink("");
                seriesList.add(series1);

            }
            series.setSeriesList(seriesList);
            sectionDtos.add(series);
        }
        coursesDto.setPurchased(false);
        coursesDto.setSectionDto(sectionDtos);
        return coursesDto;
    }

    @Override
    public CourseResponse searchCourses(PageRequestDto pageRequestDto) {

        CourseResponse courseResponse=new CourseResponse();
        ResponseDto responseDto=new ResponseDto();
        log.info("The result of the content {}",pageRequestDto.getSearch());
        List<Courses> coursesList=  courseRepository.searchCourses(pageRequestDto.getSearch());
        log.info("Courses Content {}",coursesList.size());
      //  Page<Courses> courses= courseRepository.findByCourseCategory(pageRequestDto.getCategory(),PageRequest.of(pageRequestDto.getPageNo(), pageRequestDto.getPageSize()));
        List<CoursesDto> coursesDtos= getCourses(coursesList);
        courseResponse.setTotalData(coursesList.size());
        courseResponse.setTotalPages(coursesList.size()/10);
        courseResponse.setCoursesDtos(coursesDtos);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("courses.fetch.success",null,Locale.ENGLISH));
        courseResponse.setResponseDto(responseDto);
        return courseResponse;
    }

    public CoursesDto getCourse(Courses courses){
        try{
                CoursesDto coursesDto= mapper.map(courses,CoursesDto.class);
                List<SectionDto> sections=new ArrayList<>();
                for(Section section:courses.getSection()){
                    SectionDto sectionDto= mapper.map(section,SectionDto.class);
                    List<Series> seriesList = objectMapper.readValue(section.getSeries(), new TypeReference<List<Series>>() {
                    });
                    sectionDto.setSeriesList(seriesList);
                    sections.add(sectionDto);
                }
                coursesDto.setSectionDto(sections);
            return coursesDto;
        }catch (Exception e){
            e.printStackTrace();
        }

        return  null;

    }

}


