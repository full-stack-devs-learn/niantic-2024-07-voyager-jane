package com.niantic.services;

import com.niantic.models.Assignment;

import java.io.File;
import java.sql.Array;
import java.util.*;

public class GradesFileService implements GradesService
{
    private LogService errorLogger = new LogService("error");

    @Override
    public String[] getFileNames()
    {
        String[] files = new String[0];
        File directory = new File("files");

        try
        {
            files = directory.list();
            Arrays.sort(files);
        }
        catch (Exception e)
        {
            errorLogger.createLogEntry(e.getMessage());
            System.out.println("There was an error in finding the file names.");
        }

        return files;
    }

    @Override
    public List<Assignment> getAssignments(String fileName)
    {
        File file = new File("files/" + fileName);
        List<Assignment> assignments = new ArrayList<>();

        try (Scanner reader = new Scanner(file))
        {
            // skip header
            reader.nextLine();

            while (reader.hasNextLine())
            {
                String line = reader.nextLine();
                var columns = line.split(",");

                int number = Integer.parseInt(columns[0]);
                String firstName = columns[1];
                String lastName = columns[2];
                String assignmentName = columns[3];
                int score = Integer.parseInt(columns[4]);

                Assignment assignment = new Assignment(number, firstName, lastName, assignmentName, score);
                assignments.add(assignment);
            }
        }
        catch (Exception e)
        {
            errorLogger.createLogEntry(e.getMessage());
            System.out.println("There was an error in finding the assignments.");
        }

        return assignments;
    }

    @Override
    public List<Assignment> getAllAssignments(String[] fileNames)
    {
        List<Assignment> allAssignments = new ArrayList<>();

        for (String file : fileNames)
        {
            allAssignments.addAll(getAssignments(file));
        }

        return allAssignments;
    }

    @Override
    public Map<String, List<Assignment>> distinctAssignments(List<Assignment> assignments)
    {
        Map<String, List<Assignment>> mapAssignments = new LinkedHashMap<>();

//        List<String> distinct = assignments.stream()
//                .map(assignment -> assignment.getAssignmentName())
//                .distinct()
//                .toList();

        for (Assignment assignment : assignments)
        {
            String name = assignment.getAssignmentName();

            if (!mapAssignments.containsKey(name))
            {
                mapAssignments.put(name, new ArrayList<>());
            }
            else
            {
                mapAssignments.get(name).add(assignment);
            }
        }

        return mapAssignments;
    }
}