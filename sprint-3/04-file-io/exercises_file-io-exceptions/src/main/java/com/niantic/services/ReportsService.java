package com.niantic.services;

import com.niantic.models.Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportsService
{
    private String reportFolder;

    public ReportsService(String reportFolder)
    {
        ensureDirectoryExists(reportFolder);

        this.reportFolder = reportFolder;
    }

    public void createStudentSummaryReport(String name, List<Assignment> assignments)
    {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = "reports/" + today.format(dateFormatter) + "_" + name.replace(" ", "_") + ".txt";

        File file = new File(fileName);
        List<Integer> stats = calculateStatistics(assignments);
        Map<String, List<Assignment>> mapScores = mapAssignmentsToStatistics(stats, assignments);

        try (PrintWriter out = new PrintWriter(file))
        {
            out.println(name);
            out.println("-".repeat(40));
            out.println();

            out.println("-".repeat(40));
            out.printf("Low Score                          %d\n", stats.get(0));
            out.println("-".repeat(40));
            mapScores.get("low").forEach(assignment -> {
                out.printf("%-3s %-30s %d\n", assignment.getNumber(), assignment.getAssignmentName(), assignment.getScore());
            });
            out.println();

            out.println("-".repeat(40));
            out.printf("High Score                         %d\n", stats.get(1));
            out.println("-".repeat(40));
            mapScores.get("high").forEach(assignment -> {
                out.printf("%-3s %-30s %d\n", assignment.getNumber(), assignment.getAssignmentName(), assignment.getScore());
            });
            out.println();

            out.println("-".repeat(40));
            out.printf("Average Score                      %d\n", stats.get(2));
            out.println("-".repeat(40));
            mapScores.get("avg").forEach(assignment -> {
                out.printf("%-3s %-30s %d\n", assignment.getNumber(), assignment.getAssignmentName(), assignment.getScore());
            });

            System.out.println();
            System.out.println("Student Summary Report created!");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("There was an error creating the Student Summary Report." + e.getMessage());
        }
    }

    public void createAllStudentsReport(List<Assignment> assignments)
    {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = "reports/" + today.format(dateFormatter) + "_all_students.txt";

        File file = new File(fileName);
        List<Integer> stats = calculateStatistics(assignments);
        Map<String, List<Assignment>> mapScores = mapAssignmentsToStatistics(stats, assignments);

        int studentTotal = assignments.stream()
                .map(assignment -> new String(assignment.getFirstName() + " " + assignment.getLastName()))
                .distinct()
                .toList()
                .size();

        try (PrintWriter out = new PrintWriter(file))
        {
            out.println("All Assignments");
            out.println("-".repeat(60));
            out.printf("Total Students                                         %d\n", studentTotal);
            out.printf("Total Assignments                                      %d\n", assignments.size());
            out.println("-".repeat(60));
            out.println();

            out.println("-".repeat(40));
            out.printf("Low Score                          %d\n", stats.get(0));
            out.println("-".repeat(40));
            mapScores.get("low").forEach(assignment -> {
                out.printf("%-3s %-30s %d\n", assignment.getNumber(), assignment.getAssignmentName(), assignment.getScore());
            });
            out.println();

            out.println("-".repeat(40));
            out.printf("High Score                         %d\n", stats.get(1));
            out.println("-".repeat(40));
            mapScores.get("high").forEach(assignment -> {
                out.printf("%-3s %-30s %d\n", assignment.getNumber(), assignment.getAssignmentName(), assignment.getScore());
            });
            out.println();

            out.println("-".repeat(40));
            out.printf("Average Score                      %d\n", stats.get(2));
            out.println("-".repeat(40));
            mapScores.get("avg").forEach(assignment -> {
                out.printf("%-3s %-30s %d\n", assignment.getNumber(), assignment.getAssignmentName(), assignment.getScore());
            });

            System.out.println();
            System.out.println("Student Summary Report created!");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("There was an error creating the All Students Report." + e.getMessage());
        }
    }

    private List<Integer> calculateStatistics(List<Assignment> assignments)
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

    private Map<String, List<Assignment>> mapAssignmentsToStatistics(List<Integer> stats, List<Assignment> assignments)
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

    private void ensureDirectoryExists(String filePath)
    {
        File directory = new File(filePath);

        if (!directory.exists()) directory.mkdir();
    }
}
