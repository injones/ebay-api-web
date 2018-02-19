package com.example.demo;

import com.ebay.services.finding.ItemFilter;
import com.ebay.services.finding.OutputSelectorType;
import com.example.demo.dto.Filter;
import com.example.demo.dto.OutputType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by JonesIN on 20/01/2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    private String categoryId;
    private String keyword;
    private int pages;
    private int entries;
    private List<Filter> filters;
    private List<OutputType> outputs;

    public Form(String categoryId, String keyword) {
        this.categoryId = categoryId;
        this.keyword = keyword;
    }
}
