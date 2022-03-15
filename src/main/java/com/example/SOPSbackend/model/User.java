package com.example.SOPSbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity // this means the class is JPA Entity
// more on that here -> https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
@Table// specifies name of the table in the database
@Getter // generates getters for the class
@Setter // generates setters for the class
@RequiredArgsConstructor // generates constructor with field annotated with @NonNull
@NoArgsConstructor // generates constructor without arguments
@ToString // generates toString method
public class User {

    public static User EMPTY = new User();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NonNull
    private String password;

    @Column
    @NonNull
    private String email;
}
