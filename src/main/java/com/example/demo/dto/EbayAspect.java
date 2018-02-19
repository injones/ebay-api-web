package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by JonesIN on 28/01/2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EbayAspect {
    private String name;
    private List<String> values;
}
