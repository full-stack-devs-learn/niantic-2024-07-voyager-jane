package com.niantic;

import java.util.Scanner;

public class Main
{
    private static Scanner userInput = new Scanner(System.in);
    private static int[] scores = new int[0];

    public static void main(String[] args)
    {
        while(true)
        {
            int choice = getHomeSelection();

            switch(choice)
            {
                case 1:
                    createNewTestScores();
                    break;
                case 2:
                    calculateAverage();
                    break;
                case 3:
                    findHighestScore();
                    break;
                case 4:
                    findLowestScore();
                    break;
                case 5:
                    displayTestScores();
                    break;
                case 6:
                    changeTestScore();
                    break;
                case 0:
                    System.out.println("Thanks for playing!");
                    System.out.println("Good bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection!");
                    break;
            }
        }
    }

    public static int getHomeSelection()
    {
        System.out.println();
        System.out.println("Welcome to The Gradebook!");
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("1) Enter new test scores");
        System.out.println("2) Calculate the class average");
        System.out.println("3) Find the highest score");
        System.out.println("4) Find the lowest score");
        System.out.println("5) Display test scores");
        System.out.println("6) Change a test score");
        System.out.println("0) Exit");

        System.out.print("Please select an option:");
        return Integer.parseInt(userInput.nextLine());
    }

    private static void createNewTestScores()
    {

        // "Enter code to create a new array and ask for test scores"
        System.out.println("How many test scores are you entering? ");
        int scoresSize = userInput.nextInt();
        userInput.nextLine();

        scores = new int[scoresSize];

        for (int index = 0; index < scoresSize; index++)
        {
            System.out.println("Please enter score " + (index + 1) + ": ");
            int score = userInput.nextInt();
            userInput.nextLine(); // nextLine to clear out enter key input?

            scores[index] = score;
        }

    }

    private static void calculateAverage()
    {
        // "Add logic to calculate the average of all test scores, and display it"
        System.out.println();
        if (scores.length == 0)
        {
            System.out.println("There are no test scores in the system. Please enter new test scores before continuing.");
        }

        else
        {
            int sum = 0;
            int avg;

            for (int score : scores)
            {
                sum += score;
            }

            avg = sum / scores.length;

            System.out.println("The average of all test scores is: " + avg);
        }

    }

    private static void findHighestScore()
    {
        // "Find the highest score of all tests and display it"
        if (scores.length == 0)
        {
            System.out.println("There are no test scores in the system. Please enter new test scores before continuing.");
        }

        else
        {
            int maxScore = scores[0];

            for (int score: scores)
            {
                if (score > maxScore)
                {
                    maxScore = score;
                }
            }

            System.out.println("The highest test score is: " + maxScore);
        }
    }

    private static void findLowestScore()
    {
        // "Find the lowest score of all tests and display it"

        if (scores.length == 0)
        {
            System.out.println("There are no test scores in the system. Please enter new test scores before continuing.");
        }

        else
        {
            int minScore = scores[0];

            for (int score: scores)
            {
                if (score < minScore)
                {
                    minScore = score;
                }
            }

            System.out.println("The lowest test score is: " + minScore);
        }
    }
    private static void displayTestScores()
    {
        if (scores.length == 0)
        {
            System.out.println("There are no test scores in the system. Please enter new test scores before continuing.");
        }

        else
        {
            System.out.println("Test Scores:");
            for (int score : scores)
            {
                if (score == scores[scores.length - 1])
                {
                    System.out.print(score);
                }
                else
                {
                    System.out.print(score + ", ");
                }
            }
        }
    }
    private static void changeTestScore()
    {
        if (scores.length == 0)
        {
            System.out.println("There are no test scores in the system. Please enter new test scores before continuing.");
        }
        else {
            System.out.println("Which test score would you like to change?");
            int scoreChange = userInput.nextInt();
            userInput.nextLine();

            boolean check = false;
            int indexNew = 0;

            for (int index = 0; index < scores.length; index++)
            {
                if (scores[index] == scoreChange)
                {
                    check = true;
                    indexNew = index;
                }
            }

            if (check)
            {
                System.out.println("What new test score will replace " + scoreChange + "?");
                int newScore = userInput.nextInt();
                userInput.nextLine();

                scores[indexNew] = newScore;
            }
            else
            {
                System.out.println("There is no " + scoreChange + " in the test scores provided. Please check what test scores are in the system.");
            }
        }
    }
}