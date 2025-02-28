package swe304.swe304_1.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "building")
@Data
//@Data contains all this annotations(and even more):
//@Getter
//@Setter
//@RequiredArgsConstructor

public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String address;
}
