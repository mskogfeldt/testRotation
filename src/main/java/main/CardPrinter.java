package main;

import domain.Card;
import messaging.CardListener;

public class CardPrinter implements CardListener {
    @Override
    public void onCard(Card card) {
        System.out.println("what card " + card);
    }
}
