package io;

import domain.Card;
import domain.Player;
import messaging.CardListener;

import java.util.List;
import java.util.Scanner;

public class UserIO  {
    private Scanner scanner = new Scanner(System.in);

    public int setNumberOfPlayers(){
        int numberOfPlayers = 0;
        System.out.println("Please select between 2 and 5 players");
        numberOfPlayers = scanner.nextInt();
        scanner.nextLine();
        return numberOfPlayers;
    }

    public String nameAPlayer(List<Player> list){
        String playersName;
        System.out.println("Name this Player!");
        playersName = scanner.nextLine();
        return playersName;
    }

    public int playerGuess(Player player){
        int playersGuess = 0;
        System.out.println(player.getPlayerName() + " please make a guess between 0 and the total number of cards in this round! ");
        playersGuess = scanner.nextInt();
        return playersGuess;
    }

    public void showSelf(Card card) {
        String rank = card.findRank();
        System.out.print(rank + " of " + card.getSuit() + ": ");
    }

    public void printAllPlayers(List<Player> list){
        for (Player player:list){
            printPlayer(player);
        }
        System.out.println();
    }

    public void printAllPlayersWithPoints(List<Player> list){
        for (Player player:list){
            playerShowSelfWithPoints(player);
            System.out.println();
        }
        System.out.println();
    }

    public void printAllPlayersWithData(List<Player> list){
        for (Player player:list){
            playerShowSelfWithData(player);
            System.out.println();
        }
        System.out.println();
    }

    public void printPlayer(Player player){
            System.out.print(player.getPlayerName() +  ": ");
    }

    public void playerShowSelfWithData(Player player){
        System.out.print(player.getPlayerName() + " Points: " + player.getPoints() + " Guess: " + player.getGuess() + " Stick: " + player.getStick());
    }

    public void playerShowSelfWithPoints(Player player){
        System.out.print(player.getPlayerName() + " Points: " + player.getPoints());
    }


    public void printAllPlayersPoints(List<Player> list){
        for (Player player:list){
            printPoints(player);
        }
        System.out.println();
    }

    public void printPoints(Player player){
        System.out.print(player.getPoints() +  ": ");
    }

    public void printCards(List<Card> list){
        for (Card card:list){
            showSelf(card);
        }
        System.out.println();


    }
    public void testToSePlayedCards(List<Player> list){
        for (Player player:list){
            showSelf(player.getPlayedCard());
        }


    }

    public void printTrumfCard(Card card){
        System.out.println("Trumfcard! ");
        showSelf(card);
        System.out.println();
    }

    public void playerPlayCard(Player player){
        printPlayer(player);
        System.out.println("Play one of your Cards counting from the left! ");
        printCards(player.getHand());
        int numberOfTheCard = scanner.nextInt() -1;
        player.playCard(numberOfTheCard);
        showSelf(player.getPlayedCard());
        System.out.println();
    }

}

