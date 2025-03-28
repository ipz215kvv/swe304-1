package swe304.swe304_1.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Table(name = "patent")
@Data
public class Patent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name = "authorId", nullable = false)
    private Author author;
}
