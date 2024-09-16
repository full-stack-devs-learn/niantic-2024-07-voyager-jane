package com.niantic.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Hand implements Cloneable
{
    private ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public int getPointValue()
    {
        // return sum of all card points
        int sum = cards.stream()
                        .map(card -> card.getPointValue())
                        .reduce(0, (temp, value) -> temp + value);

        return sum;
    }

    public void Sort()
    {
        // Todo: Exercise 2: implement this sort method
        cards = (ArrayList<Card>) cards.stream()
                .sorted(Card::compareTo)
                .collect(Collectors.toList());
    }

    public int getCardCount()
    {
        return cards.size();
    }


    public void dealTo(Card card)
    {
        cards.add(card);
        Sort();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<Card> cloneHand = new ArrayList<>();

        cards.forEach(card -> {
            try {
                Card clone = card.clone();
                cloneHand.add(clone);
            }
            catch (CloneNotSupportedException e)
            {
                throw new RuntimeException(e);
            }
        });

        return cloneHand;
    }
}
