package messaging;

import domain.Card;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CardConsumer {
    private BlockingQueue<Card> queue;
    private CardListener cardListener;

    public CardConsumer(BlockingDeque<Card> queue, CardListener cardListener) {
        this.queue = queue;
        this.cardListener = cardListener;
    }


    public void consumeCard(){
        try {

            Card card = queue.poll(10000, TimeUnit.MILLISECONDS);
            if(card != null) {
                cardListener.onCard(card);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
