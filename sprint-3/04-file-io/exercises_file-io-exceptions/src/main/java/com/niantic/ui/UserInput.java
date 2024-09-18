package com.niantic.ui;

import com.niantic.models.Assignment;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInput
{
    protected static Scanner in = new Scanner(System.in);
    private static int countFile;

    public static int homeScreenSelection()
    {
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("-".repeat(30));
        System.out.println();
        System.out.println("  1) Display files");
        System.out.println();
        System.out.println("  ------------ Individual File ------------");
        System.out.println("  2) Student: display all scores");
        System.out.println("  3) Student: display average score");
        System.out.println();
        System.out.println("  ---------- Challenge All Files ----------");
        System.out.println("  4) All Students: display average score");
        System.out.println("  5) All Assignments: display average score");
        System.out.println();
        System.out.println("  ---------- Writing Files ----------");
        System.out.println("  6) Create Student Summary Report");
        System.out.println("  7) Create All Students Report");
        System.out.println();
        System.out.println("  0) Exit");

        System.out.println();
        System.out.print("Please make a selection: ");

        return Integer.parseInt(in.nextLine());
    }

    public static int studentFileSelection()
    {
        System.out.println();
        System.out.print("Please make a selection: ");
        return Integer.parseInt(in.nextLine());
    }

    public static void displayAllFiles(String[] files)
    {
        countFile = 1;

        System.out.println();
        System.out.println("File Names");
        System.out.println("-".repeat(35));

        Arrays.stream(files)
                .forEach(file ->
                {
                    System.out.println(String.format("  %d.) %s", countFile, file));
                    countFile++;
                });
    }

    public static void displayStudentScores(String selectedFile, List<Assignment> assignments)
    {
        // printing assignments & scores
        System.out.printf("Assignment" + " ".repeat(24) + "Score");
        System.out.println();
        System.out.println("-".repeat(40));
        assignments.forEach(assignment -> {
            System.out.printf("%-3s %-30s %d\n",
                    assignment.getNumber(),
                    assignment.getAssignmentName(),
                    assignment.getScore());
        });
        System.out.println();
    }

    public static void displayStatistics(List<Integer> stats, Map<String, List<Assignment>> mapStatsToAssignments)
    {
        // print stats and its associated assignments
        System.out.println("-".repeat(40));
        System.out.printf("Low Score                          %d\n", stats.get(0));
        System.out.println("-".repeat(40));
        mapStatsToAssignments.get("low").forEach(assignment -> {
            System.out.printf("%-3s %-30s %d\n", assignment.getNumber(), assignment.getAssignmentName(), assignment.getScore());
        });
        System.out.println();

        System.out.println("-".repeat(40));
        System.out.printf("High Score                         %d\n", stats.get(1));
        System.out.println("-".repeat(40));
        mapStatsToAssignments.get("high").forEach(assignment -> {
            System.out.printf("%-3s %-30s %d\n", assignment.getNumber(), assignment.getAssignmentName(), assignment.getScore());
        });
        System.out.println();

        System.out.println("-".repeat(40));
        System.out.printf("Average Score                      %d\n", stats.get(2));
        System.out.println("-".repeat(40));
        mapStatsToAssignments.get("avg").forEach(assignment -> {
            System.out.printf("%-3s %-30s %d\n", assignment.getNumber(), assignment.getAssignmentName(), assignment.getScore());
        });

        System.out.println();
    }

    public static void displayStudentHeader(String selectedFile)
    {
        // print student name
        System.out.println();
        System.out.println(selectedFile.toUpperCase());
        System.out.println("-".repeat(40));
    }

    public static void displayMessage(String message)
    {
        System.out.println();
        System.out.println(message);
    }

    public static void displayUserContinue()
    {
        System.out.println();
        System.out.print("Press ENTER to continue.");
        in.nextLine();
    }
}
