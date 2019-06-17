import java.util.ArrayList;
import java.util.List;

public class Player {
    private int guess = 0;
    private int points = 0;
    private int stick = 0;
    private String playerName = "";
    private List<Card> hand = new ArrayList<>();
    private Card playedCard;


  private Player(String playerName) {
    this.playerName = playerName;
  }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public int getPoints() {
        return points;
    }

    public int getStick() {
        return stick;
    }

    public List<Card> getHand() {
        return hand;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void addCardToHand(Card card){
        hand.add(card);
    }

    public Card discard(int index){
        return hand.remove(index);
    }

    public void playCard(int index){
        playedCard = discard(index);
    }


    public static Builder builder(){
  return new Builder();
}


  public static class Builder {

    private String playerName = "";;

    public Builder withPlayerName (String playerName) {
      this.playerName = playerName;
      return this;
    }


    public Player build() {
      return new Player(playerName);
    }
  }


}