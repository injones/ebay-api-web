package com.example.demo.controller;

import com.example.demo.Form;
import com.example.demo.dto.EbayItem;
import com.example.demo.service.EbayCat;
import com.example.demo.service.IEbayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by JonesIN on 20/01/2018.
 */
@RestController
public class EbayController {

    @Autowired
    private IEbayService service;
    @Autowired
    private EbayCat cat;

    @RequestMapping(path = "/api/search")
    public List<EbayItem> search(@RequestBody Form form){
        System.out.println(form.toString());
        return service.search(form);
//        return service.search(new Form("58058", "raspberry pi 3"));
    }

    @RequestMapping("/api/cat")
    public void cat() throws IOException {
        cat.test();
    }
}
