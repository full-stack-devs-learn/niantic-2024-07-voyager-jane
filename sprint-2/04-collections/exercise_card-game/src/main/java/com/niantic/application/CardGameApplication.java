package com.niantic.application;

import com.niantic.models.Card;
import com.niantic.models.Deck;
import com.niantic.models.Hand;
import com.niantic.models.Player;
import com.niantic.ui.UserInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CardGameApplication
{
    Deck deck = new Deck();
    ArrayList<Player> players = new ArrayList<>();

    private Player startPlayer = null;
    Scanner input = new Scanner(System.in);
    
    public void run()
    {
        startGame();
//        UserInterface.displayAllPlayersCards(players);
        playTurn();
    }

    private void startGame()
    {
        addPlayers();
        dealCards();

        for (Player player : players)
        {
            if (startPlayer == null)
            {
                startPlayer = player;
                continue;
            }

            var sortCurrent = Hand.sortHand(player.getHand().getCards());
            var sortTemp = Hand.sortHand(startPlayer.getHand().getCards());

            int currentSuit = sortCurrent.get(0).getSuitOrder();
            int currentValue = sortCurrent.get(0).getValueOrder();

            int tempSuit = sortTemp.get(0).getSuitOrder();
            int tempValue = sortTemp.get(0).getValueOrder();

            if (currentValue < tempValue || (currentValue == tempValue && currentSuit < tempSuit))
            {
                startPlayer = player;
            }

        }
        System.out.println();
        System.out.println("*".repeat(45));
        System.out.println();
        System.out.println("   WELCOME TO Tiến lên also known as 13!");
        System.out.println();
        System.out.println("*".repeat(45));
        System.out.println();
        System.out.println("This game can be played up to 4 players. The first player to place all their cards into the pile wins!");
        System.out.println("Let's Start!");
        System.out.println();
        System.out.println("The player with the lowest card value is " + startPlayer.getName() + "! They will start the first turn.");
    }

    private void dealCards()
    {
        deck.shuffle();

        for (int i = 0; i < 13; i++)
        {
            for(Player player : players)
            {
                Card card = deck.takeCard();
                player.dealTo(card);
            }
        }
    }

    private void addPlayers()
    {
        players.add(new Player("One"));
        players.add(new Player("Two"));
        players.add(new Player("Three"));
    }

    private void playTurn()
    {
        // Creating Order of Players' turn based on starting player position ("clockwise" or consecutive then wrap)
        int startIndex = players.indexOf(startPlayer);

        var first = players.subList(startIndex, players.size());
        var second = players.subList(0, startIndex);

        first.addAll(second);

        // Placing players in order into queue
        Queue<Player> playerQueue = new LinkedList<>(first);

        while (!playerQueue.isEmpty())
        {
            Player player = playerQueue.poll();
        }
    }
}
