package com.niantic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest
{
    private Player player;
    private Hand pile;

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

    private ArrayList<Card> straight1;
    private ArrayList<Card> straight2;
    private ArrayList<Card> straight3;

    @BeforeEach
    public void setup()
    {
        player = new Player("Bob");
        pile = new Hand();

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

//        player.dealTo(card1);
//        player.dealTo(card2);
//        player.dealTo(card3);
//        player.dealTo(card4);
//        player.dealTo(card5);
//
//        pile.dealTo(card6);
//        pile.dealTo(card7);
//        pile.dealTo(card8);
//        pile.dealTo(card9);
//        pile.dealTo(card10);
//        pile.dealTo(card11);
//        pile.dealTo(card12);


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
        // arrange
        int expectedHandSize = 2;
        int expectedPileSize = 4;

        player.dealTo(card1);
        player.dealTo(card3);
        player.dealTo(card4);

        pile.dealTo(card12);
        pile.dealTo(card11);
        pile.dealTo(card6);

        // act
        Card card = player.getHand().findCard("spade", "8");

        player.playSingle(pile, card);

        boolean inHand = player.getHand().findCard("spade", "8") != null;
        boolean inPile = pile.findCard("spade", "8") != null;
        int actualHandSize = player.getHand().getCardCount();
        int actualPileSize = pile.getCardCount();

        // assert
        assertFalse(inHand, "Card should not be in hand after playing a valid single.");
        assertTrue(inPile, "Card should be in pile after player places a valid single.");
        assertEquals(expectedHandSize, actualHandSize, "Player's card count should lower after placing a card in the pile.");
        assertEquals(expectedPileSize, actualPileSize, "Pile's card count should rise after player places a card in the pile.");
    }

    @Test
    public void playSingle_shouldNot_removeCard_FromPlayerHandIfSmallerValueThanPile()
    {

    }

    @Test
    public void playPair_should_removePickedCards_FromPlayerHandToPutIntoPile()
    {

    }

    @Test
    public void playStraight_should_removePickedCards_FromPlayerHandToPutIntoPile()
    {

    }
}
