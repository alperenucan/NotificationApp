package com.internship.smsEmailTrigger.service;

import com.internship.smsEmailTrigger.model.Email;
import com.internship.smsEmailTrigger.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    @Autowired
    private final EmailRepository emailRepository;

    @Override
    public Email getEmail(Long id) {
       return emailRepository.findById(id).orElseGet(Email::new);
    }

    @Override
    public List<Email> getAll() {
        return emailRepository.findAll();
    }
}
