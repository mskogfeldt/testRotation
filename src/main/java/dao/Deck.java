package dao;

import domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deckOfCards = new ArrayList<>();


    public Deck() {
        generateDeck();
        shuffleDeck();
    }

    public List<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public Card dealACard(){
            return deckOfCards.remove(0);
    }

    public void generateDeck(){
    for (int i = 2; i < 15; i++){
        deckOfCards.add(new Hearts(i));
        deckOfCards.add(new Spades(i));
        deckOfCards.add(new Diamonds(i));
        deckOfCards.add(new Clubs(i));
        }

    }

    public void shuffleDeck(){
        Collections.shuffle(this.deckOfCards);
    }




}
