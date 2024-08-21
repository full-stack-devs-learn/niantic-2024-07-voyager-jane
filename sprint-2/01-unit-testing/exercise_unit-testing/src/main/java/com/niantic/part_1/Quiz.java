package com.niantic.part_1;

public class Quiz
{
    private int score;
    private final int possiblePoints;
    private final String studentName;

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        if (score <= 0)
        {
            this.score = 0;
        }

        else
        {
            this.score = score;
        }
    }

    public int getPossiblePoints()
    {
        return possiblePoints;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public Quiz(int possiblePoints, String studentName)
    {
        if (possiblePoints <= 0)
        {
            this.possiblePoints = 100;
        }

        else
        {
            this.possiblePoints = possiblePoints;
        }

        this.studentName = studentName;
    }

    public int getPercent()
    {
        double decimalScore = ((double) score / possiblePoints) * 100;

        return (int) Math.round(decimalScore) ;
    }

    public String getLetterGrade()
    {
        int percent = getPercent();

        if(percent >= 90) return "A";
        else if(percent >= 80) return "B";
        else if(percent >= 70) return "C";
        else if(percent >= 60) return "D";
        else return "F";
    }
}
