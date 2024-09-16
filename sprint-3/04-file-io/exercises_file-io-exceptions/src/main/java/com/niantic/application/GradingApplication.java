package com.niantic.application;

import com.niantic.models.Assignment;
import com.niantic.services.GradesFileService;
import com.niantic.services.GradesService;
import com.niantic.ui.UserInput;

import java.util.Arrays;
import java.util.List;

public class GradingApplication implements Runnable
{
    private GradesService gradesService = new GradesFileService();
    private int countFile = 0;

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
                    break;
                case 4:
                    displayAllStudentStatistics();
                    break;
                case 5:
                    displayAssignmentStatistics();
                    break;
                case 0:
                    UserInput.displayMessage("Goodbye");
                    System.exit(0);
                default:
                    UserInput.displayMessage("Please make a valid selection");
            }
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
                    return gradesService.getAssignments(fileName);
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
        System.out.println();
        System.out.println("What file would you like to choose?");
        System.out.println("-".repeat(35));
        displayAllFiles();

        // choose file and returning list of assignments
        System.out.println();
        List<Assignment> assignments = fileSelectionCase();

        // printing student name
        System.out.println();
        System.out.printf("Student Name: %s %s", assignments.get(0).getFirstName().toUpperCase(), assignments.get(0).getLastName().toUpperCase());
        System.out.println();
        System.out.println("-".repeat(45));

        // printing assignments
        System.out.printf("Assignment" + " ".repeat(20) + "Score");
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

    }

    private void displayAllStudentStatistics()
    {
        // todo: 4 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for all scores (low score, high score, average score, number of students, number of assignments)
    }

    private void displayAssignmentStatistics()
    {
        // todo: 5 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for each assignment (assignment name, low score, high score, average score)
        // this one could take some time
    }

    private String parseStudentName(String fileName)
    {
        return fileName.replace(".csv", "")
                        .replace("_", " ")
                        .substring(10);
    }
}
