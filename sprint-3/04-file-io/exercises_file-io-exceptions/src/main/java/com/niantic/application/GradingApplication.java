package com.niantic.application;

import com.niantic.models.Assignment;
import com.niantic.services.GradesFileService;
import com.niantic.services.GradesService;
import com.niantic.ui.UserInput;

import java.util.*;

public class GradingApplication implements Runnable
{
    private GradesService gradesService = new GradesFileService();
    private int countFile = 0;
    private String selectedFile;

    public void run()
    {
        while(true)
        {
            int choice = UserInput.homeScreenSelection();
            switch(choice)
            {
                case 1:
                    displayAllFiles();
                    UserInput.displayUserContinue();
                    break;
                case 2:
                    displayFileScores();
                    UserInput.displayUserContinue();
                    break;
                case 3:
                    displayStudentAverages();
                    UserInput.displayUserContinue();
                    break;
                case 4:
                    displayAllStudentStatistics();
                    UserInput.displayUserContinue();
                    break;
                case 5:
                    displayAssignmentStatistics();
                    UserInput.displayUserContinue();
                    break;
                case 0:
                    UserInput.displayMessage("Goodbye");
                    System.exit(0);
                default:
                    UserInput.displayMessage("Please make a valid selection");
            }
        }
    }

    private void displayAllFiles()
    {
        // todo: 1 - get and display all student file names
        String[] files = gradesService.getFileNames();
        countFile = 0;

        System.out.println();
        System.out.println("File Names");
        System.out.println("-".repeat(35));

        Arrays.stream(files)
                .forEach(file ->
                {
                    countFile++;
                    System.out.println(String.format("  %d.) %s", countFile, file));
                });
    }

    private void displayFileScores()
    {
        // todo: 2 - allow the user to select a file name
        // load all student assignment scores from the file - display all files
        // choose file and returning list of assignments
        System.out.println();
        List<Assignment> assignments = chooseFile();

        // printing assignments
        System.out.printf("Assignment" + " ".repeat(20) + "Score");
        System.out.println();
        System.out.println("-".repeat(35));
        assignments.forEach(assignment -> {
            System.out.println();
            System.out.printf("%-30s %d", assignment.getAssignmentName(), assignment.getScore());
        });
        System.out.println();
    }

    private void displayStudentAverages()
    {
        // todo: 3 - allow the user to select a file name
        // load all student assignment scores from the file - display student statistics (low score, high score, average score)
        System.out.println();
        List<Assignment> assignments = chooseFile();

        // Calculating Statistics
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int avg = 0;

        for (Assignment assignment : assignments)
        {
            int score = assignment.getScore();
            if (score < min) min = score;
            if (score > max) max = score;
            avg += score;
        }

        avg = avg / assignments.size();

        // Print Statistics
        System.out.println("Low Score: " + min);
        System.out.println("High Score: " + max);
        System.out.println("Average Score: " + avg);
    }

    private void displayAllStudentStatistics()
    {
        // todo: 4 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for all scores (low score, high score, average score, number of students, number of assignments)
        String[] files = gradesService.getFileNames();
        List<Assignment> assignments = gradesService.getAllAssignments(files);
        int numStudents = files.length;
        int numAssignments = assignments.size();

        String lowStudent = "";
        String lowAssignment = "";
        String highStudent = "";
        String highAssignment = "";
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int avg = 0;

        // Calculating Statistics
        for (Assignment assignment : assignments)
        {
            int score = assignment.getScore();

            if (score < min)
            {
                min = score;
                lowStudent = new String(assignment.getFirstName() + " " + assignment.getLastName()).toUpperCase();
                lowAssignment = assignment.getAssignmentName();
            }

            if (score > max)
            {
                max = score;
                highStudent = new String(assignment.getFirstName() + " " + assignment.getLastName()).toUpperCase();
                highAssignment = assignment.getAssignmentName();
            }

            avg += score;
        }

        avg = avg / assignments.size();

        // Print Statistics
        System.out.println();
        System.out.println("All Assignments Statistics");
        System.out.println("-".repeat(35));
        System.out.println("Low Score: " + min);
        System.out.println(" ".repeat(5) + "Student: " + lowStudent);
        System.out.println(" ".repeat(5) + "Assignment: " + lowAssignment);
        System.out.println("High Score: " + max);
        System.out.println(" ".repeat(5) + "Student: " + highStudent);
        System.out.println(" ".repeat(5) + "Assignment: " + highAssignment);
        System.out.println("Average Score: " + avg);
        System.out.println("Number of Students: " + numStudents);
        System.out.println("Number of Assignments: " + numAssignments);
    }

