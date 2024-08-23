package com.niantic.models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private ArrayList<Card> cards;

    public Deck()
    {
        // build the deck of cards
        cards = new ArrayList<>();

        String[] suits = {"heart", "diamond", "club", "spade"};
        String[] cardValues = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for(String value : cardValues)
        {
            for(String suit : suits)
            {
                Card card = new Card(suit, value);
                cards.add(card);
            }
        }
    }

    public int getCardCount()
    {
        return cards.size();
    }

    public Card takeCard()
    {
        Card card = cards.removeFirst();
        return card;
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }
}
