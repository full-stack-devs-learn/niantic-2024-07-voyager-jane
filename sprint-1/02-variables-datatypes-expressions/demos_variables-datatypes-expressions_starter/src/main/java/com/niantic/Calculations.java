package com.niantic;

public class Calculations
{
    public static void main(String[] args)
    {

        // Calculate how many donuts per student
        int numberOfStudents = 4;
        int numberOfDonuts = 22;

        int donutsPerStudent = numberOfDonuts / numberOfStudents;
        double donutsPerStudentDouble = (double)numberOfDonuts / numberOfStudents;
        int remainingDonuts = numberOfDonuts % numberOfStudents;

        System.out.println("Number of Donuts per Student");
        System.out.println("Total Donuts:       " + numberOfDonuts);
        System.out.println("Total Students:     " + numberOfStudents);
        System.out.println("Donuts per Student: " + donutsPerStudent);
        System.out.println("Remaining Donuts:   " + remainingDonuts);
        System.out.println();
        System.out.println("Donuts per Student (double): " + donutsPerStudentDouble);
    }
}
