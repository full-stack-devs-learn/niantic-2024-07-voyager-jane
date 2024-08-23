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

    public boolean isPair(ArrayList<Card> pair)
    {
        Card card1 = pair.get(0);
        Card card2 = pair.get(1);

        if (card1.getCardValue().equals(card2.getCardValue()))
        {
            return true;
        }

        return false;
    }

    public boolean isStraight(ArrayList<Card> straight)
    {

        // Q? should i have made sorthand static?
        var sorted = Hand.sortHand(straight);

        for (int i = 1; i < sorted.size(); i++)
        {
            int previous = sorted.get(i - 1).getValueOrder();
            int current = sorted.get(i).getValueOrder();

            if (current != previous + 1)
            {
                return false;
            }
        }

        return true;
    }

    public boolean playSingle(Hand pile, Card card)
    {
        // Q? WHY IS IT ADDING NEW ELEMENTS TO START OF ARRAYLIST IN PILE?
        // A! its because i was sorting ALL hands including pile with getCards and i dont want that for the pile so i changed it to just return cards
        // another question in isStraight
        ArrayList<Card> pileCards = pile.getCards();
        int pileSize = pile.getCardCount();
        Card cardOnTopOfPile = pileCards.get(pileSize - 1);

        int cardHandValue = card.getValueOrder();
        int cardPileValue = cardOnTopOfPile.getValueOrder();
        int cardHandSuit = card.getSuitOrder();
        int cardPileSuit = cardOnTopOfPile.getSuitOrder();

        if (cardHandValue > cardPileValue
                || cardHandSuit > cardPileSuit)
        {
            hand.placeInPile(pile, card);
            return true;
        }

        else return false;
    }

    public boolean playPair(Hand pile, ArrayList<Card> cards)
    {
        boolean validMove = false;

        ArrayList<Card> pileCards = pile.getCards();
        int pileSize = pile.getCardCount();
        Card card1Pile = pileCards.get(pileSize - 1);
        Card card2Pile = pileCards.get(pileSize - 2);

        Card card1Hand = cards.get(0);
        Card card2Hand = cards.get(1);

        if (isPair(cards))
        {
            int handValue = card1Hand.getValueOrder();
            int pileValue = card1Pile.getValueOrder();

            int handMaxSuit = Math.max(card1Hand.getSuitOrder(), card2Hand.getSuitOrder());
            int pileMaxSuit = Math.max(card1Pile.getSuitOrder(), card2Pile.getSuitOrder());

            // for readability, im making this two separate if cases
            // if the player's pair is greater than the pile's pair
            if (handValue > pileValue)
            {
                cards.stream().forEach(card -> hand.placeInPile(pile, card));
                validMove = true;
            }

            // if the player's and pile's pair is the same, but the player has the better suit
            if (handValue == pileValue && handMaxSuit > pileMaxSuit)
            {
                cards.stream().forEach(card -> hand.placeInPile(pile, card));
                validMove = true;
            }

        }

        return validMove;
    }

    public boolean playStraight(Hand pile, ArrayList<Card> cards)
    {
        boolean validMove = false;

        ArrayList<Card> pileCards = pile.getCards();
        int pileSize = pileCards.size();

        var sorted = Hand.sortHand(cards);
        int straightSize = sorted.size();;

        if (isStraight(sorted))
        {
            Card firstCardHand = sorted.get(0);
            Card firstCardPile = pileCards.get(pileSize - straightSize);

            int handValue = firstCardHand.getValueOrder();
            int pileValue = firstCardPile.getValueOrder();

            int handSuit = firstCardHand.getSuitOrder();
            int pileSuit = firstCardPile.getSuitOrder();

            // if the player's first card is greater than the pile's first card
            if (handValue > pileValue)
            {
                cards.stream().forEach(card -> hand.placeInPile(pile, card));
                validMove = true;
            }

            // if the player's and pile's first card is the same, but player's first suit is greater
            if (handValue == pileValue && handSuit > pileSuit)
            {
                cards.stream().forEach(card -> hand.placeInPile(pile, card));
                validMove = true;
            }
        }

        return  validMove;
    }

}
