package com.example.dklearn.images.dto;

import com.example.dklearn.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class ImageReponse {
    private ResponseDto responseDto;
    private String url;
}
