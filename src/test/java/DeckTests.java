import com.improving.uno.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTests {

    Deck deck;
 //   Hand hand;

    @BeforeEach
    public void arrange_tests() {
        deck = new Deck();
        deck.shuffle(deck.getDeck());
    }

    @Test
    public void draw_should_replace_deck() {
        List<Card> discarded = new ArrayList<>();
        discarded.add(new Card(Colors.Green, Faces.Five));
        discarded.add(new Card(Colors.Blue, Faces.Seven));
        discarded.add(new Card(Colors.Red, Faces.Draw2));
        List<Card> deck1 = new ArrayList<>();
        deck.setDiscarded(discarded);
        deck.setDeck(deck1);
        deck.draw();
        assertFalse(deck.getDeck().isEmpty());
    }

    @Test
    public void deck_draw_should_add_to_discard() {
        deck.draw();
        int result = deck.getDiscarded().size();
        assertEquals(1, result);
    }

    @Test
    public void deck_has_112_cards() {
        var result = deck.getDeck().size();
        assertEquals(112, result);
    }


}