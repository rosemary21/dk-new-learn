package com.example.dklearn.courses.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class CoursesDto {

    private String courseGroup;
    private String courseCategory;
    private String title;
    private String description;
    private String courseImageUrl;
    private String author;
    private BigDecimal nairaPrice;
    private LocalDateTime timeCreated;
    private String status;
    private boolean purchased;
//    private BigDecimal gdbPrice;
//    private BigDecimal usdPrice;
//    private BigDecimal euroPrice;
    private String courseVideoUrl;
    private List<SectionDto> sectionDto;
    private Long id;
}
