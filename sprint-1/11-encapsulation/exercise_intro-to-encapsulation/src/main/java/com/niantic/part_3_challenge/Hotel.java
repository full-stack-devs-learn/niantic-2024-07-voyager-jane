package com.niantic.part_3_challenge;

public class Hotel
{
    private final int NUMBER_OF_ROOMS;
    private final int NUMBER_OF_SUITES;
    private int bookedRooms;
    private int bookedSuites;

    public Hotel(int numberOfRooms, int numberOfSuites)
    {
        this.NUMBER_OF_ROOMS = numberOfRooms;
        this.NUMBER_OF_SUITES = numberOfSuites;
    }

    public Hotel(int numberOfRooms, int numberOfSuites, int bookedRooms, int bookedSuites)
    {
        this.NUMBER_OF_ROOMS = numberOfRooms;
        this.NUMBER_OF_SUITES = numberOfSuites;
        this.bookedRooms = bookedRooms;
        this.bookedSuites = bookedSuites;
    }

    public int getNumberOfRooms()
    {
        return this.NUMBER_OF_ROOMS;
    }

    public int getNumberOfSuites()
    {
        return this.NUMBER_OF_SUITES;
    }

    public int getBookedRooms()
    {
        return this.bookedRooms;
    }

    public int getBookedSuites()
    {
        return this.bookedSuites;
    }

    public int getAvailableRooms()
    {
        return getNumberOfRooms() - getBookedRooms();
    }

    public int getAvailableSuites()
    {
        return getNumberOfSuites() - getBookedSuites();
    }

    public boolean makeReservation(int numberOfRooms, boolean isSuite)
    {
        if (isSuite && numberOfRooms < getAvailableSuites())
        {
            this.bookedSuites += numberOfRooms;
            return true;
        }

        else if (!isSuite && numberOfRooms < getAvailableRooms())
        {
            this.bookedRooms += numberOfRooms;
            return true;
        }
        return false;
    }
}
