package com.example.dklearn.courses.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SectionDto {

    private String title;

    private List<Series> seriesList;
}
