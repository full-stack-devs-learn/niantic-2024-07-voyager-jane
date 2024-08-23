package com.niantic.models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest
{
    @ParameterizedTest
    @CsvSource ({
        "diamond, 3, 3",
        "heart, 4, 4",
        "spade, K, 1",
        "club, A, 2"
    })
    public void getSuitOrder_shouldReturn_correctOrder(String suitIn, String cardValueIn, String expectedIn)
    {
        // arrange
        Card card = new Card(suitIn, cardValueIn);

        int expectedSuitOrder = Integer.parseInt(expectedIn);

        // act
        int actualSuitOrder = card.getSuitOrder();

        // assert
        assertEquals(expectedSuitOrder, actualSuitOrder, "Card with " + card.getSuit() + " suit should have the order value of " + expectedIn);
    }

    @ParameterizedTest
    @CsvSource ({
        "diamond, 3, 3",
        "heart, 4, 4",
        "club, 5, 5",
        "spade, 6, 6",
        "diamond, 7, 7",
        "heart, 8, 8",
        "club, 9, 9",
        "spade, 10, 10",
        "heart, J, 11",
        "diamond, Q, 12",
        "spade, K, 13",
        "club, A, 14",
        "heart, 2, 15"
    })
    public void getValueOrder_shouldReturn_correctOrder(String suitIn, String cardValueIn, String expectedIn)
    {
        // arrange
        Card card = new Card(suitIn, cardValueIn);

        int expectedValueOrder = Integer.parseInt(expectedIn);

        // act
        int actualValueOrder = card.getValueOrder();

        // assert
        assertEquals(expectedValueOrder, actualValueOrder, "Card with " + card.getCardValue() + " face should have the order value of " + expectedIn);
    }

    @ParameterizedTest
    @CsvSource({
        "diamond, Q, false",
        "spade, 8, false",
        "heart, 2, true"
    })
    public void isBomb_shouldReturn_correctBoolean(String suitIn, String cardValueIn, String expectedIn)
    {
        // arrange
        Card card = new Card(suitIn, cardValueIn);

        boolean expectedBoolean = Boolean.parseBoolean(expectedIn);

        // act
        boolean actualBoolean = card.isBomb();

        // assert
        assertEquals(expectedBoolean, actualBoolean, "Only cards with 2 face value is a Bomb - Card with " + card.getCardValue() + " face should return " + expectedIn);
    }
}