package com.niantic.services;

import com.niantic.models.Assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsService
{
    public List<Integer> calculateStatistics(List<Assignment> assignments)
    {
        int lowScore = assignments.stream().mapToInt(Assignment::getScore).min().getAsInt();
        int highScore = assignments.stream().mapToInt(Assignment::getScore).max().getAsInt();
        int avgScore = assignments.stream().mapToInt(Assignment::getScore).sum() / assignments.size();

        List<Integer> stats = new ArrayList<>() {{
            add(lowScore);
            add(highScore);
            add(avgScore);
        }};

        return stats;
    }

    public Map<String, List<Assignment>> mapAssignmentsToStatistics(List<Integer> stats, List<Assignment> assignments)
    {
        List<Assignment> lowAssignments = new ArrayList<>();
        List<Assignment> highAssignments = new ArrayList<>();
        List<Assignment> avgAssignments = new ArrayList<>();

        assignments.forEach(assignment -> {
            int score = assignment.getScore();

            if (score == stats.get(0)) lowAssignments.add(assignment);
            if (score == stats.get(1)) highAssignments.add(assignment);
            if (score == stats.get(2) || score == stats.get(2) - 1 || score == stats.get(2) + 1) avgAssignments.add(assignment);
        });

        Map<String, List<Assignment>> mapScores = new HashMap<>() {{
            put("low", lowAssignments);
            put("high", highAssignments);
            put("avg", avgAssignments);
        }};

        return mapScores;
    }
}
