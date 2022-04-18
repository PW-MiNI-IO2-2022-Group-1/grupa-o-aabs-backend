package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.NewVaccinationSlotDto;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.converter.ResponseDictionary;
import com.example.SOPSbackend.security.BasicUserDetails;
import com.example.SOPSbackend.service.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

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

    @GetMapping("vaccination-slots")
    public ResponseEntity<Object> getVaccinationSlots(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "onlyReserved", required = false) String onlyReserved,
            @RequestParam(value = "page", required = false) int page,
            @AuthenticationPrincipal BasicUserDetails authPrincipal){
        DoctorEntity doctor = (DoctorEntity)authPrincipal.getUser();
        Page<ResponseDictionary> slots = doctorService.getVaccinationSlots(doctor, page, startDate, endDate, onlyReserved);
        var freeSlotsMap = slots.get().map(ResponseDictionary::toMap).toArray();
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
