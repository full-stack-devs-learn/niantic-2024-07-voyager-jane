package com.niantic.application;

import com.niantic.models.Assignment;
import com.niantic.services.*;
import com.niantic.ui.UserInput;

import java.util.*;

public class GradingApplication implements Runnable
{
    private GradesService gradesService = new GradesFileService();
    private ReportsService reportsService = new ReportsService("reports");
    private StatisticsService statsService = new StatisticsService();
    private LogService applicationLogger = new LogService("application");

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
                case 6:
                    createStudentSummaryReport();
                    UserInput.displayUserContinue();
                    break;
                case 7:
                    createAllStudentsReport();
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

        applicationLogger.createLogEntry("Display List of Files");

        UserInput.displayAllFiles(files);
    }

    private void displayFileScores()
    {
        // todo: 2 - allow the user to select a file name
        // load all student assignment scores from the file - display all files
        // choose file and returning list of assignments

        List<Assignment> assignments = chooseFile();

        applicationLogger.createLogEntry("Display Student Assignments and Scores for " + selectedFile);

        UserInput.displayStudentHeader(parseStudentName(selectedFile));
        UserInput.displayStudentScores(parseStudentName(selectedFile), assignments);
    }

    private void displayStudentAverages()
    {
        // todo: 3 - allow the user to select a file name
        // load all student assignment scores from the file - display student statistics (low score, high score, average score)

        List<Assignment> assignments = chooseFile();
        List<Integer> stats = statsService.calculateStatistics(assignments);
        Map<String, List<Assignment>> mapStatsToAssignments = statsService.mapAssignmentsToStatistics(stats, assignments);

        applicationLogger.createLogEntry("Display Student Statistics for " + selectedFile);

        UserInput.displayStudentHeader(parseStudentName(selectedFile));
        UserInput.displayStudentStatistics(stats, mapStatsToAssignments);
    }

    private void displayAllStudentStatistics()
    {
        // todo: 4 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for all scores (low score, high score, average score, number of students, number of assignments)

        String[] files = gradesService.getFileNames();
        List<Assignment> assignments = gradesService.getAllAssignments(files);
        List<Integer> stats = statsService.calculateStatistics(assignments);
        Map<String, List<Assignment>> mapStatsToAssignments = statsService.mapAssignmentsToStatistics(stats, assignments);

        int numStudents = files.length;
        int numAssignments = gradesService.distinctAssignments(assignments).size();

        applicationLogger.createLogEntry("Display All Student Statistics for All Assignments");

        UserInput.displayAllStudentsHeader(numStudents, numAssignments);
        UserInput.displayAllStudentsStatistics(stats, mapStatsToAssignments);
    }

    private void displayAssignmentStatistics()
    {
        // todo: 5 - Optional / Challenge - load all scores from all student and all assignments
        // display the statistics for each assignment (assignment name, low score, high score, average score)
        // this one could take some time

        String[] files = gradesService.getFileNames();
        List<Assignment> assignments = gradesService.getAllAssignments(files);
        Map<String, List<Assignment>> distinctAssignmentNames = gradesService.distinctAssignments(assignments);

        applicationLogger.createLogEntry("Display Statistics for Each Assignment");

        for (String key : distinctAssignmentNames.keySet())
        {
            List<Assignment> checkAssignments = distinctAssignmentNames.get(key);
            List<Integer> stats = statsService.calculateStatistics(checkAssignments);
            Map<String, List<Assignment>> mapStatsToAssignments = statsService.mapAssignmentsToStatistics(stats, checkAssignments);

            UserInput.displayAssignmentHeader(key, files.length);
            UserInput.displayAllStudentsStatistics(stats, mapStatsToAssignments);
            UserInput.displayUserContinue();
        }
    }

    private void createStudentSummaryReport()
    {
        List<Assignment> assignments = chooseFile();

        applicationLogger.createLogEntry("Create Student Summary Report for " + selectedFile);

        reportsService.createStudentSummaryReport(parseStudentName(selectedFile), assignments);
    }

    private void createAllStudentsReport()
    {
        String[] fileNames = gradesService.getFileNames();
        List<Assignment> assignments = gradesService.getAllAssignments(fileNames);

        applicationLogger.createLogEntry("Create All Student Statistics Report");

        reportsService.createAllStudentsReport(assignments);
    }

    private List<Assignment> fileSelectionCase()
    {
        while(true)
        {
            displayAllFiles();
            UserInput.displayMessage("What file would you like to choose?");

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
        // choose file and returning list of assignments
        List<Assignment> assignments = fileSelectionCase();

        return assignments;
    }

    private String parseStudentName(String fileName)
    {
        return fileName.replace(".csv", "")
                        .replace("_", " ")
                        .substring(10);
    }
}
