package com.internship.smsEmailTrigger.service;

import com.internship.smsEmailTrigger.model.Email;

import java.util.List;

public interface IEmailService {

    Email getEmail(Long id);

    List<Email> getAll();

}
