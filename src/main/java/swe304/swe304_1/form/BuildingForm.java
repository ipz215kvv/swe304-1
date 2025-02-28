package swe304.swe304_1.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BuildingForm {

    private Integer id;

    @NotBlank(message = "Address is required")
    @Size(max = 32, message = "Address cannot be longer than 32 characters")
    private String address;
}