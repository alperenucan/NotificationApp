package com.internship.smsEmailTrigger.model;

import com.poiji.annotation.ExcelCell;
import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(name = "sms")
@AllArgsConstructor
@NoArgsConstructor
public class Sms {

    @Id
    @Column(name="id",nullable = false)
    @ExcelCell(0)
    private Long id;

    @Column(name="sent")
    @ExcelCell(1)
    private Boolean sent = false;

    @Column(name="type")
    @ExcelCell(2)
    private String type;

}
