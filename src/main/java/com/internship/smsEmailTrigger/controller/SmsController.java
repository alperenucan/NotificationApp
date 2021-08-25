package com.internship.smsEmailTrigger.controller;

import com.internship.smsEmailTrigger.model.Sms;
import com.internship.smsEmailTrigger.service.ExcelReaderService;
import com.internship.smsEmailTrigger.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class SmsController {

    @Autowired
    private final ExcelReaderService excelReaderService;

    @Autowired
    private SmsService smsService;

    @GetMapping("/{id}")
    @ResponseBody
    public Long sendSMS(@RequestParam List<Sms> id){
        return null;
    }
}
