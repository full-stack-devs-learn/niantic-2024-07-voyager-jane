package com.niantic.application;

import com.niantic.models.Card;
import com.niantic.models.Deck;
import com.niantic.models.Hand;
import com.niantic.models.Player;
import com.niantic.ui.ColorCodes;
import com.niantic.ui.UserInterface;

import java.util.*;

import static com.niantic.ui.UserInterface.displayCard;
import static com.niantic.ui.UserInterface.displayPlayer;

public class CardGameApplication
{
    Deck deck = new Deck();
    ArrayList<Player> players = new ArrayList<>();
    Hand pile = new Hand();

    private Player startPlayer = null;
    private Player currentPlayer;
    private Player winner = null;

    private boolean firstTurn = true;
    private boolean firstAction = true;
    private String action;

    Scanner input = new Scanner(System.in);
    
    public void run()
    {
        startGame();
        while (winner == null) playRound();
        System.out.println("The winner is " + displayPlayer(winner));
    }

    // <editor-fold desc="Start Game">
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
        System.out.println(ColorCodes.YELLOW + "   WELCOME TO Tiến lên also known as 13!" + ColorCodes.RESET);
        System.out.println();
        System.out.println("*".repeat(45));
        System.out.println();
        System.out.println("This game can be played up to 4 players. The first player to place all their cards into the pile wins!");
        System.out.println();

        System.out.println("Press ENTER to Start");
        input.nextLine();
        System.out.println(ColorCodes.GREEN + "Let's Start!" + ColorCodes.RESET);
        System.out.println();
        System.out.println("The player with the lowest card value is " + displayPlayer(startPlayer) + "! They will start the first turn.");
        System.out.println();
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
    }

    // </editor-fold>

    private List<Player> createQueue()
    {
        // Creating Order of Players' turn based on starting player position ("clockwise" or consecutive then wrap)
        int startIndex = players.indexOf(startPlayer);
        var first = players.subList(startIndex, players.size());
        if (startIndex != 0 && first.size() < players.size())
        {
            var second = players.subList(0, startIndex);
            first.addAll(second);
        }
        return first;
    }

    private void playRound()
    {
        // Placing players in order into queue
        Queue<Player> playerQueue = new LinkedList<>(createQueue());

        playerQueue.stream().forEach(player -> System.out.println(player.getName()));
        System.out.println("NEW Q");

        System.out.println("A new round has started! " + displayPlayer(startPlayer) + " will begin this round.");
        System.out.println();

        while (!playerQueue.isEmpty())
        {

            currentPlayer = playerQueue.poll();

            System.out.println("It's " + displayPlayer(currentPlayer) + "'s turn now!");
            System.out.println();
            System.out.println("Press ENTER to continue");
            input.nextLine();

            UserInterface.displayPlayerCards(currentPlayer);

            offerPileView();
            System.out.println("Press ENTER to continue");
            input.nextLine();

            // if this is the start of a new round, current player chooses the round's action
            if (startPlayer.equals(currentPlayer) && firstAction)
            {
                chooseAction();
                playerQueue.offer(currentPlayer);
            }

            else
            {
                System.out.println("Would you like to skip your turn? If you skip now, you can't place cards in the pile until the next round starts. (y/n)");
                String skip = input.nextLine().strip().toLowerCase();
                System.out.println();

                // skip current player's turn and don't put them back into queue since they forfeited the rest of their turns for this round
                if (skip.equalsIgnoreCase("y"))
                {
                    // if there is only one other person in queue, end round and set them as the next round's starting player
                    if (playerQueue.size() == 1)
                    {
                        startPlayer = playerQueue.poll();
                        firstAction = true;
                        action = null;

                        System.out.println("The round has ended with only Player " + displayPlayer(startPlayer) + " left who hasn't skipped this round. They will start the next round");
                        break;
                    }
                    else continue;

                }

                else
                {
                    playerQueue.offer(currentPlayer);

                    // if there are more players in the queue, play the correct action
                    if (action.equals("single")) singleRound();
                    if (action.equals("pair")) pairRound();
                    if (action.equals("straight")) straightRound();

                    // after playing action, if the current player's hand is empty, they are the winner and end the game
                    if (currentPlayer.getHand().getCardCount() == 0)
                    {
                        winner = currentPlayer;
                        break;
                    }
                }
            }
        }
    }

    private void chooseAction()
    {
        boolean invalidOption = true;

        while (invalidOption)
        {
            System.out.println("Please enter the number of the action you would like to do.");
            System.out.println("-".repeat(10));
            System.out.println("1) Single");
            System.out.println("2) Pair");
            System.out.println("3) Straight");
            System.out.println();

            System.out.print("Selection: ");
            var option = Integer.parseInt(input.nextLine().strip());
            System.out.println();

            switch (option)
            {
                case 1:
                    action = "single";
                    singleRound();
                    invalidOption = false;
                    break;
                case 2:
                    action = "pair";
                    pairRound();
                    invalidOption = false;
                    break;
                case 3:
                    action = "straight";
                    straightRound();
                    invalidOption = false;
                    break;
                default:
                    System.out.println("Invalid Option. Please enter the options given.");
                    break;
            }
        }
    }

    private void singleRound()
    {
        Card validCard = null;
        boolean validTurn = false;

        if (firstTurn)
        {
            firstTurn = false;
            System.out.println("Staring player of the game's first turn must play their lowest card in their action!");

            validCard = Hand.sortHand(currentPlayer.getHand().getCards()).get(0);

            validTurn = currentPlayer.playSingle(pile, validCard, true);

            if (validTurn) System.out.println("Card " + displayCard(validCard) + " successfully played.");
            else System.out.println("There was an error in placing the card.");

            System.out.println();
        }

        else
        {
            do {
                System.out.println("What card would you like to place in the pile?");
                System.out.println("Choose Face or Number: ");
                String cardValue = input.nextLine().strip().toUpperCase();
                System.out.println("Choose Suit: ");
                String suit = input.nextLine().strip().toLowerCase();
                System.out.println();

                validCard = currentPlayer.getHand().findCard(suit, cardValue);

                if (validCard == null) System.out.println("The card you chose doesn't exist in your hand. Please choose another card.");
                else
                {
                    // if this is first action of the round it should always be played no matter the pile.
                    // if not the first action, then compare to pile
                    validTurn = currentPlayer.playSingle(pile, validCard, firstAction);

                    if (!validTurn) System.out.println("The card you chose does not have a larger value than the pile's top card. Please choose another card.");
                }
            }
            while (!validTurn);

            System.out.println("Card " + displayCard(validCard) + " successfully played.");
            System.out.println();
        }

        // set firstAction to be false so players arent allowed to just put anything into the pile
        firstAction = false;
        System.out.println("Press ENTER to continue");
        input.nextLine();
    }

    private void pairRound()
    {
        System.out.println("Pair!");
    }
    private void straightRound()
    {
        System.out.println("Straight!");
    }

    // <editor-fold desc="Helper Functions">
    private void offerPileView()
    {
        System.out.println("Would you like to see the current pile (y/n): ");
        String seePile = input.nextLine().strip().toLowerCase();
        System.out.println();
        if (seePile.equals("y"))
        {
            if (pile.getCards().isEmpty())
            {
                System.out.println("Nothing has been placed in the pile yet.");
                System.out.println();
            }
            else UserInterface.displayPile(pile);

        }
    }
    // </editor-fold>

}

