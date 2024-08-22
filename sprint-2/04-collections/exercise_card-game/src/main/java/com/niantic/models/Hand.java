package com.niantic.models;

import java.util.ArrayList;

public class Hand
{
    private final ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public int getCardCount()
    {
        return cards.size();
    }

    public void dealTo(Card card)
    {
        cards.add(card);
    }

    public ArrayList<Card> orderHandByValue()
    {
        ArrayList<Card> ordered = new ArrayList<>();

        ordered.add(cards.get(0));


        for (int i = 1; i < cards.size() - 1; i++)
        {
            Card current = cards.get(i);

            for (Card compare : ordered)
            {
                int lastItem = ordered.size() - 1;

                // if current > compare in new list & compare is the last item, add current to the end of the list
                if (current.getValueOrder() > compare.getValueOrder()
                        && ordered.get(lastItem).equals(compare))
                {
                    ordered.add(current);
                }

                // if current > compare, go next
                else if (current.getValueOrder() > compare.getValueOrder())
                {
                    continue;
                }

                // if current < compare, add current to current position in ordered
                else
                {
                    ordered.add(ordered.indexOf(compare), current);
                    break;
                }

            }
        }

        return ordered;
    }
}
