import java.util.ArrayList;
import java.util.List;

public class Player {
    int guess = 0;
    int points = 0;
    int stick = 0;
    String playerName = "";


  private Player(String playerName) {
    this.playerName = playerName;
  }

  /*
      public void addCardToHand(Card card){
          hand.add(card);
      }

      public Card discard(int index){
          return hand.remove(index);
      }

      public void playCard(int index){
          playedCard = discard(index);
      }

  */
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