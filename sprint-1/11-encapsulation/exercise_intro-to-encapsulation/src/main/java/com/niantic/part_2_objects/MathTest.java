package com.niantic.part_2_objects;

public class MathTest
{
    private int score;
    private int possiblePoints;
    private String studentName;

    public MathTest(int possiblePoints, String studentName)
    {
        this.possiblePoints = possiblePoints;
        this.studentName = studentName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getPossiblePoints()
    {
        return this.possiblePoints;
    }

    public String getStudentName()
    {
        return this.studentName;
    }

    public int getPercent()
    {
        double percent = (double)score/this.possiblePoints * 100;
        return (int) percent;
    }

    public String getLetterGrade()
    {
        if (getPercent() >= 90)
        {
            return "A";
        }

        else if (getPercent() >= 80 && getPercent() <= 89)
        {
            return "B";
        }

        else if (getPercent() >= 70 && getPercent() <= 79)
        {
            return "C";
        }

        else if (getPercent() >= 60 && getPercent() <= 69)
        {
            return "D";
        }

        else
        {
            return "F";
        }
    }
}
