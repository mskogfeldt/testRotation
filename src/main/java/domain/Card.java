package domain;

public class Card {
    private int value;
    private String suit;
    private int sortingValue;


    public Card(int value, String suit) {
      this.value = value;
      this.suit = suit;
      this.sortingValue = sortingValue;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }


    public String findRank(){
        String rank = ""+ this.value;
        if (this.value == 11) rank = "Jack";
        if (this.value == 12) rank = "Queen";
        if (this.value == 13) rank = "King";
        if (this.value == 14) rank = "Ace";
        return rank;
    }
}
