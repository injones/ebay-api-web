package com.example.demo.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by JonesIN on 26/01/2018.
 */
@Data
public class Filter {
    private String paramName;
    private String paramVal;
    private String value;
    private String delimiter;
    private FilterName type;
}
