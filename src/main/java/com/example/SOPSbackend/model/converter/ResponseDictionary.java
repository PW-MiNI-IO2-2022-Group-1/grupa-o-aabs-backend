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
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDictionary {
    public ResponseDictionary(VaccinationSlotEntity vaccinationSlotEntity, VaccinationEntity vaccination)
    {
        this.id = vaccinationSlotEntity.getId();
        this.date = vaccinationSlotEntity.getDate();
        this.vaccination = vaccination == null? Optional.empty() : Optional.of(new ResponseVaccination(vaccination));
    }

    private Long id;
    private LocalDateTime date;
    private Optional<ResponseVaccination> vaccination;
    public static Map<String, Object> toMap(ResponseDictionary slot)
    {
        var hm = new HashMap<String, Object>();
        var v = slot.getVaccination();
        var patient = v.isEmpty()? null : v.get().getPatient();
        var vaccine = v.isEmpty()? null : v.get().getVaccine();
        var address = patient == null? null : patient.getAddress();
        var vaccination = v.orElse(null);
        var vaccinationHashMap = v.isEmpty()? null: Map.ofEntries(
                new AbstractMap.SimpleEntry<String, Object>("id", vaccination.getId()),
                new AbstractMap.SimpleEntry<String, Object>("vaccine", vaccine == null? null : Map.of(
                        "id", vaccine.getId(),
                        "name", vaccine.getName(),
                        "disease", vaccine.getDisease(),
                        "requiredDoses", vaccine.getRequiredDoses()
                )),
                new AbstractMap.SimpleEntry<String, Object>("status", vaccination.getStatus()),
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
        hm.put("vaccination", vaccinationHashMap);
        return hm;
    }
}
