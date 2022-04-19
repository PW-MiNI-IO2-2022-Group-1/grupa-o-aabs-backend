package com.example.SOPSbackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VaccineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String disease;

    @Column(nullable = false)
    private long requiredDoses;

    public enum Disease {
        COVID_19("COVID-19"),
        COVID_21("COVID-21"),
        FLU("Flu"),
        OTHER("OTHER");

        private static final Map<String, Disease> BY_LABEL = new HashMap<>();
        static {
            for (Disease d: values()) {
                BY_LABEL.put(d.label, d);
            }
        }

        public final String label;

        private Disease(String label) {
            this.label = label;
        }

        public static Disease valueOfLabel(String label) {
            return BY_LABEL.get(label);
        }

        public static boolean isValidDiseaseName(String label) {
            return valueOfLabel(label) != null;
        }

        public static String getValidDiseaseNames() {
            return BY_LABEL.keySet().stream().collect(Collectors.joining(","));
        }
    }
}
