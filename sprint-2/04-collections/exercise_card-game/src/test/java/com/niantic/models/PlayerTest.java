package com.niantic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest
{
    private Player player;
    private Hand pile1;
    private Hand pile2;

    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    private Card card6;
    private Card card7;
    private Card card8;
    private Card card9;
    private Card card10;
    private Card card11;
    private Card card12;

    private ArrayList<Card> pair1;
    private ArrayList<Card> pair2;
    private ArrayList<Card> pilePair1;
    private ArrayList<Card> pilePair2;

    private ArrayList<Card> straight1;
    private ArrayList<Card> straight2;
    private ArrayList<Card> straight3;
    private ArrayList<Card> pileStraight1;

    @BeforeEach
    public void setup()
    {
        player = new Player("Bob");
        pile1 = new Hand();
        pile2 = new Hand();

        card1 = new Card("spade", "8");
        card2 = new Card("heart", "8");
        card3 = new Card("diamond", "9");
        card4 = new Card("diamond", "10");
        card5 = new Card("club", "J");

        card6 = new Card("diamond", "7");
        card7 = new Card("spade", "7");
        card8 = new Card("club", "8");
        card9 = new Card("diamond", "8");
        card10 = new Card("heart", "9");
        card11 = new Card("club", "10");
        card12 = new Card("spade", "J");


        pair1 = new ArrayList<>(){{
            add(card1);
            add(card2);
        }};

        pair2 = new ArrayList<>(){{
            add(card5);
            add(card1);
        }};


        straight1 = new ArrayList<>() {{
            add(card3);
            add(card5);
            add(card2);
            add(card4);
        }};

        straight2 = new ArrayList<>() {{
            add(card3);
            add(card5);
            add(card2);
        }};

        straight3 = new ArrayList<>() {{
            add(card3);
            add(card1);
            add(card2);
        }};

        pileStraight1 = new ArrayList<>() {{
            add(card8);
        }};
    }

    @Test
    public void isPair_shouldReturn_ifCardsHaveSameValues()
    {
        // arrange
        // act
        boolean boolPair1 = player.isPair(pair1);
        boolean boolPair2 = player.isPair(pair2);

        // assert
        assertTrue(boolPair1, "Cards with the same card value should be considered a pair.");
        assertFalse(boolPair2, "Cards with differing card values should not be considered a pair.");
    }

    @Test
    public void isStraight_shouldReturn_ifCardsAreInConsecutiveOrder()
    {
        // arrange
        // act
        boolean boolStraight1 = player.isStraight(straight1);
        boolean boolStraight2 = player.isStraight(straight2);;
        boolean boolStraight3 = player.isStraight(straight3);;

        // assert
        assertTrue(boolStraight1, "Several cards that can be sorted into consecutive order with no duplicates is considered a straight.");
        assertFalse(boolStraight2, "Several cards that cannot be sorted into consecutive order is not considered a straight.");
        assertFalse(boolStraight3, "Several cards with duplicates is not considered a straight.");
    }

    @Test
    public void playSingle_should_removeCard_FromPlayerHandToPutIntoPile()
    {

    }

    @Test
    public void playPair_should_removePickedCards_FromPlayerHandToPutIntoPile()
    {

    }
}
