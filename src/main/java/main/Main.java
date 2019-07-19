package main;

import domain.Card;
import io.UserIO;
import messaging.CardConsumer;
import messaging.CardProducer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new GenericXmlApplicationContext("beansUserIO.xml");
        Game game = context.getBean(Game.class);
        game.startAGame();




    }


}
