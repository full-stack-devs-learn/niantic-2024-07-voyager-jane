package com.niantic.ui;

import com.niantic.models.Card;
import com.niantic.models.Hand;
import com.niantic.models.Player;

import java.util.ArrayList;

public class UserInterface
{
    public static void displayAllPlayersCards(ArrayList<Player> players)
    {
        System.out.println("All Players");
        System.out.println("-".repeat(30));
        for (Player player : players)
        {
            ArrayList<Card> hand = player.getHand().getCards();
            ArrayList<Card> sortedHand = Hand.sortHand(hand);

            System.out.println(player.getName() + ": ");

            for (Card card : sortedHand)
            {
                System.out.println("  " + card.getCardValue() + " " + card.getSuit());
            }
            System.out.println();
        }
    }

    public static void displayPlayerCards(Player player)
    {
        System.out.println("Hand of Player " + player.getName());
        System.out.println("-".repeat(10));
        var cards = Hand.sortHand(player.getHand().getCards());

        for (Card card : cards)
        {
            String color;

            if (card.getSuit().equals("heart")  || card.getSuit().equals("diamond"))
            {
                color = ColorCodes.RED;
            }
            else color = ColorCodes.BLUE;

            System.out.println("  " + card.getCardValue() + " " + color + card.getSuit() + ColorCodes.RESET);
        }
        System.out.println();
    }

    public static void displayPile(Hand pile)
    {
        System.out.println("Current Pile - Card on Top is the last item of list");
        System.out.println("-".repeat(10));
        var cards = pile.getCards();

        String color;

        for (Card card : cards)
        {
            if (card.getSuit().equals("heart")  || card.getSuit().equals("diamond"))
            {
                color = ColorCodes.RED;
            }
            else color = ColorCodes.BLUE;

            System.out.println("  " + card.getCardValue() + " " + color + card.getSuit() + ColorCodes.RESET);
        }
        System.out.println();
    }
}
