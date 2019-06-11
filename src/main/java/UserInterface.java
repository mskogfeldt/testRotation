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

    String nameAPlayer(){
        String playersName;
        System.out.println("Name this Player! ");
        playersName = scanner.nextLine();
        return playersName;
    }


}

