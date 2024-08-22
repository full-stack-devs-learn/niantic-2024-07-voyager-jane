package com.niantic.models;

import java.util.ArrayList;

public class Player
{
    private String name;
    private Hand hand;

    public Player(String name)
    {
        this.name = name;
        hand = new Hand();
    }

    public String getName()
    {
        return name;
    }

    public void dealTo(Card card)
    {
        hand.dealTo(card);
    }

    public Hand getHand()
    {
        return hand;
    }

    public void playSingle(Hand pile, Card card)
    {
        hand.placeInPile(pile, card);
    }

    public void playPairOrStraight(Hand pile, ArrayList<Card> cards)
    {
        cards.stream().forEach(card -> hand.placeInPile(pile, card));
    }

}
