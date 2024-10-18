package com.foodcourt.msusers.aplication.dto.request;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Getter
@Setter
public class OwnerRequest {

    @NotNull(message = "El nombre es obligatorio")
    private String name;

    @NotNull(message = "El apellido es obligatorio")
    private String lastName;

    @NotNull(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d", message = "El DNI debe ser solo numérico")
    private Integer dni;

    @NotNull(message = "El teléfono es obligatorio")
    @Size(max = 13, message = "El teléfono no debe tener más de 13 caracteres")
    @Pattern(regexp = "^\\+?[0-9]{1,12}$", message = "El teléfono debe ser numérico y puede incluir el símbolo '+'")
    private String phone;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    @NotNull(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotNull(message = "La contraseña es obligatoria")
    private String password;




}
