package com.niantic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest
{
    private Hand hand;
    private Hand pile;

    private Card heartA;
    private Card diamondA;
    private Card clubA;
    private Card spadeA;

    private Card heart5;
    private Card diamond5;
    private Card club5;
    private Card spade5;

    private Card heart8;
    private Card diamond8;
    private Card club8;
    private Card spade8;

    @BeforeEach
    public void setup()
    {
        hand = new Hand();
        pile = new Hand();

        heartA = new Card("heart", "A");
        diamondA = new Card("diamond", "A");
        clubA = new Card("club", "A");
        spadeA = new Card("spade", "A");

        heart5 = new Card("heart", "5");
        diamond5 = new Card("diamond", "5");
        club5 = new Card("club", "5");
        spade5 = new Card("spade", "5");

        heart8 = new Card("heart", "8");
        diamond8 = new Card("diamond", "8");
        club8 = new Card("club", "8");
        spade8 = new Card("spade", "8");
    }

    @Test
    public void findCard_should_ReturnCardFromInputs_IfPresentInHand()
    {
        // arrange

        // act
        hand.dealTo(heart5);
        hand.dealTo(spadeA);

        Card card1 = hand.findCard("heart", "5");
        Card card2 = hand.findCard("spade", "A");
        Card card3 = hand.findCard("diamond", "8");

        boolean actual1 = card1 != null;
        boolean actual2 = card2 != null;
        boolean actual3 = card3 != null;

        // assert
        assertTrue(actual1, "If card is in hand, findCard() returns true.");
        assertTrue(actual2, "If card is in hand, findCard() returns true.");
        assertFalse(actual3, "If card is not in hand, findCard() returns false.");
    }

    @Test
    public void dealTo_should_addCardToHand()
    {
        // arrange

        // act
        hand.dealTo(heart5);
        hand.dealTo(diamondA);
        hand.dealTo(spade8);

        // assert
        assertTrue(hand.getCards().contains(heart5), "Hand should have Heart 5 after adding card.");
        assertTrue(hand.getCards().contains(diamondA), "Hand should have Diamond A after adding card.");
        assertTrue(hand.getCards().contains(spade8), "Hand should have Spade 8 after adding card.");
    }

    @Test
    public void removeCard_should_removeCardFromHand()
    {
        // arrange
        int expectedHandSize1 = 6;
        int expectedHandSize2 = 4;
        int expectedHandSize3 = 1;

        hand.dealTo(heart5);
        hand.dealTo(diamondA);
        hand.dealTo(spade8);
        hand.dealTo(club5);
        hand.dealTo(heart8);
        hand.dealTo(diamond5);

        // act
        int actualHandSize1 = hand.getCardCount();
        int actualHandSize2;
        int actualHandSize3;

        hand.removeCard(heart5);
        hand.removeCard(spade8);
        actualHandSize2 = hand.getCardCount();

        hand.removeCard(club5);
        hand.removeCard(diamondA);
        hand.removeCard(heart8);
        actualHandSize3 = hand.getCardCount();

        // assert
        assertEquals(expectedHandSize1, actualHandSize1, "Card count in hand should be 6 since no cards were removed.");
        assertEquals(expectedHandSize2, actualHandSize2, "Card count in hand should be 4 since 2 cards were removed.");
        assertEquals(expectedHandSize3, actualHandSize3, "Card count in hand should be 1 since 3 cards were removed.");
    }

    @Test
    public void placeInPile_should_placeCardInPileHand()
    {
        // arrange
        hand.dealTo(heart5);
        hand.dealTo(diamondA);
        hand.dealTo(spade8);

        // act
        hand.placeInPile(pile, heart5);
        hand.placeInPile(pile, diamondA);
        hand.placeInPile(pile, spade8);

        // assert
        assertTrue(pile.getCards().contains(heart5), "Pile should have Heart 5 after hand places card down in pile.");
        assertTrue(pile.getCards().contains(diamondA), "Pile should have Heart 5 after hand places card down in pile.");
        assertTrue(pile.getCards().contains(spade8), "Pile should have Heart 5 after hand places card down in pile.");

        assertFalse(hand.getCards().contains(heart5), "Hand should not have Heart 5 after placing card in pile");
        assertFalse(hand.getCards().contains(diamondA), "Hand should not have Diamond A after placing card in pile");
        assertFalse(hand.getCards().contains(spade8), "Hand should not have Spade 8 after placing card in pile");
    }

    @Test
    public void sortHand_should_sortCards_inOrderBySuiteAndValue()
    {
        // arrange
        ArrayList<Card> compare = new ArrayList<>() {{
            add(spade5);
            add(club5);
            add(diamond5);
            add(heart5);
            add(spade8);
            add(club8);
            add(diamond8);
            add(heart8);
            add(spadeA);
            add(clubA);
            add(diamondA);
            add(heartA);
        }};

        hand.dealTo(heart5);
        hand.dealTo(diamondA);
        hand.dealTo(spade8);
        hand.dealTo(club5);
        hand.dealTo(heart8);
        hand.dealTo(diamond5);
        hand.dealTo(heartA);
        hand.dealTo(spade5);
        hand.dealTo(spadeA);
        hand.dealTo(diamond8);
        hand.dealTo(club8);
        hand.dealTo(clubA);

        // act
        var sorted = hand.sortHand(hand.getCards());

        boolean inOrder = true;

        for (int i = 0; i < hand.getCardCount() - 1; i++)
        {
            if (sorted.get(i) != compare.get(i))
            {
                inOrder = false;
            }
        }

        // assert
        assertTrue(inOrder, "Sorting hand by suit and value did not work.");
    }
}
