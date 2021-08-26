package com.internship.smsEmailTrigger.repository;

import com.internship.smsEmailTrigger.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
