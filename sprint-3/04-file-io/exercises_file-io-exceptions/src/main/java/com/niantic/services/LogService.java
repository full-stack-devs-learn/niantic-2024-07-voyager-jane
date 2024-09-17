package com.niantic.services;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LogService
{
    private String logType;

    public LogService(String logType)
    {
        ensureDirectoryExists(logType);

        this.logType = logType;
    }

    public File getLogFile()
    {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = logType + "/" + today.format(dateFormatter) + ".log";

        return new File(fileName);
    }

    public void createLogEntry(String message)
    {
        LocalDate dateTime = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String entry = dateTime.format(dateTimeFormatter) + " " + message;
    }

    private void ensureDirectoryExists(String filePath)
    {
        File directory = new File(filePath);

        if (!directory.exists()) directory.mkdir();
    }
}
