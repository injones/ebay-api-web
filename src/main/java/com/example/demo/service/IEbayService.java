package com.example.demo.service;

import com.example.demo.Form;
import com.example.demo.dto.EbayItem;

import java.util.List;

/**
 * Created by JonesIN on 20/01/2018.
 */
public interface IEbayService {
    List<EbayItem> search(Form form);
}
