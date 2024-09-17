package com.niantic.services;

import com.niantic.models.Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

        try (PrintWriter out = new PrintWriter(file))
        {
            out.println(name);
            out.println("-".repeat(40));
            out.printf("Low Score                          %d\n", stats.get(0));
            out.printf("High Score                         %d\n", stats.get(1));
            out.printf("Average Score                      %d\n", stats.get(2));
            System.out.println("Student Summary Report created!");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("There was an error creating the Student Summary Report.");
        }
    }

    public void createAllStudentsReport(List<Assignment> assignments)
    {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = "reports/" + today.format(dateFormatter) + "_all_students.txt";

        File file = new File(fileName);
        List<Integer> stats = calculateStatistics(assignments);
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
            out.printf("Low Score                                              %d\n", stats.get(0));
            out.printf("High Score                                             %d\n", stats.get(1));
            out.printf("Average Score                                          %d\n", stats.get(2));
            System.out.println("Student Summary Report created!");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("There was an error creating the All Students Report.");
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

    private void ensureDirectoryExists(String filePath)
    {
        File directory = new File(filePath);

        if (!directory.exists()) directory.mkdir();
    }
}
