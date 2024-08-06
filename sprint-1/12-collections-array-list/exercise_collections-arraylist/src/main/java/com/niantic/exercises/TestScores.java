package com.niantic.exercises;

import com.niantic.models.TestScore;

import java.util.ArrayList;

public class TestScores
{

    /*
    1.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores and the name of a test - return all scores for the
        requested tests.
     */
    public ArrayList<TestScore> getScoresByTest(ArrayList<TestScore> testScores, String testName)
    {
        var byTestScore = new ArrayList<TestScore>();

        for (var test : testScores)
        {
            if (test.getTestName() == testName)
            {
                byTestScore.add(test);
            }
        }

        return byTestScore;
    }

    /*
    2.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores and the name of a student - return all scores for the
        requested student.
     */
    public ArrayList<TestScore> getScoresByStudent(ArrayList<TestScore> testScores, String student)
    {
        var studentTestScores = new ArrayList<TestScore>();

        for (var test : testScores)
        {
            if (test.getStudentName() == student)
            {
                studentTestScores.add(test);
            }
        }

        return studentTestScores;
    }

    /*
    3.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the highest score.
     */
    public int getHighestScore(ArrayList<TestScore> testScores)
    {
        int max = 0;

        for (var test : testScores)
        {
            if (test.getScore() > max)
            {
                max = test.getScore();
            }
        }

        return max;
    }

    /*
    4.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the lowest score.
     */
    public int getLowestScore(ArrayList<TestScore> testScores)
    {
        int min = Integer.MAX_VALUE;

        for (var test : testScores)
        {
            if (test.getScore() < min)
            {
                min = test.getScore();
            }
        }

        return min;
    }

    /*
    5.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the lowest score.
     */
    public int getAverageScore(ArrayList<TestScore> testScores)
    {
        int sum = 0;

        for (var test : testScores)
        {
            sum += test.getScore();
        }

        return sum / testScores.size();
    }

    /*
    6.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the highest score for the specified test name.
     */
    public int getHighestScoreByTest(ArrayList<TestScore> testScores, String testName)
    {
        int maxTest = 0;

        for (var test : testScores)
        {
            if (test.getTestName() == testName && test.getScore() > maxTest)
            {
                maxTest = test.getScore();
            }
        }

        return maxTest;
    }

    /*
    7.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the lowest score for the specified test name.
     */
    public int getLowestScoreByTest(ArrayList<TestScore> testScores, String testName)
    {
        int minTest = Integer.MAX_VALUE;

        for (var test : testScores)
        {
            if (test.getTestName() == testName && test.getScore() < minTest)
            {
                minTest = test.getScore();
            }
        }

        return minTest;
    }

    /*
    8.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the average score for the specified test name.
     */
    public int getAverageScoreByTest(ArrayList<TestScore> testScores, String testName)
    {
        int sumTest = 0;
        int testSize = 0;

        for (var test: testScores)
        {
            if (test.getTestName() == testName)
            {
                sumTest += test.getScore();
                testSize ++;
            }
        }

        return sumTest / testSize;
    }

    /*
    9.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the highest score for the specified student.
     */
    public int getHighestScoreByStudent(ArrayList<TestScore> testScores, String student)
    {
        int maxStudentTest = 0;

        for (var test: testScores)
        {
            if (test.getStudentName() == student && test.getScore() > maxStudentTest)
            {
                maxStudentTest = test.getScore();
            }
        }

        return maxStudentTest;
    }

    /*
    10.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the lowest score for the specified student.
     */
    public int getLowestScoreByStudent(ArrayList<TestScore> testScores, String student)
    {
        int minStudentTest = Integer.MAX_VALUE;

        for (var test: testScores)
        {
            if (test.getStudentName() == student && test.getScore() < minStudentTest)
            {
                minStudentTest = test.getScore();
            }
        }

        return minStudentTest;
    }

    /*
    11.  An ArrayList of TestScores contains test results for all students and all of their tests
        Given an input of All testScores return the average score for the specified student.
     */
    public int getAverageScoreByStudent(ArrayList<TestScore> testScores, String student)
    {
        int sumStudentAvg = 0;
        int testSizeStudent = 0;

        for (var test : testScores)
        {
            if (test.getStudentName() == student)
            {
                sumStudentAvg += test.getScore();
                testSizeStudent++;
            }
        }

        return sumStudentAvg / testSizeStudent;
    }
}
