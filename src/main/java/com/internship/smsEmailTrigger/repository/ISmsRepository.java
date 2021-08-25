package com.internship.smsEmailTrigger.repository;

import com.internship.smsEmailTrigger.model.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISmsRepository extends JpaRepository<Sms,Long> {

}
