public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        Game game = new Game(userInterface);
        int a = userInterface.setNumberOfPlayers();
        game.createPlayers(a);
        System.out.println(game.getOrderOfPlay());
        game.rotateOrderOfPlay();
        System.out.println(game.getOrderOfPlay());

    }


}
