package com.foodcourt.msusers.domain.validations;


import java.util.Date;

public interface ValidationService {

    boolean isAdult(Date dateOfBirth);
}
