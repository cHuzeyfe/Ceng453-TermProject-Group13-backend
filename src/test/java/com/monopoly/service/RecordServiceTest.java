package com.monopoly.service;

import com.monopoly.entity.Record;
import com.monopoly.repository.PlayerRepository;
import com.monopoly.repository.RecordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RecordServiceTest {
    @Mock
    private RecordRepository recordRepository;
    @InjectMocks
    private RecordService recordService;

    @Test
    public void testGetLeaderboardAllTimes(){
        when(recordRepository.getLeaderboardAllTimes()).thenReturn(new ArrayList<>());
        List<Map<String, Long>> leaderboardAllTimes = recordService.getLeaderboardAllTimes();
        assertNotNull(leaderboardAllTimes);
        assertEquals(leaderboardAllTimes.size(), 0);
    }

    @Test
    public void testGetLeaderboardWeekly(){
        when(recordRepository.getLeaderboardWeekly()).thenReturn(new ArrayList<>());
        List<Map<String, Long>> leaderboardWeekly = recordService.getLeaderboardWeekly();
        assertNotNull(leaderboardWeekly);
        assertEquals(leaderboardWeekly.size(), 0);
    }

    @Test
    public void testGetLeaderboardMonthly(){
        when(recordRepository.getLeaderboardMonthly()).thenReturn(new ArrayList<>());
        List<Map<String, Long>> leaderboardMonthly = recordService.getLeaderboardMonthly();
        assertNotNull(leaderboardMonthly);
    }

}
