package com.monopoly.service;

import com.monopoly.entity.Record;
import com.monopoly.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) { this.recordRepository = recordRepository; }

    public List<Map<String, Long>> getLeaderboardAllTimes(){
        return recordRepository.getLeaderboardAllTimes();
    }

    public List<Map<String, Long>> getLeaderboardWeekly(){
        return recordRepository.getLeaderboardWeekly();
    }

    public List<Map<String, Long>> getLeaderboardMonthly(){
        return recordRepository.getLeaderboardMonthly();
    }

    public Record insert(Record record) { return recordRepository.save(record); }

}
