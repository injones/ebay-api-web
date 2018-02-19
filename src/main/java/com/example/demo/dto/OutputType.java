package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Created by JonesIN on 27/01/2018.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OutputType {
    SELLER_INFO("SellerInfo"),
    STORE_INFO("StoreInfo"),
    CATEGORY_HISTOGRAM("CategoryHistogram"),
    ASPECT_HISTOGRAM("AspectHistogram"),
    CONDITION_HISTOGRAM("ConditionHistogram");

    private final String value;

    OutputType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
