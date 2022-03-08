package com.example.SOPSbackend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="people")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
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
