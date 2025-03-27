package swe304.swe304_1.dto;

import lombok.Data;
import java.util.List;

@Data
public class PatentDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer authorId;
}