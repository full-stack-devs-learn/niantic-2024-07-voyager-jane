package com.niantic.services;

import com.niantic.models.Assignment;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GradesFileService implements GradesService
{

    @Override
    public String[] getFileNames()
    {
        String[] files = new String[0];

        try {
            File directory = new File("files");
            files = directory.list();
            Arrays.sort(files);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
}
