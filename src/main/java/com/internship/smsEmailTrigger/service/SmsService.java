package com.internship.smsEmailTrigger.service;

import com.internship.smsEmailTrigger.model.Sms;
import com.internship.smsEmailTrigger.repository.ISmsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SmsService implements ISmsService{


    private final ISmsRepository smsRepository;

    public SmsService(ISmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    @Override
    public Sms getSms(Long id) {
//       List<Sms> smsList= new ArrayList<>();
//       smsRepository.findAll().forEach(sms -> {
//           smsList.add(new Sms(sms.getId(),sms.getSent()));
//       })

        return smsRepository.findById(id).orElseGet(Sms::new);
    }
    @Override
    public List<Sms> getAllSms(){
        return smsRepository.findAll();
    }
}
