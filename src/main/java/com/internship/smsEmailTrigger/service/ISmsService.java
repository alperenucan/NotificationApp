package com.internship.smsEmailTrigger.service;

import com.internship.smsEmailTrigger.model.Sms;

import java.util.List;


public interface ISmsService {
    Sms getSms(Long id);

    List<Sms> getAllSms();


}
