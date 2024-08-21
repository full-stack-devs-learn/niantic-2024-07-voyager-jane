package com.niantic.exercises;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.niantic.models.LineItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestBase
{
    public static List<LineItem> loadOrders()
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            return objectMapper.readValue(new File("data/line-items.json"), new TypeReference<ArrayList<LineItem>>(){});
        }
        catch(Exception e)
        {
            return new ArrayList<>();
        }
    }
}