package com.example.dklearn.courses.repository;

import com.example.dklearn.courses.model.CourseCategory;
import com.example.dklearn.courses.model.CourseGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {

    List<CourseCategory> findByCode(String code);
}
