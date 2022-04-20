package com.monopoly.controller;

import com.monopoly.entity.Player;
import com.monopoly.entity.Record;
import com.monopoly.repository.PlayerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordControllerTest {

    @Autowired
    private RecordController RecordController;

    @Autowired
    private PlayerRepository playerRepository;


    @Test
    public void insetRecordTest() {
        Record Record = new Record();
        Player player = playerRepository.getById(1L);
        Record.setPlayer(player);
        Record.setScore(1L);
        Record.setDate(LocalDateTime.now());
        Record newRecord = RecordController.insertRecord(Record).getBody();
        Assert.assertEquals(Record, newRecord);
    }



}