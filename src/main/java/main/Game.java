package main;

import dao.Deck;
import domain.Card;
import domain.Player;
import io.UserIO;
import messaging.CardConsumer;
import messaging.CardListener;
import messaging.CardProducer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

public class Game implements CardListener {
    private List<Integer> rounds = new ArrayList<>();
    private List<Player> orderOfPlay = new ArrayList<>();
    private List<Player> orderOfGuess = new ArrayList<>();
    private UserIO userIO;
    private Deck deckOfCards = new Deck();
    private Card trumfCard;
    private CardProducer cardProducer;
    private CardConsumer cardConsumer;
    private boolean trumfGame = true;

    public Game(UserIO userIO, BlockingDeque<Card> queue, boolean trumfGame) {
        this.trumfGame = trumfGame;
        this.userIO = userIO;
        this.cardProducer = new CardProducer(queue);
        this.cardConsumer = new CardConsumer(queue, this);

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

    public void allPlayersShowHand() {
        for (Player player : orderOfGuess) {
            userIO.printPlayer(player);
            userIO.printCards(player.getHand());
        }

    }

    public void createPlayers(List<Player> list, int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = userIO.nameAPlayer(orderOfGuess);
            Player plYER = Player.builder().withPlayerName(name).build();
            list.add(plYER);
        }
    }

    public void createOrderofPlay() {
        orderOfPlay = orderOfGuess;
    }

    public List<Player> getList(List<Player> list) {
        return list;
    }


    void dealCardToAllPlayers() {
        int numberOfCards = rounds.remove(0);
        while (numberOfCards > 0) {
            allPlayersGetACard();
            numberOfCards--;
        }
    }

    void allPlayersGetACard() {
        for (Player player : orderOfGuess) {
            Card card = deckOfCards.dealACard();
            //player.addCardToHand(card);
            cardProducer.produce(card);
            cardConsumer.consumeCard(player);
        }

    }

    @Override
    public void onCard(Card card, Player player) {
        player.addCardToHand(card);
    }


    public void rotatePlayers(List<Player> list) {
        List<Player> tempList = new ArrayList<>();
        tempList.add(list.remove(0));
        list.add(tempList.remove(0));
    }

    public void winnerGetsStick(){
        Player winnerOfLastStick = checkWhoWinsStick();
        int numberOfStick = winnerOfLastStick.getStick();
        winnerOfLastStick.setStick(numberOfStick+1);
    }

    void allPlayersClearStick() {
        for (Player player : orderOfGuess) {
            player.setStick(0);
        }

    }


    public void rotateOrderOfPlayInAStick() {
        Player winnerOfLastStick = checkWhoWinsStick();
        rotateOrderOfPlay(winnerOfLastStick);
    }

    public void rotateOrderOfPlay(Player player) {
        while (orderOfPlay.get(0) != player) {
            rotatePlayers(orderOfPlay);
        }

    }

    public void createGameRounds() {
        createfirstHalf();
        createSecondHalf();
    }

    public void createfirstHalf() {
        for (int i = 10; i > 0; i--) {
            rounds.add(i);
        }
    }

    public void createSecondHalf() {
        for (int i = 1; i < 11; i++) {
            rounds.add(i);
        }
    }

    public void allPlayersGuess() {
        for (Player player : orderOfGuess) {
            player.setGuess(userIO.playerGuess(player));

        }
    }

    public void allPlayersPlayaCard() {
        for (Player player : orderOfGuess) {
            userIO.playerPlayCard(player);
        }
    }

    public void setTheValueOfforAllPlayers() {
        for (Player player : orderOfPlay) {
            setTheValueOfPlayedCard(player.getPlayedCard());
        }
    }


    public void setTheValueOfPlayedCard(Card card) {
        if (card.getSuit().equals(orderOfPlay.get(0).getPlayedCard().getSuit())) {
        }

        if (card.getSuit().equals(trumfCard.getSuit())) {
            card.setValue(card.getValue() + 13);
        }

        if (card.getSuit().equals(orderOfPlay.get(0).getPlayedCard().getSuit()) && card.getValue() == 14) {
            card.setValue(card.getValue() + 14);
        }

        if (!card.getSuit().equals(orderOfPlay.get(0).getPlayedCard().getSuit())) {
            if (!card.getSuit().equals(trumfCard.getSuit())) {
                card.setValue(0);
            }
        }


    }

