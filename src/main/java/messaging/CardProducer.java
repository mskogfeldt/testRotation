package messaging;

import domain.Card;

import java.util.concurrent.BlockingDeque;

public class CardProducer {
    private BlockingDeque<Card> queue;

    public CardProducer(BlockingDeque<Card> queue) {
        this.queue = queue;
    }

    public void produce(Card card){
        queue.add(card);
    }
}
