public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        Game game = new Game(userInterface);
        int numberOfP = userInterface.setNumberOfPlayers();
        game.createGameRounds();
        game.createPlayers(game.getOrderOfGuess(), numberOfP);
        userInterface.printAllPlayers(game.getOrderOfGuess());
        game.rotatePlayers(game.getOrderOfGuess());
        userInterface.printAllPlayers(game.getOrderOfGuess());
        System.out.println(game.getRounds());
        game.dealCardToAllPlayers();
        game.allPlayersShowHand();
        System.out.println(game.getRounds());


    }


}
