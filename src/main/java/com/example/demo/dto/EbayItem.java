package com.example.demo.dto;

import com.ebay.services.finding.Amount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * Created by JonesIN on 21/01/2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EbayItem {
    private String title;
    private String url;
    private String startDate;
    private String endDate;
    private double price;
    private String img;

}
