package main;

import domain.Card;
import io.UserIO;
import messaging.CardConsumer;
import messaging.CardProducer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) {
        UserIO userIO = new UserIO();
        BlockingDeque <Card> blockingDeque = new LinkedBlockingDeque<>();

        CardProducer cardProducer = new CardProducer(blockingDeque);

        //CardPrinter cardPrinter = new CardPrinter();

        Game game = new Game(userIO, cardProducer);
        game.startAGame();
        game.startARound();
        CardConsumer cardConsumer = new CardConsumer(blockingDeque, userIO);
        cardConsumer.consumeCard();
        cardConsumer.consumeCard();
        cardConsumer.consumeCard();
        cardConsumer.consumeCard();
        cardConsumer.consumeCard();
        cardConsumer.consumeCard();
        cardConsumer.consumeCard();
        cardConsumer.consumeCard();
        cardConsumer.consumeCard();
        cardConsumer.consumeCard();
        cardConsumer.consumeCard();
    }


}
