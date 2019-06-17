import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Integer> rounds = new ArrayList<>();
    private List<Player> orderOfPlay = new ArrayList<>();
    private List<Player> orderOfGuess = new ArrayList<>();
    private UserInterface userInterface;
    private Deck deckOfCards = new Deck();
    private Card trumfCard;

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
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
            userInterface.printPlayer(player);
            userInterface.printCards(player.getHand());
        }

    }

    public void createPlayers( List<Player> list, int numberOfPlayers){
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = userInterface.nameAPlayer(orderOfGuess);
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
            player.addCardToHand(deckOfCards.dealACard());
        }
    }


    public void rotatePlayers( List<Player> list) {
        List<Player> tempList = new ArrayList<>();;
        tempList.add(list.remove(0));
        list.add(tempList.remove(0));
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
            player.setGuess(userInterface.playerGuess(player));
        }
    }

    public void reciveTrumfCard(){
        this.trumfCard = deckOfCards.dealACard();
    }





}

