package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.NewVaccinationSlotDto;
import com.example.SOPSbackend.dto.VaccinatePatientStatusDto;
import com.example.SOPSbackend.exception.InternalValidationException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.dto.ResponseDictionaryDto;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.security.BasicUserDetails;
import com.example.SOPSbackend.service.DoctorService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;


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
        try {
            doctorService.addVaccinationSlot(doctor, vaccinationSlot.getDate());
        }
        catch (InternalValidationException e) {
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }

        return ResponseEntity.ok().body(Map.of("success", true));
    }

    @GetMapping("vaccination-slots")
    public ResponseEntity<Object> getVaccinationSlots(
            @RequestParam Optional<String> startDate,
            @RequestParam Optional<String> endDate,
            @RequestParam Optional<String> onlyReserved,
            @RequestParam Optional<Integer> page,
            @AuthenticationPrincipal BasicUserDetails authPrincipal){
        DoctorEntity doctor = (DoctorEntity)authPrincipal.getUser();
        Page<ResponseDictionaryDto> slots = doctorService.getVaccinationSlots(
                doctor,
                page.orElse(1),
                startDate.orElse(null),
                endDate.orElse(null),
                onlyReserved.orElse(null)
        );
        return ResponseEntity.ok().body(Map.of(
                "pagination", Map.of(
                        "currentPage", page.orElse(1),
                        "totalPages", slots.getTotalPages(),
                        "currentRecords", slots.getNumberOfElements(),
                        "totalRecords", slots.getTotalElements()
                ),
                "data", slots.get().toArray()
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

    @PutMapping("vaccination-slots/{vaccinationSlotId}")
    public ResponseEntity<Object> vaccinatePatient(
            @PathVariable Long vaccinationSlotId,
            @RequestBody VaccinatePatientStatusDto vaccinatePatientStatusDto,
            @AuthenticationPrincipal BasicUserDetails authPrincipal) {
        DoctorEntity doctor = (DoctorEntity)authPrincipal.getUser();
        try {
            doctorService.vaccinatePatient(
                    vaccinationSlotId,
                    vaccinatePatientStatusDto.getStatus(),
                    doctor
            );
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("success",false, "msg", e.getMessage())
            );
        } catch (InternalValidationException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    Map.of("success", false, "data", e.getErrors())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of("success", false, "msg", e.getMessage())
            );
        }
        return ResponseEntity.ok().body(Map.of("success",true));
    }
}
