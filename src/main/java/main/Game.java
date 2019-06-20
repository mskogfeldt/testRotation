package main;

import dao.Deck;
import domain.Card;
import domain.Player;
import io.UserIO;
import messaging.CardProducer;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Integer> rounds = new ArrayList<>();
    private List<Player> orderOfPlay = new ArrayList<>();
    private List<Player> orderOfGuess = new ArrayList<>();
    private UserIO userIO;
    private Deck deckOfCards = new Deck();
    private Card trumfCard;
    private CardProducer cardProducer;

    public Game(UserIO userIO, CardProducer cardProducer) {
        this.userIO = userIO;
        this.cardProducer = cardProducer;
    }

    public List<Integer> getRounds() {
        return rounds;
    }

    public List<Player> getOrderOfPlay() {
        return orderOfPlay;
    }

    public List<Player> getOrderOfGuess() {
        return orderOfGuess;
    }

    public void allPlayersShowHand(){
        for (Player player:orderOfGuess){
            userIO.printPlayer(player);
            userIO.printCards(player.getHand());
        }

    }

    public void createPlayers( List<Player> list, int numberOfPlayers){
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = userIO.nameAPlayer(orderOfGuess);
            Player plYER = Player.builder().withPlayerName(name).build();
            list.add(plYER);
        }
    }

    public void createOrderofPlay(){
        orderOfPlay = orderOfGuess;
    }

    public List<Player> getList(List<Player> list) {
        return list;
    }


    void dealCardToAllPlayers(){
        int numberOfCards = rounds.remove(0);
        while (numberOfCards > 0) {
            allPlayersGetACard();
            numberOfCards--;
        }
    }

    void allPlayersGetACard(){
        for (Player player:orderOfGuess){
            Card card = deckOfCards.dealACard();
            player.addCardToHand(card);
            cardProducer.create(card);
        }

    }


    public void rotatePlayers( List<Player> list) {
        List<Player> tempList = new ArrayList<>();;
        tempList.add(list.remove(0));
        list.add(tempList.remove(0));
    }


    public void rotateOrderOfPlay(Player player){
       while (orderOfPlay.get(0) != player){
           rotatePlayers(orderOfPlay);
       }

    }

    public void createGameRounds() {
        createfirstHalf();
        createSecondHalf();
    }

    public void createfirstHalf(){
        for (int i = 10; i > 0; i--) {
            rounds.add(i);
        }
    }

    public void createSecondHalf(){
        for (int i = 1; i < 11; i++) {
            rounds.add(i);
        }
    }

    public void allPlayersGuess(){
        for (Player player:orderOfGuess){
            player.setGuess(userIO.playerGuess(player));
        }
    }

    public void allPlayersStickRememberToRemove(){
        for (Player player:orderOfGuess){
            player.setStick(userIO.playerStick(player));
        }
    }

    public void reciveTrumfCard(){
        this.trumfCard = deckOfCards.dealACard();
    }

    public void checkIfPLayersGetsPoints(){
            for (Player player:orderOfGuess){
                if (player.getGuess() == player.getStick()){
                    givePlayerPoints(player);
                }
            }

    }

    public void givePlayerPoints(Player player){
        int tempPoints = player.getPoints();
        if (player.getStick() == 10) tempPoints += 110;
        else tempPoints += 10 + player.getStick();
        player.setPoints(tempPoints);
    }

    public void allPlayersGetSingelDidgitRankpoints(){
        for (Player player:orderOfGuess){
            returnSingelDidgitRankingPointsForOrderOfPlay(player);
        }
    }


    public void returnSingelDidgitRankingPointsForOrderOfPlay(Player player){
        int rankDependingOnPlaceInDealing = player.getGuess() * 10;
        for (int i=0; i<orderOfGuess.size(); i++) {
            if (orderOfGuess.get(i) == player) rankDependingOnPlaceInDealing += (6-i);
        }
        player.setRankingPointForPlayingOrder(rankDependingOnPlaceInDealing);
    }

    public void findTheHighestRankkingPlayer(){
        Player highestRankingPlayer = orderOfGuess.get(0);
        for (int i=0; i<orderOfGuess.size(); i++) {
            if (orderOfGuess.get(i).getRankingPointForPlayingOrder() > highestRankingPlayer.getRankingPointForPlayingOrder()){
                highestRankingPlayer = orderOfGuess.get(i);
            }
        }
        rotateOrderOfPlay(highestRankingPlayer);
    }

    public void startANewStick() {

    }


    public void startARound(){
        dealCardToAllPlayers();
        allPlayersShowHand();
        //userIO.printAllPlayers(getOrderOfGuess());
        userIO.printAllPlayers(getOrderOfPlay());
        allPlayersGuess();
        allPlayersGetSingelDidgitRankpoints();
        findTheHighestRankkingPlayer();
        userIO.printAllPlayers(getOrderOfPlay());

    }

    public void startAGame(){
        createGameRounds();
        int numberOfP = userIO.setNumberOfPlayers();
        createPlayers(getOrderOfGuess(), numberOfP);
        createOrderofPlay();
        /*
        userIO.printAllPlayers(game.getOrderOfGuess());

        userIO.printAllPlayers(game.getOrderOfPlay());

        game.rotatePlayers(game.getOrderOfGuess());

        userIO.printAllPlayers(game.getOrderOfGuess());

        game.dealCardToAllPlayers();
        game.allPlayersShowHand();


        game.allPlayersGuess();

        game.AllPlayersGetSingelDidgitRankpoints();
        game.findTheHighestRankkingPlayer();

        userIO.printAllPlayers(game.getOrderOfPlay());
        game.allPlayersStickRememberToRemove();
        game.checkIfPLayersGetsPoints();

        userIO.printAllPlayersPoints(game.getOrderOfGuess());

        game.allPlayersGuess();

        game.AllPlayersGetSingelDidgitRankpoints();
        game.findTheHighestRankkingPlayer();

        userIO.printAllPlayers(game.getOrderOfPlay());
        game.allPlayersStickRememberToRemove();
        game.checkIfPLayersGetsPoints();
        userIO.printAllPlayersPoints(game.getOrderOfGuess());

         */


    }


}

