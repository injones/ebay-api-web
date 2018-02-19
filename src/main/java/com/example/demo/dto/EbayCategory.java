package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by JonesIN on 28/01/2018.
 */
@Data
@NoArgsConstructor
public class EbayCategory {
    private String name;
    private String id;
    private List<EbayCategory> subCategories;
}
