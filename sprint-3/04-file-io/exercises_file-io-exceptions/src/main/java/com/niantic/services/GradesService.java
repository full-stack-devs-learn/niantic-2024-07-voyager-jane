package com.niantic.services;

import com.niantic.models.Assignment;

import java.util.List;
import java.util.Map;

public interface GradesService
{
    String[] getFileNames();

    List<Assignment> getAssignments(String fileName);

    List<Assignment> getAllAssignments(String[] fileNames);

    Map<String, List<Assignment>> distinctAssignments(List<Assignment> assignments);
}
