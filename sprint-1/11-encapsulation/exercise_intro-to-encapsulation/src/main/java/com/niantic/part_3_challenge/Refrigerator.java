package com.niantic.part_3_challenge;

public class Refrigerator
{
    private final int MAX_CAPACITY;

    private int currentTemperature;
    private int availableCapacity;
    private boolean isDoorOpen = false;

    public Refrigerator(int currentTemperature, int maxCapacity)
    {
        this.currentTemperature = currentTemperature;
        this.MAX_CAPACITY = maxCapacity;
        this.availableCapacity = maxCapacity;
    }

    public int getCurrentTemperature()
    {
        return this.currentTemperature;
    }

    public int getMaxCapacity()
    {
        return this.MAX_CAPACITY;
    }

    public int getAvailableCapacity()
    {
        return this.availableCapacity;
    }

    public boolean isDoorOpen()
    {
        return this.isDoorOpen;
    }

    public void openDoor()
    {
        this.isDoorOpen = true;
    }

    public void closeDoor()
    {
        this.isDoorOpen = false;
    }

    public void addItem(int capacity)
    {
        if (isDoorOpen)
        {
            if (getAvailableCapacity() - capacity != 0)
            {
                this.availableCapacity -= capacity;
            }
        }
        else
        {
            return;
        }
    }

    public void removeItem(int capacity)
    {
        if (isDoorOpen())
        {
            if (getAvailableCapacity() + capacity != this.MAX_CAPACITY)
            {
                this.availableCapacity += capacity;
            }
        }
    }
}
