import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);

    int setNumberOfPlayers(){
        int numberOfPlayers = 0;
        System.out.println("Please select between 2 and 5 players");
        numberOfPlayers = scanner.nextInt();
        scanner.nextLine();
        return numberOfPlayers;
    }

    String nameAPlayer(List<Player> list){
        String playersName;
        System.out.println("Name this Player!");
        playersName = scanner.nextLine();
        return playersName;
    }

    int playerGuess(Player player){
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

    public void printPlayer(Player player){
            System.out.print(player.getPlayerName() +  ": ");
    }

    public void printCards(List<Card> list){
        for (Card card:list){
            showSelf(card);
        }
        System.out.println();


    }

    public void printTrumfCard(Card card){
        System.out.println("Trumfcard! ");
        showSelf(card);
    }
}

