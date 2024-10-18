package com.foodcourt.msusers.domain.validations.impl;

import com.foodcourt.msusers.domain.validations.ValidationService;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class ValidationServiceImplement implements ValidationService {


    @Override
    public boolean isAdult(Date dateOfBirth) {
        if (dateOfBirth == null) {
            return false;  // O lanza una excepción, según tu lógica
        }
        // Convertir Date a LocalDate
        LocalDate localDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(localDate, LocalDate.now()).getYears() >= 18;
    }
}
