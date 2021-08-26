package com.internship.smsEmailTrigger.controller;

import com.internship.smsEmailTrigger.model.Sms;
import com.internship.smsEmailTrigger.service.ExcelReaderService;
import com.internship.smsEmailTrigger.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class SmsController {


    @Autowired
    private SmsService smsService;

    @GetMapping("/{id}")
    public ResponseEntity<Long> sendSMS(@PathVariable Long id){
        Sms sms = smsService.getSms(id);
        return ResponseEntity.ok(sms.getId());
    }
}
