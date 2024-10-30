package com.example.dklearn.courses.repository;

import com.example.dklearn.admin.administration.model.Admin;
import com.example.dklearn.admin.staff.model.Staff;
import com.example.dklearn.courses.dto.PageRequestDto;
import com.example.dklearn.courses.model.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Stack;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {

    Page<Courses> findByStaffAndStatus(Staff staff,String status, Pageable pageable);
    Page<Courses> findByStaff(Staff staff, Pageable pageable);
    Page<Courses> findByAdmin(Admin admin, Pageable pageable);

    List<Courses> findByStaff(Staff staff);
    List<Courses> findByAdmin(Admin admin);

    List<Courses> findByStatus(String status);
    Page<Courses> findByStatus(String status,Pageable pageable);
    List<Courses> findByCourseCategory(String courseCategory);

    Page<Courses> findByCourseCategory(String courseCategory, Pageable pageable);


    @Query("SELECT u FROM Courses u where u.title LIKE  %:pattern% or u.author like  %:pattern%")
    List<Courses> searchCourses(@Param(value = "pattern") String pattern);

}
