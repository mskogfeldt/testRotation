import java.util.ArrayList;
import java.util.List;

public class Game {
    List<String> rounds = new ArrayList<>();
    List<Player> orderOfPlay = new ArrayList<>();
    List<Player> orderOfGuess = new ArrayList<>();
    private UserInterface userInterface;

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void createPlayers(int numberOfPlayers){
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = userInterface.nameAPlayer();
            Player plYER = Player.builder().withPlayerName(name).build();
            orderOfPlay.add(plYER);
        }
    }

    public List<Player> getOrderOfPlay() {
        return orderOfPlay;
    }

    public void rotateOrderOfPlay() {
        List<Player> tempList = new ArrayList<>();;
        tempList.add(orderOfPlay.remove(0));
        orderOfPlay.add(tempList.remove(0));
    }



}

