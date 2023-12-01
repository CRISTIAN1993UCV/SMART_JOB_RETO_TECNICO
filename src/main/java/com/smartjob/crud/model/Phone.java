package com.smartjob.crud.model;

import java.util.UUID;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    private String number;

    private String cityCode;

    private String countryCode;

}
