package swe304.swe304_1.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "author")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String imgUrl;

    @OneToMany(mappedBy = "patent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patent> patents;
}
