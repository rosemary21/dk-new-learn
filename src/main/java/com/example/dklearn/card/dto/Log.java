package com.example.dklearn.card.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Log {

    private long start_time;
    private long time_spent;
    private long attempts;
    private long errors;
    private boolean success;
    private boolean mobile;


}
