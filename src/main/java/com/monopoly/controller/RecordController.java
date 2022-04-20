package com.monopoly.controller;

import com.monopoly.entity.Record;
import com.monopoly.service.RecordService;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
public class RecordController {
    private final RecordService recordService;

    public RecordController(RecordService RecordService) { this.recordService = RecordService; }

    @GetMapping("/leaderboard/alltimes")
    public List<Map<String, Long>> getLeaderBoardAllTimes() {return recordService.getLeaderboardAllTimes();}

    @GetMapping("/leaderboard/weekly")
    public List<Map<String, Long>> getLeaderboardWeekly() {return recordService.getLeaderboardWeekly();}

    @GetMapping("/leaderboard/monthly")
    public List<Map<String, Long>> getLeaderboardMonthly() {return recordService.getLeaderboardMonthly();}

    @PostMapping("/record/insert")
    public ResponseEntity<Record> insertRecord(@RequestBody Record record) {
        Record newRecord = recordService.insert(record);
        return ResponseEntity.ok(newRecord);
    }
}
