package com.niantic.ui;

import com.niantic.models.Card;
import com.niantic.models.Hand;
import com.niantic.models.Player;

import java.awt.*;
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
                System.out.println("  " + displayCard(card));
            }
            System.out.println();
        }
    }

    public static void displayPlayerCards(Player player)
    {
        System.out.println("Hand of Player " + displayPlayer(player));
        System.out.println("-".repeat(10));
        var cards = Hand.sortHand(player.getHand().getCards());

        for (Card card : cards)
        {
            System.out.println("  " + displayCard(card));
        }
        System.out.println();
    }

    public static void displayPile(Hand pile)
    {
        System.out.println("Current Pile - Card on Top is the last item of list");
        System.out.println("-".repeat(10));
        var cards = pile.getCards();

        for (Card card : cards)
        {
            System.out.println("  " + displayCard(card));
        }
        System.out.println();
    }

    public static String displayCard(Card card)
    {
        String color;

        if (card.getSuit().equals("heart")  || card.getSuit().equals("diamond"))
        {
            color = ColorCodes.RED;
        }
        else color = ColorCodes.BLUE;

        return card.getCardValue() + " " + color + card.getSuit() + ColorCodes.RESET;
    }

    public static String displayPlayer(Player player)
    {
        if (player.getName().equalsIgnoreCase("one")) return ColorCodes.BOLD + ColorCodes.YELLOW + player.getName() + ColorCodes.RESET;
        else return ColorCodes.BOLD + ColorCodes.PURPLE + player.getName() + ColorCodes.RESET;
    }
}
