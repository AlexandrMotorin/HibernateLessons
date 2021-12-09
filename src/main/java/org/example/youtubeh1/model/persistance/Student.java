package org.example.youtubeh1.model.persistance;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "first_name", nullable = false)
    @EqualsAndHashCode.Include
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @EqualsAndHashCode.Include
    private String lastName;

    @Column(name = "birthday")
    @EqualsAndHashCode.Include
    private LocalDate birthday;

    @OneToOne
    @EqualsAndHashCode.Include
    private Address address;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Group group;
}
