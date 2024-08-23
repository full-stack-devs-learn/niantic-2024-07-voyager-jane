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
                System.out.println("  " + card.getSuit() + " " + card.getCardValue());
            }
            System.out.println();
        }
    }
}
