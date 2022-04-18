package com.example.SOPSbackend.model.converter;

import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
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
        this.vaccination = vaccination == null? null : new ResponseVaccination(vaccination);
    }

    private Long id;
    private LocalDateTime date;
    private ResponseVaccination vaccination;
    public static Map<String, Object> toMap(ResponseDictionary slot)
    {
        var hm = new HashMap<String, Object>();
        var v = slot.getVaccination();
        var patient = v == null? null : v.getPatient();
        var vaccine = v == null? null : v.getVaccine();
        var address = patient == null? null : patient.getAddress();
        var vaccinationHashMap = v == null? null: Map.ofEntries(
                new AbstractMap.SimpleEntry<String, Object>("id", v.getId()),
                new AbstractMap.SimpleEntry<String, Object>("vaccine", vaccine == null? null : Map.of(
                        "id", v.getVaccine().getId(),
                        "name", v.getVaccine().getName(),
                        "disease", v.getVaccine().getDisease(),
                        "requiredDoses", v.getVaccine().getRequiredDoses()
                )),
                new AbstractMap.SimpleEntry<String, Object>("status", v.getStatus()),
                new AbstractMap.SimpleEntry<String, Object>("patient", patient == null? null : Map.of(
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
                ))
        );
        hm.put("id", slot.getId());
        hm.put("date", slot.getDate()
                .format(DateTimeFormatter.ISO_DATE_TIME));
        hm.put("vaccination", v == null? null : vaccinationHashMap);
        return hm;
    }
}
