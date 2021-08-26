package com.internship.smsEmailTrigger.model;

import com.poiji.annotation.ExcelCell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "email")
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @Id
    @Column(name="id",nullable = false)
    @ExcelCell(0)
    private Long id;

    @Column(name="sent")
    @ExcelCell(1)
    private Boolean sent = false;

}