package com.example.SOPSbackend.model.converter;

import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDictionary {
    public ResponseDictionary(VaccinationSlotEntity vaccinationSlotEntity, VaccinationEntity vaccination)
    {
        this.id = vaccinationSlotEntity.getId();
        this.date = vaccinationSlotEntity.getDate();
        this.vaccination = new ResponseVaccination(vaccination);
    }

    private Long id;
    private LocalDateTime date;
    private ResponseVaccination vaccination;
    public static Map<String, Object> convertToMap(ResponseDictionary slot)
    {
        var hm = new HashMap<String, Object>();
        var v = slot.getVaccination();
        var patient = v == null? null : v.getPatient();
        var address = patient == null? null : patient.getAddress();
        hm.put("id", slot.getId());
        hm.put("date", slot.getDate()
                .format(DateTimeFormatter.ISO_DATE_TIME));
        hm.put("vaccination", v == null? null : Map.of(
                "id", v.getId(),
                "vaccine", Map.of(
                        "id", v.getVaccine().getId(),
                        "name", v.getVaccine().getName(),
                        "disease", v.getVaccine().getDisease(),
                        "requiredDoses", v.getVaccine().getRequiredDoses()
                ),
                "status", v.getStatus(),
                "patient", Map.of(
                        "id", patient.getId(),
                        "firstName", patient.getFirstName(),
                        "lastName", patient.getLastName(),
                        "pesel", patient.getPesel(),
                        "email", patient.getEmail(),
                        "address", Map.of(
                                "id", address.getId(),
                                "city", address.getCity(),
                                "zipCode", address.getZipCode(),
                                "street", address.getStreet(),
                                "houseNumber", address.getHouseNumber(),
                                "localNumber", address.getLocalNumber()
                        )
                )
        ));
        return hm;
    }
}
