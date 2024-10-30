package com.example.dklearn.courses.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties
public class Series {
    private String title;
    private String videoLink;
    private String resourceFile;
}