    public Player checkWhoWinsStick() {
        int highestValue = 0;
        Player winner = null;
        for (Player player : orderOfPlay) {
            if (player.getPlayedCard().getValue() > highestValue) {
                winner = player;
                highestValue = player.getPlayedCard().getValue();
            }
        }
        System.out.println();
        System.out.println(winner.getPlayerName() + ": Wins the stick!");
        return winner;
    }


    public void reciveTrumfCard() {
        this.trumfCard = deckOfCards.dealACard();
    }

    public void checkIfPLayersGetsPoints() {
        for (Player player : orderOfGuess) {
            if (player.getGuess() == player.getStick()) {
                givePlayerPoints(player);
            }
        }

    }

    public void givePlayerPoints(Player player) {
        int tempPoints = player.getPoints();
        if (player.getStick() == 10) tempPoints += 110;
        else tempPoints += 10 + player.getStick();
        player.setPoints(tempPoints);
    }

    public void allPlayersGetSingelDidgitRankpoints() {
        for (Player player : orderOfGuess) {
            returnSingelDidgitRankingPointsForOrderOfPlay(player);
        }
    }


    public void returnSingelDidgitRankingPointsForOrderOfPlay(Player player) {
        int rankDependingOnPlaceInDealing = player.getGuess() * 10;
        for (int i = 0; i < orderOfGuess.size(); i++) {
            if (orderOfGuess.get(i) == player) rankDependingOnPlaceInDealing += (6 - i);
        }
        player.setRankingPointForPlayingOrder(rankDependingOnPlaceInDealing);
    }

    public void findTheHighestRankkingPlayer() {
        Player highestRankingPlayer = orderOfGuess.get(0);
        for (int i = 0; i < orderOfGuess.size(); i++) {
            if (orderOfGuess.get(i).getRankingPointForPlayingOrder() > highestRankingPlayer.getRankingPointForPlayingOrder()) {
                highestRankingPlayer = orderOfGuess.get(i);
            }
        }
        rotateOrderOfPlay(highestRankingPlayer);
    }

    public int countPlayersCards(Player player) {
        int count = 0;
        for (int i = 0; i < player.getHand().size(); i++) {
            count++;
        }
        return count;
    }

    public int numberOfRoundsLeft() {
        int count = 0;
        for (int inte : rounds) {
            count ++;
        }
        return count;
    }

    public void checkIfGameIsOverOrStartRound() {
        int roundLeft = numberOfRoundsLeft();
        if (roundLeft == 0) {
            endGame();
        } else {
            startARound();
        }
    }


    public void checkIfRoundIsOverOrStartStick() {
        Player player = orderOfGuess.get(0);
        int playersNumberOfCards = countPlayersCards(player);
        if (playersNumberOfCards == 0) {
            endRound();
        } else {
            startANewStick();
        }
    }

    public void startANewStick() {
        allPlayersPlayaCard();
        setTheValueOfforAllPlayers();
        //userIO.testToSePlayedCards(getOrderOfPlay());
        winnerGetsStick();
        rotateOrderOfPlayInAStick();
        userIO.printAllPlayersWithData(getOrderOfPlay());
        checkIfRoundIsOverOrStartStick();

    }


    public void startARound() {
        deckOfCards = new Deck();
        dealCardToAllPlayers();
        reciveTrumfCard();
        allPlayersShowHand();
        //userIO.printAllPlayers(getOrderOfPlay());
        userIO.printTrumfCard(trumfCard);
        allPlayersGuess();
        allPlayersGetSingelDidgitRankpoints();
        findTheHighestRankkingPlayer();
        //userIO.printAllPlayers(getOrderOfPlay());
        startANewStick();


    }

    public void startAGame() {
        createGameRounds();
        int numberOfP = userIO.setNumberOfPlayers();
        createPlayers(getOrderOfGuess(), numberOfP);
        createOrderofPlay();
        startARound();


    }

    public void endRound() {
        checkIfPLayersGetsPoints();
        rotatePlayers(orderOfGuess);
        userIO.printAllPlayersWithPoints(orderOfGuess);
        allPlayersClearStick();
        checkIfGameIsOverOrStartRound();

    }

    public void endGame(){
        userIO.printAllPlayersWithPoints(orderOfGuess);

    }



}

