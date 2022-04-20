package com.monopoly.repository;

import com.monopoly.entity.Record;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class RecordRepositoryTest {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private PlayerRepository playerRepository;



    @Test
    public void testInsertRecord() {


        Record newRecord = recordRepository.save(new Record(null, 1L, LocalDateTime.now(), playerRepository.getById(1L)));
        assertEquals(playerRepository.getById(1L), newRecord.getPlayer());

    }
}
