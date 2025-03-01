package swe304.swe304_1.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Table(name = "building")
@Data
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> persons;
}
