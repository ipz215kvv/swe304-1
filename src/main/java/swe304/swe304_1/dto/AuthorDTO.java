package swe304.swe304_1.dto;

import lombok.Data;
import java.util.List;

@Data
public class AuthorDTO {
    private Integer id;
    private String name;
    private String address;
    private String imgUrl;
    private List<PatentDTO> patents;
}