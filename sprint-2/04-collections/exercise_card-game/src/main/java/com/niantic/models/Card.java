package com.niantic.models;

import java.util.HashMap;
import java.util.Map;

public class Card
{
    private String suit;
    private String cardValue;

    private static final Map<String, Integer> suitOrder = new HashMap<>() {{
        put("heart", 4);
        put("diamond", 3);
        put("club", 2);
        put("spade", 1);
    }};

    private static final Map<String, Integer> valueOrder = new HashMap<>() {{
        put("3", 3);
        put("4", 4);
        put("5", 5);
        put("6", 6);
        put("7", 7);
        put("8", 8);
        put("9", 9);
        put("10", 10);
        put("J", 11);
        put("Q", 12);
        put("K", 13);
        put("A", 14);
        put("2", 15);
    }};

    public Card(String suit, String cardValue)
    {
        this.suit = suit;
        this.cardValue = cardValue;
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
