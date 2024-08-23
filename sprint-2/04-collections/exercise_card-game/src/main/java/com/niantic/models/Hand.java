package com.niantic.models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Hand
{
    private final ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards()
    {
        return sortHand(cards);
    }

    public int getCardCount()
    {
        return cards.size();
    }

    public void dealTo(Card card)
    {
        cards.add(card);
    }

    public void removeCard(Card card)
    {
        cards.remove(cards.indexOf(card));
    }

    public void placeInPile(Hand pile, Card card)
    {
        pile.dealTo(card);
        removeCard(card);
    }

    public static ArrayList<Card> sortHand(ArrayList<Card> cards)
    {
        var sorted = cards.stream()
                            .sorted((c1, c2) -> {{

                                // if the two cards have the same value, check suit order
                                if (c1.getValueOrder() == c2.getValueOrder())
                                {
                                    if (c1.getSuitOrder() < c2.getSuitOrder())
                                    {
                                        return -1;
                                    }

                                    else {
                                        return 1;
                                    }
                                }

                                // if the two cards have different values, check value order
                                if (c1.getValueOrder() < c2.getValueOrder())
                                {
                                    return -1;
                                }
                                else
                                {
                                    return 1;
                                }
                            }})

                            .collect(Collectors.toCollection(ArrayList::new));

        return sorted;
    }
}
