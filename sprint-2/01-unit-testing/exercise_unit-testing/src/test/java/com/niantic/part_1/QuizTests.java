package com.niantic.part_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizTests
{
    private Quiz quiz1;
    private Quiz quiz2;
    private Quiz quiz3;
    private Quiz quiz4;
    private Quiz quiz5;

    private Quiz zeroQuiz;
    private Quiz negativeQuiz;

    @BeforeEach
    public void setup()
    {
        quiz1 = new Quiz(100, "John");
        quiz2 = new Quiz(56, "Katherine");
        quiz3 = new Quiz(83, "Ivan");
        quiz4 = new Quiz(100, "Mary");
        quiz5 = new Quiz(100, "Courtney");

        zeroQuiz = new Quiz(0, "Larry");
        negativeQuiz = new Quiz(-50, "Amy");
    }

    @Test
    public void newQuiz_shouldMakeSurePossiblePointsArePositive_whenCreated()
    {
        // arrange

        // making sure negative inputs are changed to default positive value
        int expectedPossiblePoints = 100;

        // act
        int actualPossiblePoints1 = zeroQuiz.getPossiblePoints();
        int actualPossiblePoints2 = negativeQuiz.getPossiblePoints();

        // assert
        assertEquals(expectedPossiblePoints, actualPossiblePoints1, "0 as PossiblePoints value should default to 100.");
        assertEquals(expectedPossiblePoints, actualPossiblePoints2, "Negative PossiblePoints value should default to 100.");
    }

    @Test
    public void setScore_shouldBePositive_whenSetterIsCalled()
    {
        // arrange
        int expectedScore = 0;

        // act
        zeroQuiz.setScore(0);
        negativeQuiz.setScore(-300);

        int actualZeroScore = zeroQuiz.getScore();
        int actualNegativeScore = negativeQuiz.getScore();

        // assert
        assertEquals(expectedScore, actualZeroScore, "Setting score to 0, should set score to 0.");
        assertEquals(expectedScore, actualNegativeScore, "Setting score to a negative value, should set score to 0.");
    }

    @Test
    public void getPercentage_ShouldCalculatePercentageCorrectly_whenCalled()
    {
        // arrange
        quiz1.setScore(68);
        quiz2.setScore(56);
        quiz3.setScore(71);
        quiz4.setScore(0);


        int expectedPercentage1 = 68;
        int expectedPercentage2 = 100;
        int expectedPercentage3 = 86;
        int expectedPercentage4 = 0;


        // act
        int actualPercentage1 = quiz1.getPercent();
        int actualPercentage2 = quiz2.getPercent();
        int actualPercentage3 = quiz3.getPercent();
        int actualPercentage4 = quiz4.getPercent();

        // assert
        assertEquals(expectedPercentage1, actualPercentage1, "68/100 score should return 68");
        assertEquals(expectedPercentage2, actualPercentage2, "56/56 score should return 100");
        assertEquals(expectedPercentage3, actualPercentage3, "71/83 score should return 86");
        assertEquals(expectedPercentage4, actualPercentage4, "0/100 score should return 0");
    }

    @Test
    public void getLetterGrade_shouldReturnCorrectGrade_whenCalled()
    {
        // arrange
        quiz1.setScore(52); // 52/100
        quiz2.setScore(56); // 56/56
        quiz3.setScore(71); // 71/83
        quiz4.setScore(70); // 78/100
        quiz5.setScore(63); // 63/100

        String expectedLetterGrade1 = "F";
        String expectedLetterGrade2 = "A";
        String expectedLetterGrade3 = "B";
        String expectedLetterGrade4 = "C";
        String expectedLetterGrade5 = "D";

        // act
        String actualLetterGrade1 = quiz1.getLetterGrade();;
        String actualLetterGrade2 = quiz2.getLetterGrade();;
        String actualLetterGrade3 = quiz3.getLetterGrade();;
        String actualLetterGrade4 = quiz4.getLetterGrade();;
        String actualLetterGrade5 = quiz5.getLetterGrade();;

        // assert
        assertEquals(expectedLetterGrade1, actualLetterGrade1, "52 / 100 should have letter grade ''F''");
        assertEquals(expectedLetterGrade2, actualLetterGrade2, "56 / 56 should have letter grade ''A''");
        assertEquals(expectedLetterGrade3, actualLetterGrade3, "71 / 83 should have letter grade ''B''");
        assertEquals(expectedLetterGrade4, actualLetterGrade4, "70 / 100 should have letter grade ''C''");
        assertEquals(expectedLetterGrade5, actualLetterGrade5, "63 / 100 should have letter grade ''D''");

    }
}