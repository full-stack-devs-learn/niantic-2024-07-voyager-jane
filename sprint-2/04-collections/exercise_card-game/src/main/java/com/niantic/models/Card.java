package com.niantic.models;

import java.util.HashMap;
import java.util.Map;

public class Card
{
    private String suit;
    private String cardValue;

    private static final Map<String, Integer> suitOrder = new HashMap<>() {{
        put("hearts", 4);
        put("diamonds", 3);
        put("clubs", 2);
        put("spades", 1);
    }};

    private static final Map<String, Integer> valueOrder = new HashMap<>() {{
        put("3", 1);
        put("4", 2);
        put("5", 3);
        put("6", 4);
        put("7", 5);
        put("8", 6);
        put("9", 7);
        put("10", 8);
        put("J", 9);
        put("Q", 10);
        put("K", 11);
        put("A", 12);
        put("2", 13);
    }};

    public Card(String suit, String faceValue)
    {
        this.suit = suit;
        this.cardValue = faceValue;
    }

    public String getSuit()
    {
        return suit;
    }

    public String getCardValue()
    {
        return cardValue;
    }

    public int getSuitOrder()
    {
        return suitOrder.get(suit);
    }

    public int getValueOrder()
    {
        return valueOrder.get(cardValue);
    }

}
