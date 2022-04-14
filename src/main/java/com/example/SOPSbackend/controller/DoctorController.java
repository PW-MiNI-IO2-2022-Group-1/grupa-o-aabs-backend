package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.NewVaccinationSlotDto;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.model.converter.VaccinationSlotStatus;
import com.example.SOPSbackend.security.BasicUserDetails;
import com.example.SOPSbackend.service.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import java.util.HashMap;
@RestController
@RequestMapping("doctor")
public class DoctorController extends AbstractController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("vaccination-slots")
    public ResponseEntity<Object> createNewVaccinationSlot(
            @RequestBody @Valid NewVaccinationSlotDto vaccinationSlot,
            @AuthenticationPrincipal BasicUserDetails authPrincipal) {

        DoctorEntity doctor = (DoctorEntity)authPrincipal.getUser();
        doctorService.addVaccinationSlot(doctor, vaccinationSlot.getDate());

        return ResponseEntity.ok().body(Map.of("success", true));
    }

    @RequestMapping(value="vaccination-slots", method = RequestMethod.GET)
    public ResponseEntity<Object> getVaccinationSlots(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "onlyReserved", required = false) String onlyReserved,
            @RequestParam(value = "page", required = false) int page,
            @AuthenticationPrincipal BasicUserDetails authPrincipal){
        DoctorEntity doctor = (DoctorEntity)authPrincipal.getUser();
        Page<VaccinationSlotStatus> slots = doctorService.getVaccinationSlots(doctor, page, startDate, endDate, onlyReserved);
        var freeSlotsMap = slots.get().map(
                slot -> {
                    var hm = new HashMap<String, Object>();
                    var v = slot.getVaccination();
                    var patient = v == null? null : v.getPatient();
                    var address = patient == null? null : patient.getAddress();
                    hm.put("id", slot.getVaccinationSlot().getId());
                    hm.put("date", slot.getVaccinationSlot().getDate()
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
                }).toArray();
        return ResponseEntity.ok().body(Map.of(
                "pagination", Map.of(
                        "currentPage", page,
                        "totalPages", slots.getTotalPages(),
                        "currentRecords", slots.getNumberOfElements(),
                        "totalRecords", slots.getTotalElements()
                ),
                "data", freeSlotsMap
                ));
    }

    @DeleteMapping("vaccination-slots/{id}")
    public ResponseEntity<Object> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal BasicUserDetails authPrincipal) {
        DoctorEntity doctor = (DoctorEntity)authPrincipal.getUser();
        doctorService.deleteVaccinationSlot(doctor, id);

        return ResponseEntity.ok().body(Map.of("success",true));
    }
}
