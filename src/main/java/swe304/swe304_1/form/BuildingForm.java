package swe304.swe304_1.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BuildingForm {

    private Integer id;

    @NotBlank(message = "Country is required")
    @Size(max = 32, message = "Country cannot be longer than 32 characters")
    private String country;

    @NotBlank(message = "City is required")
    @Size(max = 32, message = "City cannot be longer than 32 characters")
    private String city;

    @NotBlank(message = "Street is required")
    @Size(max = 64, message = "Street cannot be longer than 64 characters")
    private String street;

    @NotBlank(message = "Number is required")
    @Size(max = 12, message = "Number cannot be longer than 12 characters")
    private String number;
}