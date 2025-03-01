package swe304.swe304_1.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class PersonForm {

    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(max = 16, message = "Name cannot be longer than 16 characters")
    private String name;

    @NotBlank(message = "Occupation is required")
    @Size(max = 32, message = "Occupation cannot be longer than 32 characters")
    private String occupation;

    @NotNull(message = "Floor is required")
    private Integer floor;

    @NotBlank(message = "Number is required")
    @Size(max = 16, message = "Number cannot be longer than 16 characters")
    private String number;

    @NotNull(message = "Building is required")
    private Integer buildingId;

    private MultipartFile image;

    private String imgUrl;
}