    private void displayAssignmentStatistics()
    {
        // todo: 5 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for each assignment (assignment name, low score, high score, average score)
        // this one could take some time
        String[] files = gradesService.getFileNames();
        List<Assignment> assignments = gradesService.getAllAssignments(files);

        List<String> allAssignmentNames = distinctAssignments(assignments);
        Map<String, List<Integer>> assignmentStatisticsMap = new HashMap<>();

        for (Assignment assignment : assignments)
        {
            int score = assignment.getScore();
            String name = assignment.getAssignmentName();

            // Key: String assignmentName
            // Value: List<Integer> [min, max, sum, count]
            if (!assignmentStatisticsMap.containsKey(name))
            {
                List<Integer> initiate = new ArrayList<>() {{
                    add(score);
                    add(score);
                    add(score);
                    add(1);
                }};

                assignmentStatisticsMap.put(name, initiate);
            }
            else
            {
                List<Integer> check = assignmentStatisticsMap.get(name);

                // checking min
                if (score < check.get(0)) check.set(0, score);

                // checking max
                if (score > check.get(1)) check.set(1, score);

                // tracking for average
                check.set(2, check.get(2) + score);
                check.set(3, check.get(3) + 1);
            }
        }

        for (String assignmentName : allAssignmentNames)
        {
            List<Integer> assignmentStats = assignmentStatisticsMap.get(assignmentName);
            int sum = assignmentStats.get(2);
            int countName = assignmentStats.get(3);

            // calculating average
            assignmentStats.set(2, sum / countName);

            // print statistics per distinct assignment
            System.out.println();
            System.out.println("Assignment: " + assignmentName);
            System.out.println("-".repeat(35));
            System.out.println("Low Score: " + assignmentStats.get(0));
            System.out.println("High Score: " + assignmentStats.get(1));
            System.out.println("Average Score: " + assignmentStats.get(2));
        }
    }

    private List<Assignment> fileSelectionCase()
    {
        while(true)
        {
            int choice = UserInput.studentFileSelection();
            switch(choice)
            {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    String fileName = gradesService.getFileNames()[choice - 1];
                    selectedFile = fileName;
                    return gradesService.getAssignments(fileName);
                default:
                    UserInput.displayMessage("Please make a valid selection");
            }
        }
    }

    private List<Assignment> chooseFile()
    {
        System.out.println();
        System.out.println("What file would you like to choose?");
        System.out.println("-".repeat(35));
        displayAllFiles();

        // choose file and returning list of assignments
        System.out.println();
        List<Assignment> assignments = fileSelectionCase();

        // printing student name
        System.out.println();
        System.out.printf(parseStudentName(selectedFile).toUpperCase());
        System.out.println();
        System.out.println("-".repeat(35));

        return assignments;
    }

    private List<String> distinctAssignments(List<Assignment> assignments)
    {
        List<String> distinct = assignments.stream()
                .map(assignment -> assignment.getAssignmentName())
                .distinct()
                .toList();

        return distinct;
    }

    private String parseStudentName(String fileName)
    {
        return fileName.replace(".csv", "")
                        .replace("_", " ")
                        .substring(10);
    }
}
