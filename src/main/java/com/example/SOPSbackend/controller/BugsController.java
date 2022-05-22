package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.BugDto;
import com.example.SOPSbackend.service.BugService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class BugsController extends AbstractController {
    private BugService bugService;

    public BugsController(BugService bugService) {
        this.bugService = bugService;
    }

    @PostMapping("bugs")
    public ResponseEntity<Object> reportBug(@RequestBody @Valid BugDto bug) {
        bugService.saveBug(bug);
        return ResponseEntity.ok().body(Map.of("success", true));
    }
}
