package com.example.dklearn.images.repository;

import com.example.dklearn.courses.model.Courses;
import com.example.dklearn.images.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Images, Long> {
}
