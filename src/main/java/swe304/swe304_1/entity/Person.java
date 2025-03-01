package swe304.swe304_1.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String occupation;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private String number;

    private String imgUrl;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
}
