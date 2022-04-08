package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.NewVaccinationSlotDto;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.service.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import javax.validation.Valid;
import java.util.HashMap;
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
            @AuthenticationPrincipal DoctorEntity doctor) {

        doctorService.addVaccinationSlot(doctor, vaccinationSlot.getDate());

        return ResponseEntity.ok().body(Map.of("success", true));
    }

    @RequestMapping(value="vaccination-slots", method = RequestMethod.GET)
    public ResponseEntity<Object> getVaccinationSlots(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "onlyReserved", required = false) String onlyReserved,
            @RequestParam(value = "page", required = false) int page,
            @AuthenticationPrincipal DoctorEntity doctor){
        Page<VaccinationSlotEntity> slots = doctorService.getVaccinationSlots(doctor, page, startDate, endDate, onlyReserved);
        return ResponseEntity.ok().body(Map.of(
                "pagination", Map.of(
                        "currentPage", page,
                        "totalPages", slots.getTotalPages(),
                        "currentRecords", doctorService.ITEMS_PER_PAGE,
                        "totalRecords", slots.getTotalElements()
                ),
                "data", slots.get().map(
                        slot -> Map.of(
                                "id", slot.getId(),
                                "date", slot.getDate()
                        ))
                ));
    }

    @DeleteMapping("vaccination-slots/{id}")
    public ResponseEntity<Object> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal DoctorEntity doctor) {

        doctorService.deleteVaccinationSlot(doctor, id);

        return ResponseEntity.ok().body(Map.of("success",true));
    }
}
