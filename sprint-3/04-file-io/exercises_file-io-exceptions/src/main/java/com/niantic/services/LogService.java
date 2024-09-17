package com.niantic.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public void createLogEntry(String entry)
    {
        var file = getLogFile();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (FileOutputStream stream = new FileOutputStream(file, true);
             PrintWriter out = new PrintWriter(stream))
        {
            out.printf("%s %s\n", dateTime.format(dateTimeFormatter), entry);
        }
        catch (Exception e)
        {
            System.out.println("Error in Creating Log Entry: " + e.getMessage());
        }
    }

    private void ensureDirectoryExists(String filePath)
    {
        File directory = new File(filePath);

        if (!directory.exists()) directory.mkdir();
    }
}
