package com.niantic.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest
{
    @Test
    public void newDeck_should_contain52Cards_with13CardsEachSuit()
    {
        // arrange
        Deck deck = new Deck();

        int expectedCardsTotal = 52;
        int expectedSuitCards = 13;

        // act
        int actualCardsTotal = deck.getCardCount();
        int actualHeartsTotal = 0;
        int actualDiamondsTotal = 0;
        int actualClubsTotal= 0;
        int actualSpadesTotal = 0;

        int count = 0;
        while (count < 52)
        {
            Card current = deck.takeCard();

            switch (current.getSuit())
            {
                case "heart":
                    actualHeartsTotal++;
                    break;
                case "diamond":
                    actualDiamondsTotal++;
                    break;
                case "club":
                    actualClubsTotal++;
                    break;
                case "spade":
                    actualSpadesTotal++;
                    break;
                default:
                    break;
            }

            count++;
        }

        // assert
        assertEquals(expectedCardsTotal, actualCardsTotal, "Deck should have 52 cards.");
        assertEquals(expectedSuitCards, actualHeartsTotal, "Hearts suite should have 13 cards.");
        assertEquals(expectedSuitCards, actualDiamondsTotal, "Diamonds suite should have 13 cards.");
        assertEquals(expectedSuitCards, actualClubsTotal, "Clubs suite should have 13 cards.");
        assertEquals(expectedSuitCards, actualSpadesTotal, "Spades suite should have 13 cards.");

    }
}
