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
    private StatisticsService statService = new StatisticsService();
    private LogService errorLogger = new LogService("error");

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
        List<Integer> stats = statService.calculateStatistics(assignments);
        Map<String, List<Assignment>> mapScores = statService.mapAssignmentsToStatistics(stats, assignments);

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
            System.out.println("Student Summary Report created for " + name);
        }
        catch (FileNotFoundException e)
        {
            errorLogger.createLogEntry(e.getMessage());
            System.out.println("There was an error creating the Student Summary Report for " + name);
        }
    }

    public void createAllStudentsReport(List<Assignment> assignments)
    {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = "reports/" + today.format(dateFormatter) + "_all_students.txt";

        File file = new File(fileName);
        List<Integer> stats = statService.calculateStatistics(assignments);
        Map<String, List<Assignment>> mapScores = statService.mapAssignmentsToStatistics(stats, assignments);

        int studentTotal = assignments.stream()
                .map(assignment -> new String(assignment.getFirstName() + " " + assignment.getLastName()))
                .distinct()
                .toList()
                .size();

        try (PrintWriter out = new PrintWriter(file))
        {
            out.println("All Assignments");
            out.println("-".repeat(60));
            out.printf("Total Students                                           %d\n", studentTotal);
            out.printf("Total Assignments                                        %d\n", assignments.size());
            out.println("-".repeat(60));
            out.println();

            out.println("-".repeat(60));
            out.printf("Low Score                                                %d\n", stats.get(0));
            out.println("-".repeat(60));
            mapScores.get("low").forEach(assignment -> {
                String name = assignment.getFirstName() + " " + assignment.getLastName();

                out.printf("%-3s %-30s %-21s %d\n",
                        assignment.getNumber(),
                        assignment.getAssignmentName(),
                        name,
                        assignment.getScore());
            });
            out.println();

            out.println("-".repeat(60));
            out.printf("High Score                                               %d\n", stats.get(1));
            out.println("-".repeat(60));
            mapScores.get("high").forEach(assignment -> {
                String name = assignment.getFirstName() + " " + assignment.getLastName();

                out.printf("%-3s %-30s %-21s %d\n",
                        assignment.getNumber(),
                        assignment.getAssignmentName(),
                        name,
                        assignment.getScore());
            });
            out.println();

            out.println("-".repeat(60));
            out.printf("Average Score                                            %d\n", stats.get(2));
            out.println("-".repeat(60));
            mapScores.get("avg").forEach(assignment -> {
                String name = assignment.getFirstName() + " " + assignment.getLastName();

                out.printf("%-3s %-30s %-21s %d\n",
                        assignment.getNumber(),
                        assignment.getAssignmentName(),
                        name,
                        assignment.getScore());
            });

            System.out.println();
            System.out.println("All Students Report created!");
        }
        catch (FileNotFoundException e)
        {
            errorLogger.createLogEntry(e.getMessage());
            System.out.println("There was an error creating the All Students Report.");
        }
    }

    private void ensureDirectoryExists(String filePath)
    {
        File directory = new File(filePath);

        if (!directory.exists()) directory.mkdir();
    }
}
