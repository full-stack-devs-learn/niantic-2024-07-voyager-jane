package com.niantic.application;

import com.niantic.models.Card;
import com.niantic.models.Deck;
import com.niantic.models.Hand;
import com.niantic.models.Player;
import com.niantic.ui.ColorCodes;
import com.niantic.ui.UserInterface;

import java.util.*;

import static com.niantic.ui.UserInterface.*;

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

    private Card validCard1 = null;
    private Card validCard2 = null;

    Scanner input = new Scanner(System.in);
    
    public void run()
    {
        startGame();

        while (winner == null) playRound();

        System.out.println();
        System.out.println(ColorCodes.GREEN + "*".repeat(30) + ColorCodes.RESET);
        System.out.println();
        System.out.println("The Winner is " + displayPlayer(winner));
        System.out.println();
        System.out.println(ColorCodes.GREEN + "*".repeat(30) + ColorCodes.RESET);
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

        for (int i = 0; i < 6; i++)
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
        var first = new ArrayList<>(players.subList(startIndex, players.size()));
        if (startIndex != 0)
        {
            var second = new ArrayList<>(players.subList(0, startIndex));
            first.addAll(second);
        }
        return first;
    }



    private void playRound()
    {
        // Placing players in order into queue
        Queue<Player> playerQueue = new LinkedList<>(createQueue());

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
                // after playing action, if the current player's hand is empty, they are the winner and end the game
                if (currentPlayer.getHand().getCardCount() == 0)
                {
                    winner = currentPlayer;
                    break;
                }
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
                    System.out.println(displayPlayer(currentPlayer) + " has decided to skip the rest of their turns for this round.");
                    System.out.println();
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
                    boolean canContinue = false;

                    // if there are more players in the queue, play the correct action
                    if (action.equals("single")) canContinue = singleRound();
                    if (action.equals("pair")) canContinue = pairRound();

                    if (!canContinue)
                    {
                        if (playerQueue.size() == 1)
                        {
                            startPlayer = playerQueue.poll();
                            firstAction = true;
                            action = null;

                            System.out.println("The round has ended with only Player " + displayPlayer(startPlayer) + " left who hasn't skipped this round. They will start the next round");
                            break;
                        }
                    }

                    playerQueue.offer(currentPlayer);

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
                default:
                    System.out.println("Invalid Option. Please enter the options given.");
                    break;
            }
        }
    }



    private boolean singleRound()
    {
        Card validCard = null;
        boolean validTurn = false;
        boolean continueRound;

        int handSize = currentPlayer.getHand().getCardCount();
        Card lastHandCard = Hand.sortHand(currentPlayer.getHand().getCards()).get(handSize - 1);
        int pileSize = pile.getCardCount();
        Card topOfPile = null;

        if (pile.getCardCount() != 0) topOfPile = pile.getCards().get(pileSize - 1);

        if (firstTurn)
        {
            firstTurn = false;
            System.out.println("Staring player of the game's first turn must play their lowest card in their action!");

            validCard = Hand.sortHand(currentPlayer.getHand().getCards()).get(0);

            validTurn = currentPlayer.playSingle(pile, validCard, true);

            if (validTurn) System.out.println("Card " + displayCard(validCard) + " successfully played.");

            continueRound = true;

            System.out.println();
        }

        // run code if this is the firstAction of the round OR the largest value card in hand can beat the pile
        else if (firstAction ||
                (lastHandCard.getValueOrder() > topOfPile.getValueOrder()
                        || (lastHandCard.getValueOrder() == topOfPile.getValueOrder()
                        && lastHandCard.getSuitOrder() > topOfPile.getSuitOrder())))
        {
            do {
                System.out.println("What card would you like to place in the pile?");
                System.out.println("Choose Face or Number: ");
                String cardValue = input.nextLine().strip().toUpperCase();
                System.out.println("Choose Suit: ");
                String suit = input.nextLine().strip().toLowerCase();
                System.out.println();

                validCard = currentPlayer.getHand().findCard(suit, cardValue);

                if (validCard == null)
                {
                    System.out.println("The card you chose doesn't exist in your hand. Please choose another card.");
                    System.out.println();
                }
                else
                {
                    // if this is first action of the round it should always be played no matter the pile.
                    // if not the first action, then compare to pile
                    validTurn = currentPlayer.playSingle(pile, validCard, firstAction);

                    if (!validTurn)
                    {
                        System.out.println("The card you chose does not have a larger value than the pile's top card. Please choose another card.");
                        System.out.println();
                    }
                }
            }
            while (!validTurn);

            System.out.println("Card " + displayCard(validCard) + " successfully played.");
            System.out.println();

            continueRound = true;
        }

        else
        {
            // if none of the cards in the hand, can beat the pile's card, they cannot play for the rest of the round.
            System.out.println("Your largest value single card in your hand cannot beat the pile's card. You are unable to play the rest of this round.");
            System.out.println();

            continueRound = false;
        }

        // set firstAction to be false so players arent allowed to just put anything into the pile
        firstAction = false;
        System.out.println("Press ENTER to continue");
        input.nextLine();

        return continueRound;
    }




    private boolean pairRound()
    {
        ArrayList<Card> cards = currentPlayer.getHand().getCards();

        // HOW DO I EVEN PULL THIS OFF FOR FIRST TURN DO I NEED TO CHECK IF THERES A PAIR FIRST
        if (firstTurn)
        {
            firstTurn = false;
            firstAction = false;
            System.out.println("Staring player of the game's first turn must play their lowest card in their action!");
            // PUT MORE HERE
        }

        // if player only has 1 card in hand, they can't play a pair. if first action of the round, choose a different option.
        if (firstAction && currentPlayer.getHand().getCardCount() < 2)
        {
            System.out.println("You don't have enough cards to play a pair. Please choose a different option.");
            System.out.println();
            chooseAction();
            return false;
        }

        // if a player only has 1 card in hand and this is not firstAction of the round, they cannot play for the rest of the round.
        if (!firstAction && currentPlayer.getHand().getCardCount() < 2)
        {
            System.out.println("You don't have enough cards to play a pair. You are unable to play the rest of this round.");
            return false;
        }

        // FIRST ACTION VERSION OF BELOW BUT FOR IF THERE IS A PAIR

        // if a player's last 2 cards do not match, they cannot play for the rest of the round
        if (currentPlayer.getHand().getCardCount() == 2 && !currentPlayer.isPair(cards))
        {
            System.out.println("The last 2 cards in your hand do not match and cannot be played as a pair. You are unable to play the rest of this round.");
            return false;
        }

        // check to make sure player has at least one pair in their hand that can beat pile
        // NEED TO MAKE CONDITION IF PILE IS EMPTY
        boolean beatPile = false;
        int pileSize = pile.getCardCount();
        int pileSuit1 = pile.getCards().get(pileSize - 1).getSuitOrder();
        int pileSuit2 = pile.getCards().get(pileSize - 2).getSuitOrder();
        int pileValue = pile.getCards().get(pileSize - 1).getValueOrder();

        for (Card card : cards)
        {
            int cardValue = card.getValueOrder();
            int cardSuit = card.getSuitOrder();

            for (Card compare : cards)
            {
                int compareValue = compare.getValueOrder();
                int compareSuit = compare.getSuitOrder();

                if (cardValue == compareValue &&
                        (cardValue > pileValue ||
                                (cardValue == pileValue && cardSuit > pileSuit1) ||
                                (cardValue == pileValue && cardSuit > pileSuit2) ||
                                (cardValue == pileValue && compareSuit > pileSuit1) ||
                                (cardValue == pileValue && compareSuit > pileSuit2)))
                {
                    beatPile = true;
                    break;
                }
            }
        }

        // NEED FIRST ACTION OPTION

        // if there is a pair present that can beat pile pair
        if (beatPile)
        {
            boolean validTurn = false;

            while (!validTurn)
            {
                while (validCard1 == null)
                {
                    System.out.println("What is the first card you are choosing for the pair?");
                    System.out.println("Choose Face or Number: ");
                    String cardValue1 = input.nextLine().strip().toUpperCase();
                    System.out.println("Choose Suit: ");
                    String suit1 = input.nextLine().strip().toLowerCase();
                    System.out.println();

                    validCard1 = currentPlayer.getHand().findCard(suit1, cardValue1);

                    if (validCard1 == null) {
                        System.out.println("The card you chose doesn't exist in your hand. Please choose another card.");
                        System.out.println();
                    }
                }

                while (validCard2 == null)
                {
                    System.out.println("What is the second card you are choosing for the pair?");
                    System.out.println("Choose Face or Number: ");
                    String cardValue1 = input.nextLine().strip().toUpperCase();
                    System.out.println("Choose Suit: ");
                    String suit1 = input.nextLine().strip().toLowerCase();
                    System.out.println();

                    validCard1 = currentPlayer.getHand().findCard(suit1, cardValue1);

                    if (validCard1 == null)
                    {
                        System.out.println("The card you chose doesn't exist in your hand. Please choose another card.");
                        System.out.println();
                    }
                }

                ArrayList<Card> compare = new ArrayList<>() {{
                    add(validCard1);
                    add(validCard2);
                }};

                if (currentPlayer.isPair(compare))
                {
                    validTurn = currentPlayer.playPair(pile, compare, firstAction);

                    if (validTurn)
                    {
                        System.out.println("Pair " + displayCard(validCard1) + " and " + displayCard(validCard2) + " successfully played.");
                        System.out.println();
                        return true;
                    }

                    if (!validTurn)
                    {
                        System.out.println("The pair you chose does not have a larger value than the pile's pair.");
                        System.out.println();
                    }
                }
                else
                {
                    System.out.println("The two cards you chose are not a pair.");
                    System.out.println();
                }

                System.out.println("Would you like to see your hand again? (y/n)");
                String seeHand = input.nextLine();
                if (seeHand.equalsIgnoreCase("y")) displayPlayerCards(currentPlayer);
                System.out.println();

                System.out.println("Would you like to see the pair in the pile you need to beat? (y/n)");
                String seePilePair = input.nextLine();
                if (seePilePair.equalsIgnoreCase("y")) seePilePair();
                System.out.println();

                System.out.println("Would you like to skip your turn? If you skip now, you can't place cards in the pile until the next round starts. (y/n)");
                String skip = input.nextLine();
                if (skip.equalsIgnoreCase("y")) return false;

            }

        }

        else
        {
            System.out.println("There are no pairs present in your hand that can beat the pile pair. You are unable to play the rest of this round.");
            return false;
        }

        return false;
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

    private void seePilePair()
    {
        int pileSize = pile.getCardCount();
        Card card1 = pile.getCards().get(pileSize - 1);
        Card card2 = pile.getCards().get(pileSize - 2);

        System.out.println("Pair Pile to Beat");
        System.out.println("-".repeat(10));
        displayCard(card1);
        displayCard(card2);
    }
    // </editor-fold>

}

