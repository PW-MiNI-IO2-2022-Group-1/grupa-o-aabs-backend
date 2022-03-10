package com.example.SOPSbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity // this means the class is JPA Entity
        // more on that here -> https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
@Table(name="people") // specifies name of the table in the database
@Getter // generates getters for the class
@Setter // generates setters for the class
@RequiredArgsConstructor // generates constructor with field annotated with @NonNull
@NoArgsConstructor // generates constructor without arguments
@ToString // generates toString method
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;
}
