package messaging;

import domain.Card;
import domain.Player;

public interface CardListener {
    void onCard(Card card, Player player);
}
