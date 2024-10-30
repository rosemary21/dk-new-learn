package com.example.dklearn.courses.repository;

import com.example.dklearn.courses.model.CourseGroup;
import com.example.dklearn.courses.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseGroupRepository extends JpaRepository<CourseGroup, Long> {

}
