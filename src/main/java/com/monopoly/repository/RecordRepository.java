package com.monopoly.repository;

import com.monopoly.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{

    @Query(value = "SELECT P.username, SUM(R.score) as overall_score " +
            "FROM player P INNER JOIN record R ON P.userid = R.userid " +
            "GROUP BY P.userid ORDER BY overall_score DESC", nativeQuery=true )
    List<Map<String, Long>> getLeaderboardAllTimes();
    @Query(value = "SELECT P.username, SUM(R.score) as overall_score " +
            "FROM player P INNER JOIN record R ON P.userid = R.userid " +
            "WHERE R.date >= DATE_SUB(now(), INTERVAL 1 WEEK) " +
            "GROUP BY P.userid ORDER BY overall_score DESC", nativeQuery=true )
    List<Map<String, Long>> getLeaderboardWeekly();

    @Query(value = "SELECT P.username, SUM(R.score) as overall_score\n" +
            "FROM player P INNER JOIN record R ON P.userid = R.userid\n" +
            "WHERE R.date >= DATE_SUB(now(), INTERVAL  1 MONTH)\n" +
            "GROUP BY P.userid ORDER BY overall_score DESC", nativeQuery=true )
    List<Map<String, Long>> getLeaderboardMonthly();
}
