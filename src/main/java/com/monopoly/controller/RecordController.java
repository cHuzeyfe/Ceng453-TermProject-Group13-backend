package com.monopoly.controller;

import com.monopoly.entity.Player;
import com.monopoly.entity.Record;
import com.monopoly.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.CacheRequest;
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
