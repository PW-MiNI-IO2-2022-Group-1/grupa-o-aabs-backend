package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.BugDto;
import com.example.SOPSbackend.model.BugEntity;
import com.example.SOPSbackend.repository.BugRepository;
import org.springframework.stereotype.Service;

@Service
public class BugService {
    private BugRepository bugRepository;

    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    public void saveBug(BugDto bugDto) {
       BugEntity bug = new BugEntity(bugDto);
       bugRepository.save(bug);
    }
}
