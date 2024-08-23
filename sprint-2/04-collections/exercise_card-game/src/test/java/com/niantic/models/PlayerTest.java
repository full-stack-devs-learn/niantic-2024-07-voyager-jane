package com.niantic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
    private Card card13;
    private Card card14;
    private Card card15;


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

        card13 = new Card("heart", "10");
        card14 = new Card("club", "9");
        card15 = new Card("spade", "9");
    }

    @Test
    public void isPair_shouldReturn_ifCardsHaveSameValues()
    {
        // arrange
        ArrayList<Card> pair1 = new ArrayList<>(){{
            add(card1);
            add(card2);
        }};

        ArrayList<Card> pair2 = new ArrayList<>(){{
            add(card5);
            add(card1);
        }};

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
        ArrayList<Card> straight1 = new ArrayList<>() {{
            add(card3);
            add(card5);
            add(card2);
            add(card4);
        }};

        ArrayList<Card> straight2 = new ArrayList<>() {{
            add(card3);
            add(card5);
            add(card2);
        }};

        ArrayList<Card> straight3 = new ArrayList<>() {{
            add(card3);
            add(card1);
            add(card2);
        }};

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
    public void playSingle_should_removeCard_fromPlayerHand_toPutIntoPile()
    {
        // arrange
        int expectedHandSize = 1;
        int expectedPile1Size = 4;
        int expectedPile2Size = 3;

        player.dealTo(card1);
        player.dealTo(card2);
        player.dealTo(card4);

        pile1.dealTo(card12);
        pile1.dealTo(card11);
        pile1.dealTo(card6);

        pile2.dealTo(card9);
        pile2.dealTo(card8);

        Card card1 = player.getHand().findCard("spade", "8");
        Card card2 = player.getHand().findCard("heart", "8");

        // act
        player.playSingle(pile1, card1);
        player.playSingle(pile2, card2);

        boolean inHand1 = player.getHand().findCard("spade", "8") != null;
        boolean inHand2 = player.getHand().findCard("heart", "8") != null;
        boolean inPile1 = pile1.findCard("spade", "8") != null;
        boolean inPile2 = pile2.findCard("heart", "8") != null;
        int actualHandSize = player.getHand().getCardCount();
        int actualPile1Size = pile1.getCardCount();
        int actualPile2Size = pile2.getCardCount();

        // assert
        assertFalse(inHand1, "Card should not be in hand after playing a valid single.");
        assertFalse(inHand2, "Card should not be in hand after playing a valid single.");

        assertTrue(inPile1, "Card should be in pile after player places a valid single.");
        assertTrue(inPile2, "Card should be in pile after player places a valid single.");

        assertEquals(expectedHandSize, actualHandSize, "Player's card count should decrease by 1 after placing a card in the pile.");
        assertEquals(expectedPile1Size, actualPile1Size, "Pile's card count should increase by 1 after player places a card in the pile.");
        assertEquals(expectedPile2Size, actualPile2Size, "Pile's card count should increase by 1 after player places a card in the pile.");
    }

    @Test
    public void playSingle_shouldNot_removeCard_fromPlayerHand_ifSmallerValueThanPile()
    {
        // arrange
        int expectedHandSize = 3;
        int expectedPile1Size = 3;
        int expectedPile2Size = 1;

        player.dealTo(card1);
        player.dealTo(card3);
        player.dealTo(card4);

        pile1.dealTo(card10);
        pile1.dealTo(card11);
        pile1.dealTo(card12);

        pile2.dealTo(card9);

        Card card = player.getHand().findCard("spade", "8");

        // act
        player.playSingle(pile1, card);
        player.playSingle(pile2, card);

        boolean inHand = player.getHand().findCard("spade", "8") != null;
        boolean inPile1 = pile1.findCard("spade", "8") != null;
        boolean inPile2 = pile2.findCard("spade", "8") != null;
        int actualHandSize = player.getHand().getCardCount();
        int actualPile1Size = pile1.getCardCount();
        int actualPile2Size = pile2.getCardCount();

        // assert
        assertTrue(inHand, "Card should still be in hand if the value in the pile is larger.");

        assertFalse(inPile1, "Card should not be in pile if the value in the hand is smaller.");
        assertFalse(inPile2, "Card should not be in pile if the value in the hand is smaller.");

        assertEquals(expectedHandSize, actualHandSize, "Player's card count should stay the same if the chosen card's value is smaller than the pile's card value.");
        assertEquals(expectedPile1Size, actualPile1Size, "Player's card count should stay the same if the player card's value is smaller than the pile's card value.");
        assertEquals(expectedPile2Size, actualPile2Size, "Player's card count should stay the same if the player card's value is smaller than the pile's card value.");
    }

    @Test
    public void playPair_should_removeCards_fromPlayerHand_toPutIntoPile()
    {
        // arrange
        int expectedHandSize = 0;
        int expectedPile1Size = 6;
        int expectedPile2Size = 6;

        player.dealTo(card1);
        player.dealTo(card2);
        player.dealTo(card4);
        player.dealTo(card13);

        // same value - player has stronger suit test
        pile1.dealTo(card3);
        pile1.dealTo(card11);
        pile1.dealTo(card8);
        pile1.dealTo(card9);

        // different value - player has stronger value test
        pile2.dealTo(card5);
        pile2.dealTo(card10);
        pile2.dealTo(card6);
        pile2.dealTo(card7);

        Card cardHand1 = player.getHand().findCard("spade", "8");
        Card cardHand2 = player.getHand().findCard("heart", "8");
        Card cardHand3 = player.getHand().findCard("diamond", "10");
        Card cardHand4 = player.getHand().findCard("heart", "10");

        ArrayList<Card> cardPair1 = new ArrayList<>(){{
            add(cardHand1);
            add(cardHand2);
        }};

        ArrayList<Card> cardPair2 = new ArrayList<>() {{
            add(cardHand3);
            add(cardHand4);
        }};

        // act
        player.playPair(pile1, cardPair1);
        player.playPair(pile2, cardPair2);

        boolean inHand1 = player.getHand().findCard("spade", "8") != null;
        boolean inHand2 = player.getHand().findCard("heart", "8") != null;
        boolean inHand3 = player.getHand().findCard("diamond", "10") != null;
        boolean inHand4 = player.getHand().findCard("heart", "10") != null;
        boolean inPile1 = pile1.findCard("spade", "8") != null;
        boolean inPile2 = pile1.findCard("heart", "8") != null;
        boolean inPile3 = pile2.findCard("diamond", "10") != null;
        boolean inPile4 = pile2.findCard("heart", "10") != null;

        int actualHandSize = player.getHand().getCardCount();
        int actualPile1Size = pile1.getCardCount();
        int actualPile2Size = pile2.getCardCount();

        // assert
        assertFalse(inHand1, "Card should not be in player's hand if placed in the pile as a pair.");
        assertFalse(inHand2, "Card should not be in player's hand if placed in the pile as a pair.");
        assertFalse(inHand3, "Card should not be in player's hand if placed in the pile as a pair.");
        assertFalse(inHand4, "Card should not be in player's hand if placed in the pile as a pair.");

        assertTrue(inPile1, "Card should be in pile if played as a pair by player.");
        assertTrue(inPile2, "Card should be in pile if played as a pair by player.");
        assertTrue(inPile3, "Card should be in pile if played as a pair by player.");
        assertTrue(inPile4, "Card should be in pile if played as a pair by player.");

        assertEquals(expectedHandSize, actualHandSize, "Player's card count should decrease by 4 if both pairs are placed in pile.");
        assertEquals(expectedPile1Size, actualPile1Size, "Pile's card count should increase by 2 if pair from player's hand is placed in pile.");
        assertEquals(expectedPile2Size, actualPile2Size, "Pile's card count should increase by 2 if pair from player's hand is placed in pile.");
    }

    @Test
    public void playPair_shouldNot_removeCards_fromPlayerHand_IfPilePairLarger()
    {
        // arrange
        int expectedHandSize = 4;
        int expectedPile1Size = 4;
        int expectedPile2Size = 4;

        player.dealTo(card8);
        player.dealTo(card9);
        player.dealTo(card6);
        player.dealTo(card7);

        // same value - player has weaker suit test
        pile1.dealTo(card12);
        pile1.dealTo(card11);
        pile1.dealTo(card1);
        pile1.dealTo(card2);

        // different value - player has weaker value test
        pile2.dealTo(card3);
        pile2.dealTo(card10);
        pile2.dealTo(card4);
        pile2.dealTo(card13);

        Card cardHand1 = player.getHand().findCard("club", "8");
        Card cardHand2 = player.getHand().findCard("diamond", "8");
        Card cardHand3 = player.getHand().findCard("diamond", "7");
        Card cardHand4 = player.getHand().findCard("spade", "7");

        ArrayList<Card> cardPair1 = new ArrayList<>(){{
            add(cardHand1);
            add(cardHand2);
        }};

        ArrayList<Card> cardPair2 = new ArrayList<>() {{
            add(cardHand3);
            add(cardHand4);
        }};

        // act
        player.playPair(pile1, cardPair1);
        player.playPair(pile2, cardPair2);

        boolean inHand1 = player.getHand().findCard("club", "8") != null;
        boolean inHand2 = player.getHand().findCard("diamond", "8") != null;
        boolean inHand3 = player.getHand().findCard("diamond", "7") != null;
        boolean inHand4 = player.getHand().findCard("spade", "7") != null;
        boolean inPile1 = pile1.findCard("club", "8") != null;
        boolean inPile2 = pile1.findCard("diamond", "8") != null;
        boolean inPile3 = pile2.findCard("diamond", "7") != null;
        boolean inPile4 = pile2.findCard("spade", "7") != null;

        int actualHandSize = player.getHand().getCardCount();
        int actualPile1Size = pile1.getCardCount();
        int actualPile2Size = pile2.getCardCount();

        // assert
        assertTrue(inHand1, "Card should be in player's hand if pair is not larger than the pile's pair.");
        assertTrue(inHand2, "Card should be in player's hand if pair is not larger than the pile's pair.");
        assertTrue(inHand3, "Card should be in player's hand if pair is not larger than the pile's pair.");
        assertTrue(inHand4, "Card should be in player's hand if pair is not larger than the pile's pair.");

        assertFalse(inPile1, "Card should not be in pile if nothing was placed down.");
        assertFalse(inPile2, "Card should not be in pile if nothing was placed down.");
        assertFalse(inPile3, "Card should not be in pile if nothing was placed down.");
        assertFalse(inPile4, "Card should not be in pile if nothing was placed down.");

        assertEquals(expectedHandSize, actualHandSize, "Player's card count should decrease by 4 if both pairs are placed in pile.");
        assertEquals(expectedPile1Size, actualPile1Size, "Pile's card count should increase by 2 if pair from player's hand is placed in pile.");
        assertEquals(expectedPile2Size, actualPile2Size, "Pile's card count should increase by 2 if pair from player's hand is placed in pile.");

    }

    @Test
    public void playStraight_should_removeCards_fromPlayerHand_toPutIntoPile()
    {
        // arrange
        int expectedHandSize = 0;
        int expectedPile1Size = 8;
        int expectedPile2Size = 6;

        player.dealTo(card1);
        player.dealTo(card13);
        player.dealTo(card15);

        player.dealTo(card2);
        player.dealTo(card3);
        player.dealTo(card4);
        player.dealTo(card5);

        // same value - player has stronger suit
        pile1.dealTo(card9);
        pile1.dealTo(card10);
        pile1.dealTo(card11);
        pile1.dealTo(card12);

        // different value - player has stronger value
        pile2.dealTo(card6);
        pile2.dealTo(card8);
        pile2.dealTo(card14);

        Card cardHand1 = player.getHand().findCard("heart", "8");
        Card cardHand2 = player.getHand().findCard("diamond", "9");
        Card cardHand3 = player.getHand().findCard("diamond", "10");
        Card cardHand4 = player.getHand().findCard("club", "J");

        Card cardHand5 = player.getHand().findCard("spade", "8");
        Card cardHand6 = player.getHand().findCard("heart", "10");
        Card cardHand7 = player.getHand().findCard("spade", "9");


        ArrayList<Card> straight1 = new ArrayList<>() {{
            add(cardHand1);
            add(cardHand2);
            add(cardHand3);
            add(cardHand4);
        }};

        ArrayList<Card> straight2 = new ArrayList<>() {{
            add(cardHand5);
            add(cardHand6);
            add(cardHand7);
        }};

        // act
        player.playStraight(pile1, straight1);
        player.playStraight(pile2, straight2);

        boolean inHand1 = player.getHand().findCard("heart", "8") != null;
        boolean inHand2 = player.getHand().findCard("diamond", "9") != null;
        boolean inHand3 = player.getHand().findCard("diamond", "10") != null;
        boolean inHand4 = player.getHand().findCard("club", "J") != null;

        boolean inHand5 = player.getHand().findCard("spade", "8") != null;
        boolean inHand6 = player.getHand().findCard("heart", "10") != null;
        boolean inHand7 = player.getHand().findCard("spade", "9") != null;

        boolean inPile1 = pile1.findCard("heart", "8") != null;
        boolean inPile2 = pile1.findCard("diamond", "9") != null;
        boolean inPile3 = pile1.findCard("diamond", "10") != null;
        boolean inPile4 = pile1.findCard("club", "J") != null;

        boolean inPile5 = pile2.findCard("spade", "8") != null;
        boolean inPile6 = pile2.findCard("heart", "10") != null;
        boolean inPile7 = pile2.findCard("spade", "9") != null;

        int actualHandSize = player.getHand().getCardCount();
        int actualPile1Size = pile1.getCardCount();
        int actualPile2Size = pile2.getCardCount();

        // assert
        assertFalse(inHand1, "Card should not be in player's hand after placed in pile as part of a straight.");
        assertFalse(inHand2, "Card should not be in player's hand after placed in pile as part of a straight.");
        assertFalse(inHand3, "Card should not be in player's hand after placed in pile as part of a straight.");
        assertFalse(inHand4, "Card should not be in player's hand after placed in pile as part of a straight.");
        assertFalse(inHand5, "Card should not be in player's hand after placed in pile as part of a straight.");
        assertFalse(inHand6, "Card should not be in player's hand after placed in pile as part of a straight.");
        assertFalse(inHand7, "Card should not be in player's hand after placed in pile as part of a straight.");

        assertTrue(inPile1, "Card should be in the pile after player played a straight.");
        assertTrue(inPile2, "Card should be in the pile after player played a straight.");
        assertTrue(inPile3, "Card should be in the pile after player played a straight.");
        assertTrue(inPile4, "Card should be in the pile after player played a straight.");
        assertTrue(inPile5, "Card should be in the pile after player played a straight.");
        assertTrue(inPile6, "Card should be in the pile after player played a straight.");
        assertTrue(inPile7, "Card should be in the pile after player played a straight.");

        assertEquals(expectedHandSize, actualHandSize, "Player's card count should decrease after playing a straight.");
        assertEquals(expectedPile1Size, actualPile1Size, "Pile's card count should increase after playing a straight.");
        assertEquals(expectedPile2Size, actualPile2Size, "Pile's card count should increase after playing a straight.");
    }

    @Test
    public void playStraight_shouldNot_removeCards_fromPlayerHand_IfPileStraightLarger()
    {
        // arrange
        int expectedHandSize = 7;
        int expectedPile1Size = 4;
        int expectedPile2Size = 3;

        player.dealTo(card6);
        player.dealTo(card8);
        player.dealTo(card14);

        player.dealTo(card9);
        player.dealTo(card10);
        player.dealTo(card11);
        player.dealTo(card12);

        // same value - player has weaker suit
        pile1.dealTo(card2);
        pile1.dealTo(card3);
        pile1.dealTo(card4);
        pile1.dealTo(card5);

        // different value - player has weaker value
        pile2.dealTo(card1);
        pile2.dealTo(card13);
        pile2.dealTo(card15);

        Card cardHand1 = player.getHand().findCard("diamond", "8");
        Card cardHand2 = player.getHand().findCard("heart", "9");
        Card cardHand3 = player.getHand().findCard("club", "10");
        Card cardHand4 = player.getHand().findCard("spade", "J");

        Card cardHand5 = player.getHand().findCard("diamond", "7");
        Card cardHand6 = player.getHand().findCard("club", "8");
        Card cardHand7 = player.getHand().findCard("club", "9");


        ArrayList<Card> straight1 = new ArrayList<>() {{
            add(cardHand1);
            add(cardHand2);
            add(cardHand3);
            add(cardHand4);
        }};

        ArrayList<Card> straight2 = new ArrayList<>() {{
            add(cardHand5);
            add(cardHand6);
            add(cardHand7);
        }};

        // act
        player.playStraight(pile1, straight1);
        player.playStraight(pile2, straight2);

        boolean inHand1 = player.getHand().findCard("diamond", "8") != null;
        boolean inHand2 = player.getHand().findCard("heart", "9") != null;
        boolean inHand3 = player.getHand().findCard("club", "10") != null;
        boolean inHand4 = player.getHand().findCard("spade", "J") != null;

        boolean inHand5 = player.getHand().findCard("diamond", "7") != null;
        boolean inHand6 = player.getHand().findCard("club", "8") != null;
        boolean inHand7 = player.getHand().findCard("club", "9") != null;

        boolean inPile1 = pile1.findCard("diamond", "8") != null;
        boolean inPile2 = pile1.findCard("heart", "9") != null;
        boolean inPile3 = pile1.findCard("club", "10") != null;
        boolean inPile4 = pile1.findCard("spade", "J") != null;

        boolean inPile5 = pile2.findCard("diamond", "7") != null;
        boolean inPile6 = pile2.findCard("club", "8") != null;
        boolean inPile7 = pile2.findCard("club", "9") != null;

        int actualHandSize = player.getHand().getCardCount();
        int actualPile1Size = pile1.getCardCount();
        int actualPile2Size = pile2.getCardCount();

        // assert
        assertTrue(inHand1, "Card should stay in player's hand if pile's straight is larger.");
        assertTrue(inHand2, "Card should stay in player's hand if pile's straight is larger.");
        assertTrue(inHand3, "Card should stay in player's hand if pile's straight is larger.");
        assertTrue(inHand4, "Card should stay in player's hand if pile's straight is larger.");
        assertTrue(inHand5, "Card should stay in player's hand if pile's straight is larger.");
        assertTrue(inHand6, "Card should stay in player's hand if pile's straight is larger.");
        assertTrue(inHand7, "Card should stay in player's hand if pile's straight is larger.");

        assertFalse(inPile1, "Card should not be in the pile if player's straight is smaller.");
        assertFalse(inPile2, "Card should not be in the pile if player's straight is smaller.");
        assertFalse(inPile3, "Card should not be in the pile if player's straight is smaller.");
        assertFalse(inPile4, "Card should not be in the pile if player's straight is smaller.");
        assertFalse(inPile5, "Card should not be in the pile if player's straight is smaller.");
        assertFalse(inPile6, "Card should not be in the pile if player's straight is smaller.");
        assertFalse(inPile7, "Card should not be in the pile if player's straight is smaller.");

        assertEquals(expectedHandSize, actualHandSize, "Player's card count should stay the same if pile's straight is larger.");
        assertEquals(expectedPile1Size, actualPile1Size, "Pile's card count should stay the same if player's straight is smaller.");
        assertEquals(expectedPile2Size, actualPile2Size, "Pile's card count should stay the same if player's straight is smaller.");
    }
}
