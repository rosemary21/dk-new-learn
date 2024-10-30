package com.example.dklearn.courses.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageRequestDto {
    private Integer pageSize;
    private Integer pageNo;
    private Long staffId;
    private Long courseId;
    private String category;
    private String search;
    private String id;
}
