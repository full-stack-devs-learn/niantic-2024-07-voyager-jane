package com.niantic.part_3_challenge;

public class Refrigerator
{
    private int maxCapacity;

    private int currentTemperature;
    private int availableCapacity;
    private boolean isDoorOpen;

    public Refrigerator(int currentTemperature, int maxCapacity)
    {
        this.currentTemperature = currentTemperature;
        this.maxCapacity = maxCapacity;
        this.availableCapacity = maxCapacity;
    }

    public int getCurrentTemperature()
    {
        return this.currentTemperature;
    }

    public int getMaxCapacity()
    {
        return this.maxCapacity;
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

    public boolean addItem(int capacity)
    {
        if (isDoorOpen())
        {
            if (getAvailableCapacity() - capacity >= 0)
            {
                this.availableCapacity -= capacity;
                return true;
            }
        }
        return false;
    }

    public boolean removeItem(int capacity)
    {
        if (isDoorOpen())
        {
            if (getAvailableCapacity() + capacity != this.maxCapacity)
            {
                this.availableCapacity += capacity;
                return true;
            }
        }
        return true;
    }
}
