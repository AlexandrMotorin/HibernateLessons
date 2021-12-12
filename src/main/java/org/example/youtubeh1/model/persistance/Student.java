package org.example.youtubeh1.model.persistance;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "student")
@NamedQueries({
        @NamedQuery(name = Student.PREFIX_QUERY_NAME, query = "select s " +
                "from Student s " +
                "where firstName like ?1"),
        @NamedQuery(name = Student.BY_GROUP_ID_QUERY_NAME, query = "select s " +
                "from Student s " +
                "where s.group.id = ?1"),
        @NamedQuery(name = Student.BY_GROUP_ID_AND_SORTED_QUERY_NAME, query = "select s " +
                "from Student s " +
                "where s.group.id = :groupId " +
                "order by s.birthday")
})
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {

    public static final String PREFIX_QUERY_NAME = "Student.prefixByFirstName";
    public static final String BY_GROUP_ID_QUERY_NAME = "Student.byGroupId";
    public static final String BY_GROUP_ID_AND_SORTED_QUERY_NAME = "Student.byGroupIdAndSortedByBirthday";

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
